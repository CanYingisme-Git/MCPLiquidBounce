package net.minecraft.client.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import javax.crypto.SecretKey;

import net.ccbluex.liquidbounce.injection.backend.NetworkManagerImplKt;
import net.ccbluex.liquidbounce.injection.backend.SPacketEncryptionRequestImplKt;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.mcleaks.MCLeaks;
import net.mcleaks.Session;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.network.login.server.S03PacketEnableCompression;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.CryptManager;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerLoginClient implements INetHandlerLoginClient
{
    private static final Logger logger = LogManager.getLogger();
    private final Minecraft mc;
    private final GuiScreen previousGuiScreen;
    private final NetworkManager networkManager;
    private GameProfile gameProfile;

    public NetHandlerLoginClient(NetworkManager p_i45059_1_, Minecraft mcIn, GuiScreen p_i45059_3_)
    {
        this.networkManager = p_i45059_1_;
        this.mc = mcIn;
        this.previousGuiScreen = p_i45059_3_;
    }

    public void handleEncryptionRequest(S01PacketEncryptionRequest packetIn)
    {
        {
            if(MCLeaks.isAltActive()) {
                final SecretKey secretkey = CryptManager.createNewSharedKey();
                String s = packetIn.getServerId();
                PublicKey publickey = packetIn.getPublicKey();
                String s1 = (new BigInteger(CryptManager.getServerIdHash(s, publickey, secretkey))).toString(16);

                final Session session = MCLeaks.getSession();
                final String server = ((InetSocketAddress) this.networkManager.getRemoteAddress()).getHostName() + ":" + ((InetSocketAddress) this.networkManager.getRemoteAddress()).getPort();

                try {
                    final String jsonBody = "{\"session\":\"" + session.getToken() + "\",\"mcname\":\"" + session.getUsername() + "\",\"serverhash\":\"" + s1 + "\",\"server\":\"" + server + "\"}";

                    final HttpURLConnection connection = (HttpURLConnection) new URL("https://auth.mcleaks.net/v1/joinserver").openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                    connection.setDoOutput(true);

                    final DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    outputStream.write(jsonBody.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    outputStream.close();

                    final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    final StringBuilder outputBuilder = new StringBuilder();

                    String line;
                    while((line = reader.readLine()) != null)
                        outputBuilder.append(line);

                    reader.close();
                    final JsonElement jsonElement = new Gson().fromJson(outputBuilder.toString(), JsonElement.class);

                    if(!jsonElement.isJsonObject() || !jsonElement.getAsJsonObject().has("success")) {
                        this.networkManager.closeChannel(new ChatComponentText("Invalid response from MCLeaks API"));
                        return;
                    }

                    if(!jsonElement.getAsJsonObject().get("success").getAsBoolean()) {
                        String errorMessage = "Received success=false from MCLeaks API";

                        if(jsonElement.getAsJsonObject().has("errorMessage"))
                            errorMessage = jsonElement.getAsJsonObject().get("errorMessage").getAsString();

                        this.networkManager.closeChannel(new ChatComponentText(errorMessage));
                        return;
                    }
                }catch(final Exception e) {
                    this.networkManager.closeChannel(new ChatComponentText("Error whilst contacting MCLeaks API: " + e.toString()));
                    return;
                }

                ClientUtils.sendEncryption(NetworkManagerImplKt.wrap(networkManager), secretkey, publickey, SPacketEncryptionRequestImplKt.wrap(packetIn));
                return;
            }
        }
        final SecretKey secretkey = CryptManager.createNewSharedKey();
        String s = packetIn.getServerId();
        PublicKey publickey = packetIn.getPublicKey();
        String s1 = (new BigInteger(CryptManager.getServerIdHash(s, publickey, secretkey))).toString(16);

        if (this.mc.getCurrentServerData() != null && this.mc.getCurrentServerData().func_181041_d())
        {
            try
            {
                this.getSessionService().joinServer(this.mc.getSession().getProfile(), this.mc.getSession().getToken(), s1);
            }
            catch (AuthenticationException var10)
            {
                logger.warn("Couldn\'t connect to auth servers but will continue to join LAN");
            }
        }
        else
        {
            try
            {
                this.getSessionService().joinServer(this.mc.getSession().getProfile(), this.mc.getSession().getToken(), s1);
            }
            catch (AuthenticationUnavailableException var7)
            {
                this.networkManager.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[] {new ChatComponentTranslation("disconnect.loginFailedInfo.serversUnavailable", new Object[0])}));
                return;
            }
            catch (InvalidCredentialsException var8)
            {
                this.networkManager.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[] {new ChatComponentTranslation("disconnect.loginFailedInfo.invalidSession", new Object[0])}));
                return;
            }
            catch (AuthenticationException authenticationexception)
            {
                this.networkManager.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[] {authenticationexception.getMessage()}));
                return;
            }
        }

        this.networkManager.sendPacket(new C01PacketEncryptionResponse(secretkey, publickey, packetIn.getVerifyToken()), new GenericFutureListener < Future <? super Void >> ()
        {
            public void operationComplete(Future <? super Void > p_operationComplete_1_) throws Exception
            {
                NetHandlerLoginClient.this.networkManager.enableEncryption(secretkey);
            }
        }, new GenericFutureListener[0]);
    }

    private MinecraftSessionService getSessionService()
    {
        return this.mc.getSessionService();
    }

    public void handleLoginSuccess(S02PacketLoginSuccess packetIn)
    {
        this.gameProfile = packetIn.getProfile();
        this.networkManager.setConnectionState(EnumConnectionState.PLAY);
        this.networkManager.setNetHandler(new NetHandlerPlayClient(this.mc, this.previousGuiScreen, this.networkManager, this.gameProfile));
    }

    /**
     * Invoked when disconnecting, the parameter is a ChatComponent describing the reason for termination
     */
    public void onDisconnect(IChatComponent reason)
    {
        this.mc.displayGuiScreen(new GuiDisconnected(this.previousGuiScreen, "connect.failed", reason));
    }

    public void handleDisconnect(S00PacketDisconnect packetIn)
    {
        this.networkManager.closeChannel(packetIn.func_149603_c());
    }

    public void handleEnableCompression(S03PacketEnableCompression packetIn)
    {
        if (!this.networkManager.isLocalChannel())
        {
            this.networkManager.setCompressionTreshold(packetIn.getCompressionTreshold());
        }
    }
}

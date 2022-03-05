package al.nya.mcpbounce.fakeforge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.awt.*;
import java.io.IOException;

public class GuiModList extends GuiScreen {
    private GuiScreen parentScreen;
    public GuiModList(GuiScreen parentScreen)
    {
        this.parentScreen = parentScreen;
    }
    public void initGui(){
        this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 76, this.height - 28, 75, 20, I18n.format("gui.cancel", new Object[0])));

    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawBackground(0);
        Minecraft.getMinecraft().fontRendererObj.drawString("Fake Forge - ModList",0,0, Color.BLACK.getRGB());
    }
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0)
        {
            this.mc.displayGuiScreen(this.parentScreen);
        }
    }
}

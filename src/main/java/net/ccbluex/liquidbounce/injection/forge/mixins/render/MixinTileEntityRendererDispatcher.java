/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.render;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.modules.render.XRay;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

//@Mixin(TileEntityRendererDispatcher.class)
@Deprecated
public class MixinTileEntityRendererDispatcher {
    /*
    @Inject(method = "renderTileEntity", at = @At("HEAD"), cancellable = true)
    private void renderTileEntity(TileEntity tileentityIn, float partialTicks, int destroyStage, final CallbackInfo callbackInfo) {
        final XRay xray = (XRay) LiquidBounce.moduleManager.getModule(XRay.class);

        if (xray.getState() && !xray.getXrayBlocks().contains(tileentityIn.getBlockType()))
            callbackInfo.cancel();
    }

     */
}

/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.block;

//@Mixin(BlockSoulSand.class)
//@SideOnly(Side.CLIENT)
@Deprecated
public class MixinBlockSoulSand {
    /*
    @Inject(method = "onEntityCollidedWithBlock", at = @At("HEAD"), cancellable = true)
    private void onEntityCollidedWithBlock(CallbackInfo callbackInfo) {
        final NoSlow noSlow = (NoSlow) LiquidBounce.moduleManager.getModule(NoSlow.class);

        if (Objects.requireNonNull(noSlow).getState() && noSlow.getSoulsandValue().get())
            callbackInfo.cancel();
    }

     */
}
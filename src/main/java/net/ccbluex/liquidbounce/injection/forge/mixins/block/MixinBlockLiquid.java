/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.block;

//@SideOnly(Side.CLIENT)
//@Mixin(BlockLiquid.class)
@Deprecated
public class MixinBlockLiquid {

    /*@Inject(method = "canCollideCheck", at = @At("HEAD"), cancellable = true)
    private void onCollideCheck(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (Objects.requireNonNull(LiquidBounce.moduleManager.getModule(Liquids.class)).getState())
            callbackInfoReturnable.setReturnValue(true);
    }

    @Inject(method = "modifyAcceleration", at = @At("HEAD"), cancellable = true)
    private void onModifyAcceleration(CallbackInfoReturnable<Vec3> callbackInfoReturnable) {
        final NoSlow noSlow = (NoSlow) LiquidBounce.moduleManager.getModule(NoSlow.class);

        if (noSlow.getState() && noSlow.getLiquidPushValue().get()) {
            callbackInfoReturnable.setReturnValue(new Vec3(0.0D, 0.0D, 0.0D));
        }
    }

     */
}
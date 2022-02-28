package net.ccbluex.liquidbounce.injection.forge.mixins.block;

//@Mixin(BlockAnvil.class)
@Deprecated
public abstract class MixinBlockAnvil extends MixinBlock {
    /*
    @Inject(method = "onBlockPlaced", cancellable = true, at = @At("HEAD"))
    private void injectAnvilCrashFix(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, CallbackInfoReturnable<IBlockState> cir) {
        if (((meta >> 2) & ~0x3) != 0) {
            cir.setReturnValue(super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(BlockAnvil.FACING, placer.getHorizontalFacing().rotateY()).withProperty(BlockAnvil.DAMAGE, 2));
            cir.cancel();
        }
    }*/
}

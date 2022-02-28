/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.block;


//@Mixin(BlockLadder.class)
//@SideOnly(Side.CLIENT)
@Deprecated
public abstract class MixinBlockLadder extends MixinBlock {

    /*@Shadow
    @Final
    public static PropertyDirection FACING;

    /**
     * @author CCBlueX
     */
    /*
    @Overwrite
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        final IBlockState iblockstate = worldIn.getBlockState(pos);

        if(iblockstate.getBlock() instanceof BlockLadder) {
            final FastClimb fastClimb = (FastClimb) LiquidBounce.moduleManager.getModule(FastClimb.class);
            final float f = Objects.requireNonNull(fastClimb).getState() && fastClimb.getModeValue().get().equalsIgnoreCase("AAC3.0.0") ? 0.99f : 0.125f;

            switch(iblockstate.getValue(FACING)) {
                case NORTH:
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                    break;
                case SOUTH:
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                    break;
                case WEST:
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;
                case EAST:
                default:
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            }
        }
    }*/
}

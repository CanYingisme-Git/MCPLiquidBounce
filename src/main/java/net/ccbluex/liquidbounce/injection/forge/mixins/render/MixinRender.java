/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.render;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.RenderEntityEvent;
import net.ccbluex.liquidbounce.injection.backend.EntityImplKt;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;


//@Mixin(Render.class)
@Deprecated
public abstract class MixinRender {
    /*
    @Shadow
    protected abstract <T extends Entity> boolean bindEntityTexture(T entity);

    @Inject(method = "doRender", at = @At("HEAD"))
    private void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo callbackInfo) {
        LiquidBounce.eventManager.callEvent(new RenderEntityEvent(EntityImplKt.wrap(entity), x, y, z, entityYaw, partialTicks));
    }

     */
}

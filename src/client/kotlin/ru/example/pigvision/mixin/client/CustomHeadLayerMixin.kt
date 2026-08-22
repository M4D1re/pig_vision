package ru.example.pigvision.mixin.client

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.SkullBlock
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(CustomHeadLayer::class)
abstract class CustomHeadLayerMixin {

    @Inject(
        method = ["submit"],
        at = [At("HEAD")]
    )
    private fun pigVisionHead(
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        lightCoords: Int,
        state: LivingEntityRenderState,
        yRot: Float,
        xRot: Float,
        ci: CallbackInfo
    ) {
        if (state.entityType == EntityType.PLAYER) {
            state.wornHeadType = SkullBlock.Types.PIGLIN
        }
    }
}
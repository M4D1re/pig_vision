package ru.example.pigvision.mixin.client

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.SkullBlock
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.ModifyArg
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import ru.example.pigvision.PigVisionConfig

@Mixin(CustomHeadLayer::class)
abstract class CustomHeadLayerMixin {

    @Inject(
        method = ["submit"],
        at = [At("HEAD")]
    )
    private fun forcePiglinHead(
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        lightCoords: Int,
        state: LivingEntityRenderState,
        yRot: Float,
        xRot: Float,
        ci: CallbackInfo
    ) {
        if (
            PigVisionConfig.enabled &&
            state.entityType == EntityType.PLAYER
        ) {
            state.wornHeadType = SkullBlock.Types.PIGLIN
        }
    }

    @ModifyArg(
        method = ["resolveSkullRenderType"],
        at = At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/blockentity/SkullBlockRenderer;" +
                    "getSkullRenderType(" +
                    "Lnet/minecraft/world/level/block/SkullBlock\$Type;" +
                    "Lnet/minecraft/resources/Identifier;" +
                    ")Lnet/minecraft/client/renderer/rendertype/RenderType;"
        ),
        index = 1
    )
    private fun selectPiglinTexture(originalTexture: Identifier?): Identifier {
        return if (PigVisionConfig.useResourcePackTexture) {
            Identifier.fromNamespaceAndPath(
                "pigvision",
                "textures/entity/piglin/piglin.png"
            )
        } else {
            Identifier.fromNamespaceAndPath(
                "minecraft",
                "textures/entity/piglin/piglin.png"
            )
        }
    }
}
package ru.example.pigvision.mixin.client


import ru.example.pigvision.PigVisionConfig
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer
import net.minecraft.client.renderer.entity.layers.RenderLayer
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(HumanoidArmorLayer::class)
abstract class HumanoidArmorLayerMixin<
        S : HumanoidRenderState,
        M : HumanoidModel<S>,
        A : HumanoidModel<S>
        >(
    renderer: RenderLayerParent<S, M>
) : RenderLayer<S, M>(renderer) {

    @Inject(
        method = ["renderArmorPiece"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun hideHelmet(
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        itemStack: ItemStack,
        slot: EquipmentSlot,
        lightCoords: Int,
        state: S,
        ci: CallbackInfo
    ) {
        if (
            PigVisionConfig.enabled &&
            slot == EquipmentSlot.HEAD
        ) {
            ci.cancel()
        }
    }
}
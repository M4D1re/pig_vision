package ru.example.pigvision

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.ClientCommands
import net.minecraft.network.chat.Component

class PigVisionClient : ClientModInitializer {

    override fun onInitializeClient() {
        PigVariantManager.initialize()

        ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->

            dispatcher.register(
                ClientCommands.literal("pigvision")

                    .then(
                        ClientCommands.literal("on")
                            .executes { context ->
                                PigVisionConfig.enable()

                                context.source.sendFeedback(
                                    Component.literal("Pig Vision: enabled")
                                )

                                1
                            }
                    )

                    .then(
                        ClientCommands.literal("off")
                            .executes { context ->
                                PigVisionConfig.disable()

                                context.source.sendFeedback(
                                    Component.literal("Pig Vision: disabled")
                                )

                                1
                            }
                    )

                    .then(
                        ClientCommands.literal("rp")
                            .executes { context ->
                                PigVisionConfig.useResourcePack()

                                context.source.sendFeedback(
                                    Component.literal("Pig Vision: RP texture enabled")
                                )

                                1
                            }
                    )

                    .then(
                        ClientCommands.literal("vanilla")
                            .executes { context ->
                                PigVisionConfig.useVanilla()

                                context.source.sendFeedback(
                                    Component.literal("Pig Vision: vanilla texture enabled")
                                )

                                1
                            }
                    )
            )
        }
    }
}
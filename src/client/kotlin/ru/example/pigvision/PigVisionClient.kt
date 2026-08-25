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
                        ClientCommands.literal("rp")
                            .executes { context ->
                                PigVisionConfig.useResourcePack()

                                context.source.sendFeedback(
                                    Component.literal(
                                        "Pig Vision: RP texture enabled"
                                    )
                                )

                                1
                            }
                    )

                    .then(
                        ClientCommands.literal("vanilla")
                            .executes { context ->
                                PigVisionConfig.useVanilla()

                                context.source.sendFeedback(
                                    Component.literal(
                                        "Pig Vision: vanilla texture enabled"
                                    )
                                )

                                1
                            }
                    )

                    .then(
                        ClientCommands.literal("toggle")
                            .executes { context ->

                                val rpEnabled = PigVisionConfig.toggle()

                                val mode = if (rpEnabled) {
                                    "RP"
                                } else {
                                    "vanilla"
                                }

                                context.source.sendFeedback(
                                    Component.literal(
                                        "Pig Vision: $mode texture enabled"
                                    )
                                )

                                1
                            }
                    )
            )
        }
    }
}
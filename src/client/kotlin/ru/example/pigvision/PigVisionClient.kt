package ru.example.pigvision

import net.fabricmc.api.ClientModInitializer

class PigVisionClient : ClientModInitializer {

    override fun onInitializeClient() {
        PigVariantManager.initialize()
    }
}
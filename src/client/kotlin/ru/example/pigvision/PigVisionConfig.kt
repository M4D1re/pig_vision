package ru.example.pigvision

object PigVisionConfig {

    var useResourcePackTexture: Boolean = true
        private set

    fun useResourcePack() {
        useResourcePackTexture = true
    }

    fun useVanilla() {
        useResourcePackTexture = false
    }

    fun toggle(): Boolean {
        useResourcePackTexture = !useResourcePackTexture
        return useResourcePackTexture
    }
}
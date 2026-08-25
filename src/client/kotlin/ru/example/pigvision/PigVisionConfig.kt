package ru.example.pigvision

object PigVisionConfig {

    var enabled: Boolean = true
        private set

    var useResourcePackTexture: Boolean = true
        private set

    fun enable() {
        enabled = true
    }

    fun disable() {
        enabled = false
    }

    fun toggleEnabled(): Boolean {
        enabled = !enabled
        return enabled
    }

    fun useResourcePack() {
        useResourcePackTexture = true
    }

    fun useVanilla() {
        useResourcePackTexture = false
    }

    fun toggleTexture(): Boolean {
        useResourcePackTexture = !useResourcePackTexture
        return useResourcePackTexture
    }
}
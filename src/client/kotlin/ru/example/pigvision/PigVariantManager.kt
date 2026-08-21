package ru.example.pigvision

import java.util.UUID
import kotlin.random.Random

object PigVariantManager {

    /**
     * Набор вариантов голов свиней.
     *
     */
    private val pigVariants = listOf(
        "pig",
        "zombified_piglin",
        "hoglin",
        "zoglin"
    )

    private val playerVariants = mutableMapOf<UUID, String>()

    fun initialize() {
        println("[PigVision] Pig variant manager initialized")
    }

    /**
     * Возвращает существующий вариант игрока
     * или случайным образом назначает новый.
     */
    fun getOrAssignVariant(uuid: UUID): String {
        return playerVariants.getOrPut(uuid) {
            pigVariants[Random.nextInt(pigVariants.size)].also {
                println("[PigVision] Assigned $it to $uuid")
            }
        }
    }

    /**
     * Удаляет данные игрока.
     *
     */
    fun removePlayer(uuid: UUID) {
        playerVariants.remove(uuid)
    }

    fun clear() {
        playerVariants.clear()
    }
}
package me.nepnep.dimlist

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.minecraft.text.LiteralText
import org.apache.logging.log4j.LogManager
import java.io.File
import java.io.IOException

@Suppress("unused")
class DimListClientMod : ClientModInitializer {
    override fun onInitializeClient() {
        ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("dimlist").executes { context ->
            val identifiers = context.source
                .registryManager
                .dimensionTypes
                .ids
                .map { it.toString() }
            val file = File("Dimensions.txt")
            try {
                file.writer().use {
                    val formatted = identifiers.joinToString(",\n")
                    it.write(formatted)
                }
                context.source.sendFeedback(LiteralText("Dimension list saved at " + file.absolutePath))
            } catch (e: IOException) {
                LogManager.getLogger("DimList").error("Failed to write to file", e)
                context.source.sendFeedback(LiteralText("Failed to write to file, check your log"))
            }
            return@executes 1
        })
    }
}
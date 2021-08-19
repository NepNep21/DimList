package me.nepnep.dimlist

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString
import net.minecraft.world.DimensionType
import net.minecraftforge.common.DimensionManager
import org.apache.logging.log4j.LogManager
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class Command : ICommand {
    override fun compareTo(other: ICommand?): Int = 0

    override fun getName(): String = "dimlist"

    override fun getUsage(sender: ICommandSender): String = "/dimlist"

    override fun getAliases(): MutableList<String> = mutableListOf()

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<out String>) {
        val ids = DimensionManager.getRegisteredDimensions()
            .values
            .map { it.firstInt() }
        val names = ids.map { DimensionType.getById(it).name }

        val properties = Properties()
        for (i in ids.indices) {
            properties[ids[i].toString()] = names[i]
        }

        val file = File("Dimensions.txt")
        try {
            file.createNewFile()
            properties.store(FileOutputStream(file), null)
            sender.sendMessage(TextComponentString("Dimension list created at " + file.absolutePath))
        } catch (e: IOException) {
            LogManager.getLogger("DimList").error("Failed to write to file", e)
            sender.sendMessage(TextComponentString("Failed to write to file, check your log"))
        }
    }

    override fun checkPermission(server: MinecraftServer, sender: ICommandSender): Boolean = true

    override fun getTabCompletions(
        server: MinecraftServer,
        sender: ICommandSender,
        args: Array<out String>,
        targetPos: BlockPos?
    ): MutableList<String> = mutableListOf()

    override fun isUsernameIndex(args: Array<out String>, index: Int): Boolean = false
}
package me.nepnep.dimlist

import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = "dimlist")
class DimList {
    @Mod.EventHandler
    @Suppress("UNUSED_PARAMETER")
    fun preInit(event: FMLPreInitializationEvent) {
        ClientCommandHandler.instance.registerCommand(Command())
    }
}
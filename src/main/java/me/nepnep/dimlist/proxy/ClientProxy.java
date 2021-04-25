package me.nepnep.dimlist.proxy;

import me.nepnep.dimlist.Command;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new Command());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}

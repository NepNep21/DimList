package me.nepnep.dimlist;

import me.nepnep.dimlist.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = DimList.MODID, name = DimList.NAME, version = DimList.VERSION)
public class DimList {
    public static final String MODID = "dimlist";
    public static final String NAME = "DimList";
    public static final String VERSION = "1.0.0";
    public static final String SERVER = "me.nepnep.dimlist.proxy.ServerProxy";
    public static final String CLIENT = "me.nepnep.dimlist.proxy.ClientProxy";

    private static Logger logger;

    @SidedProxy(clientSide = DimList.CLIENT, serverSide = DimList.SERVER)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}

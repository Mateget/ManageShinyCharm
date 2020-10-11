package shinycharm;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import commands.Removemegaring;
import commands.Removeshinycharm;


@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION ,acceptableRemoteVersions = "*", serverSideOnly = true)
public class Main
{
    public static final String MODID = "rsc";
    public static final String NAME = "Remove Shiny Charm";
    public static final String VERSION = "1.0";
    public static Logger logger;
    @Instance
    public static Main instance = new Main();
    public static Main getInstance() {
    	return instance;
    }
    @SidedProxy(serverSide="shinycharm.ServerProxy")
    public static ServerProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
        Main.proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        Main.proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        Main.proxy.postInit(e);
    }
    
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent e) {
    	Removeshinycharm removeshinycharm = new Removeshinycharm();
    	e.registerServerCommand(removeshinycharm);
    	logger.info("Removeshinycharm command added !");
    	Removemegaring removemegaring = new Removemegaring();
    	e.registerServerCommand(removemegaring);
    	logger.info("Removemegaring command added !");
    	
    }
    
    
}

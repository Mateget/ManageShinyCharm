package shinycharm;

import java.io.File;

import org.apache.logging.log4j.Logger;

import commands.Charm;
import commands.Charmreload;
import commands.Removemegaring;
import commands.Removeshinycharm;
import data.CapabilityHandler;
import data.IShinyCharmTemp;
import data.ShinyCharmTempProvider;
import data.ShinyCharmTemp;
import data.ShinyCharmTempStorage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import shinycharm.config.FileHandler;


@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION ,acceptableRemoteVersions = "*" ,serverSideOnly = true )
public class Main
{
    public static final String MODID = "rsc";
    public static final String NAME = "Remove Shiny Charm";
    public static final String VERSION = "1.0.2";
    public static Logger logger;
    public static File configDir;
    public static File configFile;
    
    @Instance
    public static Main instance = new Main();
    public static Main getInstance() {
    	return instance;
    }
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    	logger.info("Booting up " + NAME);
        configDir = new File(event.getModConfigurationDirectory() + "/" + MODID);
        configDir.mkdir();
        configFile = new File(configDir, "config.json");
        FileHandler.readConfig();
        FileHandler.creationCheck();
        FileHandler.writeConfig();
        CapabilityManager.INSTANCE.register(IShinyCharmTemp.class, new ShinyCharmTempStorage(), ShinyCharmTemp.class);
        MinecraftForge.EVENT_BUS.register(new ShinyCharmTempProvider());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new data.EventHandler());
        
        logger.info("Finished the booting process");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }
    
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent e) {
    	
    	Removeshinycharm removeshinycharm = new Removeshinycharm();
    	e.registerServerCommand(removeshinycharm);
    	
    	Removemegaring removemegaring = new Removemegaring();
    	e.registerServerCommand(removemegaring);
    	
    	Charm charm = new Charm();
    	e.registerServerCommand(charm);
    	
    	Charmreload reload = new Charmreload();
    	e.registerServerCommand(reload);
    	
    }
    
    
}

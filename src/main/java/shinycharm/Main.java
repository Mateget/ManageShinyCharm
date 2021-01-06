package shinycharm;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.server.FMLServerHandler;
import commands.Reload;
import commands.Removemegaring;
import commands.Removeshinycharm;
import commands.Wololo;


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
    	logger.info("Booting up " + NAME + " made by Mateget - " + VERSION);
        configDir = new File(event.getModConfigurationDirectory() + "/" + MODID);
        configDir.mkdir();
        configFile = new File(configDir, "config.json");
        FileHandler.readConfig();
        FileHandler.creationCheck();
        FileHandler.writeConfig();
        
        logger.info("Finished the booting process");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new ShinyCharmEffect());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }
    
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent e) {
    	Removeshinycharm removeshinycharm = new Removeshinycharm();
    	e.registerServerCommand(removeshinycharm);
    	logger.info("Removeshinycharm command added !");
    	Removemegaring removemegaring = new Removemegaring();
    	e.registerServerCommand(removemegaring);
    	logger.info("Removemegaring command added !");
    	Wololo wololo = new Wololo();
    	e.registerServerCommand(wololo);
    	logger.info("Wololo command added !");
    	Reload reload = new Reload();
    	e.registerServerCommand(reload);
    	logger.info("Reload command added !");
    	/*
    	 * MinecraftServer server = FMLServerHandler.instance().getServer();
    	ServerCommandManager test = new ServerCommandManager(server);
    	logger.info(test.getCommands().toString());
    	*/
    	/*MinecraftServer server = FMLServerHandler.instance().getServer();
    	ICommandManager commandManager = server.getCommandManager();
    	ServerCommandManager serverCommandManager = ((ServerCommandManager)commandManager);
    	logger.info(serverCommandManager.getCommands().toString());*/
    	
    }
    
    
}

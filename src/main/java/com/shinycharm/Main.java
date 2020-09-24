package com.shinycharm;

import org.apache.logging.log4j.Logger;

import com.shinycharm.commands.Removeshinycharm;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION ,acceptableRemoteVersions = "*", serverSideOnly = true)
public class Main
{
    public static final String MODID = "scm";
    public static final String NAME = "Shiny Charm Manager";
    public static final String VERSION = "1.0";
    public static Logger logger;
    @Instance
    public static Main instance = new Main();
    public static Main getInstance() {
    	return instance;
    }
    @SidedProxy(serverSide="com.shinycharm.ServerProxy")
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
    	Removeshinycharm Removeshinycharm = new Removeshinycharm();
    	e.registerServerCommand(Removeshinycharm);
    	logger.info("Removeshinycharm command added !");
    	
    }
    
    
}

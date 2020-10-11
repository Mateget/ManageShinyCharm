package shinycharm;


import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
public class ServerProxy extends CommonProxy {
	
	public static MinecraftServer server;
    @Override
    public void preInit(FMLPreInitializationEvent e) {;
        super.preInit(e);
    }

    @Override @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
    	super.init(e);
    	
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}
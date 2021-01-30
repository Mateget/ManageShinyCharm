package data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shinycharm.Main;

/**
 * Capability handler
 * 
 * This class is responsible for attaching our capabilities
 */

public class CapabilityHandler {
	 public static final ResourceLocation RECIPES_CAP = new ResourceLocation(Main.MODID, "recipes");
	 
	 @SubscribeEvent
	 public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		 if (event.getObject() instanceof EntityPlayer)
			 event.addCapability(RECIPES_CAP, new ShinyCharmTempProvider());
	 }
}


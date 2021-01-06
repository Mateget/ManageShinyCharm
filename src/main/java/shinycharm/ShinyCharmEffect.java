package shinycharm;


import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.enums.EnumFeatureState;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ShinyCharmEffect {
	
	int CHECK_TIME =10; // seconds
	int tick = 0;
	boolean active;
	;
	@SubscribeEvent
    public void shinyCharmEffect(PlayerTickEvent e) {
	  if(tick != 20*CHECK_TIME){
	      tick++;
	      return;
	  }
	  tick = 0;
	  
	  EntityPlayer player = (EntityPlayer) e.player;
	  
	  if(!FileHandler.config.getActive()) {
		  if(hasShinyCharm(player) && hasShinyCharmActive(player)) {
			  unsetShinyCharm(player);
		  }
		  
	  } else {
		  
		  if(player.getEntityData().hasKey("tempshinycharm") && player.getEntityData().getBoolean("tempshinycharm")){
			  int timeLeft = player.getEntityData().getInteger("timeshinycharm");
			  if(timeLeft>0) {
				  if(!hasShinyCharm(e.player)) {
					  setShinyCharmAvavilable(e.player);
					  setShinyCharmActive(e.player);
				  }
				  player.getEntityData().setInteger("timeshinycharm", timeLeft-CHECK_TIME);
			  } else {
				  player.getEntityData().setBoolean("tempshinycharm", false);
				  player.getEntityData().setInteger("timeshinycharm", 0);
				  unsetShinyCharm(player);
				  player.sendMessage(format(net.minecraft.util.text.TextFormatting.GRAY, "Your Shiny Charm has just expired"));
			  }
		  }
	  }
	}
	  
	  	
	private void setShinyCharmAvavilable(EntityPlayer player) {
		Pixelmon.storageManager.getParty(player.getUniqueID()).setShinyCharm(EnumFeatureState.Available);
	}
	private void setShinyCharmActive(EntityPlayer player) {
		Pixelmon.storageManager.getParty(player.getUniqueID()).setShinyCharm(EnumFeatureState.Active);
	}
	private void unsetShinyCharm(EntityPlayer player) {
		Pixelmon.storageManager.getParty(player.getUniqueID()).setShinyCharm(EnumFeatureState.Disabled);
	}
	private boolean hasShinyCharm(EntityPlayer player) {
		return Pixelmon.storageManager.getParty(player.getUniqueID()).getShinyCharm().isAvailable();
	}
	private boolean hasShinyCharmActive(EntityPlayer player) {
		return Pixelmon.storageManager.getParty(player.getUniqueID()).getShinyCharm().isActive();
	}
	
	private TextComponentTranslation format(TextFormatting color, String str, Object... args)
    {
        TextComponentTranslation ret = new TextComponentTranslation(str, args);
        ret.getStyle().setColor(color);
        return ret;
    }
}



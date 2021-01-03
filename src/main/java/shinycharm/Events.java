package shinycharm;


import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.enums.EnumFeatureState;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class Events {
	
	int CHECK_TIME = 2; // seconds
	int tick = 0;
	@SubscribeEvent
    public void shinyCharmEffect(PlayerTickEvent e) {
	  if(tick != 20*CHECK_TIME){
	      tick++;
	      return;
	  }
	  tick = 0;
	  EntityPlayer player = (EntityPlayerMP) e.player;
	  if(player.getEntityData().hasKey("tempshinycharm") && player.getEntityData().getBoolean("tempshinycharm")){
		  int timeLeft = player.getEntityData().getInteger("timeshinycharm");
		  if(timeLeft>0) {
			  if(!this.hasShinyCharm(e.player)) {
				  this.setShinyCharmAvavilable(e.player);
				  this.setShinyCharmActive(e.player);
			  }
			  player.getEntityData().setInteger("timeshinycharm", timeLeft-CHECK_TIME);
		  } else {
			  player.getEntityData().setBoolean("tempshinycharm", false);
			  player.getEntityData().setInteger("timeshinycharm", 0);
			  this.unsetShinyCharm(player);
			  player.sendMessage(format(net.minecraft.util.text.TextFormatting.GRAY, "Your Shiny Charm has just expired"));
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
	
	private TextComponentTranslation format(TextFormatting color, String str, Object... args)
    {
        TextComponentTranslation ret = new TextComponentTranslation(str, args);
        ret.getStyle().setColor(color);
        return ret;
    }
}



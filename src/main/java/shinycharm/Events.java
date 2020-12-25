package shinycharm;


import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.enums.EnumFeatureState;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class Events {

	@SubscribeEvent
    public void shinyCharmEffect(PlayerTickEvent e) {
	  if(e.player.isPotionActive(PotionInit.shinyCharmPotion_Effect)){
		  if(!this.hasShinyCharm(e.player)) {
			  this.setShinyCharmAvavilable(e.player);
			  this.setShinyCharmActive(e.player);
		  }
	  }
	  else {
		  if(this.hasShinyCharm(e.player) || this.hasShinyCharmActive(e.player))
			  this.unsetShinyCharm(e.player);
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



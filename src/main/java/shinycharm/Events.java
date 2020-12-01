package shinycharm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.storage.IStorageManager;
import com.pixelmonmod.pixelmon.enums.EnumFeatureState;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class Events {

	@SubscribeEvent
    public void shinyCharmEffect(PlayerTickEvent e) {
	  boolean isActive = false;
	  if(e.player.isPotionActive(PotionInit.shinyCharmPotion_Effect)) isActive=true;
	  
	  if(isActive && hasShinyCharm(e.player)) {
		  this.setShinyCharm(e.player);
	  } else {
		  this.unsetShinyCharm(e.player);
	  }
    }
	
	private void setShinyCharm(EntityPlayer player) {
		Pixelmon.storageManager.getParty(player.getUniqueID()).setShinyCharm(EnumFeatureState.Available);
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



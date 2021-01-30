package data;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.enums.EnumFeatureState;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.network.play.server.SPacketTitle.Type;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import shinycharm.config.FileHandler;

public class EventHandler{
	
	int CHECK_TIME = 1; // seconds
	int tick = 0;
	
	@SubscribeEvent
	 public void onPlayerLogsIn(PlayerLoggedInEvent event){
		 EntityPlayer player = event.player;
		 IShinyCharmTemp shinyCharmTemp = player.getCapability(ShinyCharmTempProvider.SHINYCHARMTEMP_CAP, EnumFacing.UP);
		 if(shinyCharmTemp.getTime()>0){
			  EntityPlayerMP play = (EntityPlayerMP)player;
			  int timeString = shinyCharmTemp.getTime()/60;
			  String time = ""+shinyCharmTemp.getTime()%60;
			  if(timeString>0) time = timeString+"m"+time;				  
			  SPacketTitle packet = new SPacketTitle (Type.ACTIONBAR,format(net.minecraft.util.text.TextFormatting.GOLD, "Shiny charm left " + time),0,60,0);
			  play.connection.sendPacket(packet);
		  }
	 } 
	 @SubscribeEvent
	    public void shinyCharmEffect(PlayerTickEvent e) {
		  if(tick != 40*CHECK_TIME){
		      tick++;
		      return;
		  }
		  tick = 0;
		  //long begin = System.nanoTime(); //Performances check
		  if(!FileHandler.config.getActive()) {
			  EntityPlayer player = (EntityPlayer) e.player;
			  if(hasShinyCharm(player) && hasShinyCharmActive(player)) {
				  unsetShinyCharm(player);
			  }
			  
		  } else {
			  EntityPlayer player = (EntityPlayer) e.player;
			  IShinyCharmTemp shinyCharmTemp = player.getCapability(ShinyCharmTempProvider.SHINYCHARMTEMP_CAP, EnumFacing.UP);
			  int currentTime = shinyCharmTemp.getTime();
			  if(currentTime>0){
				  if(!hasShinyCharm(e.player)) {
					  setShinyCharmAvavilable(e.player);
					  setShinyCharmActive(e.player);
				  }
				  int newtime = currentTime - CHECK_TIME;
				  if( newtime <= 0) {
					  shinyCharmTemp.setTime(-1);
				  }  else {
					  shinyCharmTemp.setTime(newtime);
					  EntityPlayerMP play = (EntityPlayerMP)player;
					  int min = newtime/60;
					  int sec = newtime%60; 
					  String  time;
					  
					  if( min > 1 ) {
						  if(sec < 10) time = min + "m0" + sec ;
						  else time = min + "m" + sec ;
					  } else if( min == 1) {
						  if(sec>10) time = min + "m" + sec ;
						  else if(sec>1) time = min + "m0" + sec ;
						  else time = "60" + sec ;
					  }  else time = sec + " sec";
						  
					  SPacketTitle packet = new SPacketTitle (Type.ACTIONBAR,format(net.minecraft.util.text.TextFormatting.GOLD, "Shiny charm left " + time ),0,60,0);
					  play.connection.sendPacket(packet);
				  }
				  
			  } else if(shinyCharmTemp.getTime() == -1){
				  shinyCharmTemp.setTime(0);
				  unsetShinyCharm(player);
				  player.sendMessage(format(net.minecraft.util.text.TextFormatting.GRAY, "Your Shiny Charm has just expired"));
				  EntityPlayerMP play = (EntityPlayerMP)player;
				  SPacketTitle packet = new SPacketTitle (Type.ACTIONBAR,format(net.minecraft.util.text.TextFormatting.GRAY, "Your Shiny Charm has just expired"),0,60,0);
				  play.connection.sendPacket(packet);
			  }
			}
		  //long end = System.nanoTime();
		  //e.player.sendMessage(format(net.minecraft.util.text.TextFormatting.GRAY, Long.toString((long)end-begin)));
			 
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

	 	 
	 private TextComponentTranslation format(TextFormatting color, String str, Object... args){
	        TextComponentTranslation ret = new TextComponentTranslation(str, args);
	        ret.getStyle().setColor(color);
	        return ret;
	 }
	 
	 

	 
}
package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.storage.IStorageManager;
import com.pixelmonmod.pixelmon.enums.EnumFeatureState;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class Shinycharm implements ICommand {
	
	String cmdname = "wololo";
	@Override
	public int compareTo(ICommand arg0) {
		return 0;
	}

	@Override
	public String getName() {
		return cmdname;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/" + cmdname + " <player>";
	}

	@Override
	public List<String> getAliases() {
		List<String> aliases = Lists.<String>newArrayList();
		aliases.add("/"+cmdname);
		return aliases;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer player;
		if(args.length < 2) {
			sender.sendMessage(format(net.minecraft.util.text.TextFormatting.RED,"Syntaxe : /" + cmdname +" <player> <check|active|disable> [time]" ));
		} else {
			if(args[0].length()==36) {
				if ((server.getPlayerList().getPlayerByUUID(UUID.fromString(args[0])) instanceof EntityPlayer)) {
					player = server.getPlayerList().getPlayerByUUID(UUID.fromString(args[0]));
				} 
				else {
					return;
				}
			} else {
				List<String> list = new ArrayList<String>();
				list = Arrays.asList(server.getPlayerList().getOnlinePlayerNames());
				ListIterator<String> iterator = list.listIterator();
				while(iterator.hasNext()) {
					iterator.set(iterator.next().toLowerCase());
				}
				if(list.contains(args[0].toLowerCase())) {
					if ((server.getPlayerList().getPlayerByUsername(args[0]) instanceof EntityPlayer)) {
						player = server.getPlayerList().getPlayerByUsername(args[0]);
					}
					else {
						return;
					}
					
				}
				else {
					sender.sendMessage((format(net.minecraft.util.text.TextFormatting.RED, "Player "+'"'+args[0]+'"'+" not found")));
					return;
				}
			}
			
			if(args[1].equals("check")) {
				if(player.getEntityData().hasKey("tempshinycharm") && player.getEntityData().getBoolean("tempshinycharm")){
					int timeleft = player.getEntityData().getInteger("timeshinycharm");
					if( timeleft < 8 ) {
						sender.sendMessage(format(net.minecraft.util.text.TextFormatting.GREEN, "Player " + player.getName() + " only has a few seconds left of Shiny Charm"));
					} else {
						sender.sendMessage(format(net.minecraft.util.text.TextFormatting.GREEN, "Player " + player.getName() + " has " + timeleft + " seconds of Shiny Charm left"));
					}
				} else {
					sender.sendMessage(format(net.minecraft.util.text.TextFormatting.RED, "Player " + player.getName() + " has no Shiny Charm active"));
				}
			}
			if(args[1].equals("active")) {
				if(args.length != 3) {
					sender.sendMessage(format(net.minecraft.util.text.TextFormatting.RED,"Syntaxe : /" + cmdname + " " + player.getName() + " active <time>" ));
				} else {
					if(isInteger(args[2])) {
						int newtime = player.getEntityData().getInteger("timeshinycharm") + Integer.parseInt(args[2]);
						player.getEntityData().setBoolean("tempshinycharm", true);
						player.getEntityData().setInteger("timeshinycharm", newtime);
						sender.sendMessage(format(net.minecraft.util.text.TextFormatting.GREEN, "Player " + player.getName() + " has now " +  newtime + " seconds of Shiny Charm"));
					} else {
						sender.sendMessage(format(net.minecraft.util.text.TextFormatting.RED,"Syntaxe : /"+cmdname+" " + player.getName() + " active <time>" ));
						sender.sendMessage(format(net.minecraft.util.text.TextFormatting.RED,"Use seconds for time, example : /" +cmdname+ " " + player.getName() + " active 300" ));
					}
				}
			}
			if(args[1].equals("disable")) {
				if(player.getEntityData().hasKey("tempshinycharm") && player.getEntityData().getBoolean("tempshinycharm")){
					player.getEntityData().setBoolean("tempshinycharm", false);
					player.getEntityData().setInteger("timeshinycharm", 0);
					sender.sendMessage(format(net.minecraft.util.text.TextFormatting.RED, "Player " + player.getName() + " Shiny Charm no longer active"));
					player.sendMessage(format(net.minecraft.util.text.TextFormatting.GRAY, "Your Shiny Charm has been expired by an Operator"));
				} else {
					sender.sendMessage(format(net.minecraft.util.text.TextFormatting.RED, "Player " + player.getName() + " has no Shiny Charm active"));
				}
			}
		}
		
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		List<String> autocompleteList = new ArrayList<String>();
		if ( args.length == 1 ) {
			List<String> list = Arrays.asList(server.getPlayerList().getOnlinePlayerNames());
			autocompleteList = new ArrayList<String>();
			for( String e : list) {
				if(e.toLowerCase().indexOf(args[0].toLowerCase())==0) {
					autocompleteList.add(e);
				}
			}
		}
		if( args.length == 2 ) {
			List<String> list = new ArrayList<String>();
			list.add("active");
			list.add("check");
			list.add("disable");
			autocompleteList = new ArrayList<String>();
			for( String e : list) {
				if(e.toLowerCase().indexOf(args[1].toLowerCase())==0) {
					autocompleteList.add(e);
				}
			}
		}
		return autocompleteList;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		if (args.length > 0) {
			return true;
		}
		return false;
	}
	
	private TextComponentTranslation format(TextFormatting color, String str, Object... args)
    {
        TextComponentTranslation ret = new TextComponentTranslation(str, args);
        ret.getStyle().setColor(color);
        return ret;
    }
	
	public static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
	
}
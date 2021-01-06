package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.storage.IStorageManager;
import com.pixelmonmod.pixelmon.enums.EnumMegaItem;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import shinycharm.Main;

public class Removemegaring implements ICommand {
	
	
	@Override
	public int compareTo(ICommand arg0) {
		return 0;
	}

	@Override
	public String getName() {
		return "removemegaring";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/removemegaring <player>";
	}

	@Override
	public List<String> getAliases() {
		List<String> aliases = Lists.<String>newArrayList();
		aliases.add("/removemegaring");
		return aliases;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer player;
		IStorageManager storage = Pixelmon.storageManager;
		
		if (args.length == 0) {
			if (sender instanceof EntityPlayer) {
				player = (EntityPlayer) sender;
			} 
			else {
				return;
			}
			
		} 
		else {
			if(args[0].length()==36) {
				if ((server.getPlayerList().getPlayerByUUID(UUID.fromString(args[0])) instanceof EntityPlayer)) {
					player = server.getPlayerList().getPlayerByUUID(UUID.fromString(args[0]));
				} 
				else {
					return;
				}
			}
			else {
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
			
		}

		PlayerPartyStorage playerData = storage.getParty(player.getUniqueID());
		//EnumMegaItem shinyCharmData = playerData.getMegaItem();
		if (playerData.canEquipMegaItem()) {
			sender.sendMessage(format(net.minecraft.util.text.TextFormatting.GREEN, "Woosh ! no more mega ring for "+player.getName()));
			playerData.setMegaItem(EnumMegaItem.Disabled, false);
		}
		else {
			sender.sendMessage(format(net.minecraft.util.text.TextFormatting.RED, "Player "+player.getName()+" doesn't have mega ring"));
		}
		
	}

	@Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return PermissionUtils.canUse(Main.MODID + ".removemegaring", sender);
    }

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		List<String> autocompleteList = new ArrayList<String>();
		if (args.length == 1 ) {
			List<String> list = Arrays.asList(server.getPlayerList().getOnlinePlayerNames());
			autocompleteList = new ArrayList<String>();
			for( String e : list) {
				if(e.toLowerCase().indexOf(args[0].toLowerCase())==0) {
					autocompleteList.add(e);
				}
			}
		}		
		return autocompleteList;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		if (args.length>0) {
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
	
}
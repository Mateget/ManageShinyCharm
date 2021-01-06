// https://github.com/Licious17/Eggcellent/blob/master/src/main/java/pt/licious/eggcellent/commands/ReloadCommand.java

package commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import shinycharm.FileHandler;
import shinycharm.Main;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Charmreload extends CommandBase {

    @Override
    public String getName() {
        return "charmreload";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "&cUsage: /charmreload";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0 ) {
            FileHandler.readConfig();
            this.send(sender, "&aYou have reloaded the configuration files");
            return;
        }
        this.send(sender, getUsage(sender));
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> possibleArgs = new ArrayList<>();
        return getListOfStringsMatchingLastWord(args, possibleArgs);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return PermissionUtils.canUse(Main.MODID + ".charmreload", sender);
    }

    private void send(ICommandSender sender, String message) {
        sender.sendMessage(new TextComponentString(( "&f[&l&6" + Main.NAME + "&f] " + message).replaceAll("&([0-9a-fk-or])","\u00a7$1")));
    }


}

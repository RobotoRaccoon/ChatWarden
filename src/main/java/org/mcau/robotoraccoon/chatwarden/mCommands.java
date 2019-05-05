package org.mcau.robotoraccoon.chatwarden;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class mCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("chatwarden") || cmd.getName().equalsIgnoreCase("cw")) {

            String commandPrefix = ChatColor.DARK_PURPLE + "[CW] ";
            String commandError = commandPrefix + ChatColor.DARK_RED + "Error: " + ChatColor.RED;
            String commandUnknown = commandError + "/" + cmd.getName().toLowerCase() + " <reload>";

            if (!sender.hasPermission("chatwarden.commands.staff")) {

                sender.sendMessage(commandError + "You do not have permission to run this command.");
            } else if (args.length < 1) {

                sender.sendMessage(commandUnknown);
            } else if (args[0].equalsIgnoreCase("RELOAD")) {

                mConfig.loadConfigs();
                sender.sendMessage(commandPrefix + ChatColor.GREEN + "Successfully reloaded the config file!");
            } else {

                sender.sendMessage(commandUnknown);
            }
        }

        return true;

    }

}

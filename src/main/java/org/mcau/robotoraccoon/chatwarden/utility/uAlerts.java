package org.mcau.robotoraccoon.chatwarden.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.chatwarden.mConfig;
import org.mcau.robotoraccoon.chatwarden.mMain;

public class uAlerts {

    public static void notifyStaff( String msg ) {

        // Do nothing if notify-staff is not enabled globally.
        if( !mMain.getPlugin().getConfig().getBoolean("global.notify-staff") ) {
            return;
        }

        // Loop through all users; check for staff permission; send the message.
        for( Player user: Bukkit.getServer().getOnlinePlayers() ) {
            if( user.hasPermission("chatwarden.staff") ) {
                user.sendMessage( ChatColor.translateAlternateColorCodes('&',msg) );
            }
        }

    }

    // Anyone have an idea on how to make the processing of macro tokens easier?

    public static void alertCase( Player player, String msg ) {

        // Console.
        String msgConsole = mConfig.getLanguageConfig().getString("case.filter.console");
        msgConsole = msgConsole.replace("%PLAYER%", player.getName());
        msgConsole = msgConsole.replace("%MESSAGE%", msg);

        mMain.getPlugin().getLogger().info(ChatColor.translateAlternateColorCodes('&', msgConsole));

        // Local.
        String msgLocal = mConfig.getLanguageConfig().getString("case.filter.local");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msgLocal));

        // Staff.
        if( mMain.getPlugin().getConfig().getBoolean("case.notify-staff") ) {
            String msgStaff = mConfig.getLanguageConfig().getString("case.filter.staff");
            msgStaff = msgStaff.replace("%PLAYER%", player.getName());
            msgStaff = msgStaff.replace("%MESSAGE%", msg);

            notifyStaff(msgStaff);
        }

    }

    public static void alertSpam(Player player, String msg) {

        // Console.
        String msgConsole = mConfig.getLanguageConfig().getString("spam.cancel.console");
        msgConsole = msgConsole.replace("%PLAYER%", player.getName());
        msgConsole = msgConsole.replace("%MESSAGE%", msg);

        mMain.getPlugin().getLogger().info(ChatColor.translateAlternateColorCodes('&', msgConsole));

        // Local.
        String msgLocal = mConfig.getLanguageConfig().getString("spam.cancel.local");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msgLocal));

        // Staff.
        if( mMain.getPlugin().getConfig().getBoolean("spam.notify-staff") ) {
            String msgStaff = mConfig.getLanguageConfig().getString("spam.cancel.staff");
            msgStaff = msgStaff.replace("%PLAYER%", player.getName());
            msgStaff = msgStaff.replace("%MESSAGE%", msg);

            notifyStaff(msgStaff);
        }

    }

    public static void alertSwear( Player player, String msg ) {

        // Console.
        String msgConsole = mConfig.getLanguageConfig().getString("swear.kick.console");
        msgConsole = msgConsole.replace("%PLAYER%", player.getName());
        msgConsole = msgConsole.replace("%MESSAGE%", msg);

        mMain.getPlugin().getLogger().info(ChatColor.translateAlternateColorCodes('&', msgConsole));

        // Local.
        //String msgLocal = mConfig.getLanguageConfig().getString("swear.kick.local");
        //player.sendMessage(ChatColor.translateAlternateColorCodes('&', msgLocal));

        // Staff.
        if( mMain.getPlugin().getConfig().getBoolean("swear.notify-staff") ) {
            String msgStaff = mConfig.getLanguageConfig().getString("swear.kick.staff");
            msgStaff = msgStaff.replace("%PLAYER%", player.getName());
            msgStaff = msgStaff.replace("%MESSAGE%", msg);

            notifyStaff(msgStaff);
        }

    }

}

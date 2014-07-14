package org.mcau.robotoraccoon.chatwarden.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.chatwarden.mConfig;
import org.mcau.robotoraccoon.chatwarden.mMain;

import java.util.ArrayList;
import java.util.Map;

public class uSwear {

    // Arraylist for autokick regex
    public static ArrayList< Map<String, String> > autokickRegexList = new ArrayList<>();

    public static Boolean checkSwear(final Player player, String chat) {

        // Go back if staff have bypass on.
        if( player.hasPermission("chatwarden.staff") && mConfig.getConfig().getBoolean("swear.bypass-staff") ) {
            return false;
        }

        for(Map currentRegexList : autokickRegexList) {
            String currentRegex = (String) currentRegexList.get("regex");
            String currentReason = (String) currentRegexList.get("reason");

            if( chat.matches(currentRegex) ) {

                final String kickReason = ChatColor.translateAlternateColorCodes('&',
                        mConfig.getLanguageConfig().getString("swear.kick.local").replace("%REASON%", currentReason));
                Bukkit.getScheduler().runTask(mMain.getPlugin(), new Runnable() {
                    public void run() {
                        player.kickPlayer(kickReason);
                    }
                });
                return true;

            }
        }

        return false;

    }

}

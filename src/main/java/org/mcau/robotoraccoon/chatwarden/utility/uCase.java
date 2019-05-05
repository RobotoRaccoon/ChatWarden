package org.mcau.robotoraccoon.chatwarden.utility;

import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.chatwarden.mConfig;
import org.mcau.robotoraccoon.chatwarden.mMain;

import java.util.List;

public class uCase {

    public static Boolean checkCase(Player player, String chat) {

        // Go back if staff have bypass on.
        if (player.hasPermission("chatwarden.staff") && mConfig.getConfig().getBoolean("case.bypass-staff")) {
            return false;
        }

        // Settings from the config.yml
        List<?> ignoredWords = mMain.getPlugin().getConfig().getList("case.ignored-words");
        Integer minChars = mMain.getPlugin().getConfig().getInt("case.min-chars");
        Double minRatio = mMain.getPlugin().getConfig().getDouble("case.min-ratio");

        // Extra variables
        Integer caseCount = 0;
        String caseString = chat;

        // Strip out ignored words, spaces, and some punctuation.
        for (int i = 0; i < ignoredWords.size(); i++) {
            caseString = caseString.replaceAll((String) ignoredWords.get(i), "");
        }
        caseString = caseString.replaceAll("( |\\.)", "");

        // Find the total count of "yelling" in the string.
        for (int i = 0; i < caseString.length(); i++) {

            if (caseString.charAt(i) == '!') {
                caseCount++;
            } else for (char c = 'A'; c <= 'Z'; c++) {
                if (caseString.charAt(i) == c) {
                    caseCount++;
                }
            }
        }

        // Filter when caseCount is higher than the ratio in the config.yml
        return (caseString.length() > minChars) && (caseCount > caseString.length() * minRatio);

    }

}

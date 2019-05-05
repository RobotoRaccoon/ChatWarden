package org.mcau.robotoraccoon.chatwarden.utility;

import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.chatwarden.mConfig;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.getLevenshteinDistance;

public class uSpam {

    // Map for people who say the same lines repeatedly.
    private static Map<String, Map<String, String>> chatRepeats = new HashMap<>();

    public static Boolean checkSpam(Player player, String chat) {

        // Go back if staff have bypass on.
        if (player.hasPermission("chatwarden.staff") && mConfig.getConfig().getBoolean("spam.bypass-staff")) {
            return false;
        }

        // Repeated characters.
        if (chat.matches(".*(\\D.?.?)\\1{6,}.*")) {
            return true;
        }

        // Levenshtein distance checking.
        if (!chatRepeats.containsKey(player.getName())) {
            chatRepeats.put(player.getName(), new HashMap<String, String>());
        }

        if (!chatRepeats.get(player.getName()).containsKey("last") || !chatRepeats.get(player.getName()).containsKey("count")) {
            chatRepeats.get(player.getName()).put("last", "");
            chatRepeats.get(player.getName()).put("count", "0");
        }

        String chatLast = chatRepeats.get(player.getName()).get("last");

        if (chatLast == null || chatLast.isEmpty()) {
            chatLast = "";
        }

        Integer allowedDistance = Math.min(mConfig.getConfig().getInt("spam.repeat-distance"), chat.length());
        Integer chatDistance = getLevenshteinDistance(chat, chatLast);

        if (chatDistance >= allowedDistance) {
            chatRepeats.get(player.getName()).put("count", "0");
            chatRepeats.get(player.getName()).put("last", chat);
        } else {

            // Increase the count by one and update the last message.
            Integer repeatCount = Integer.valueOf(chatRepeats.get(player.getName()).get("count"));
            repeatCount++;
            chatRepeats.get(player.getName()).put("count", repeatCount.toString());
            chatRepeats.get(player.getName()).put("last", chat);

            return repeatCount >= mConfig.getConfig().getInt("spam.repeat-count");

        }

        return false;

    }

}

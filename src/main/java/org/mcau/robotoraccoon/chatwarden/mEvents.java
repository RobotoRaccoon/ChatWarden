package org.mcau.robotoraccoon.chatwarden;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mcau.robotoraccoon.chatwarden.utility.uAlerts;
import org.mcau.robotoraccoon.chatwarden.utility.uCase;
import org.mcau.robotoraccoon.chatwarden.utility.uSpam;
import org.mcau.robotoraccoon.chatwarden.utility.uSwear;

public class mEvents implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        // Do nothing if not enabled globally.
        if( !mConfig.getConfig().getBoolean("global.enabled") ) {
            return;
        }

        Player player = event.getPlayer();
        String newMessage = event.getMessage();

        // Do nothing if staff have global bypass.
        if( player.hasPermission("chatwarden.staff") && mConfig.getConfig().getBoolean("global.bypass-staff") ) {
            return;
        }

        // Do nothing if player isn't being filtered.
        if( !player.hasPermission("chatwarden.player") ) {
            return;
        }

        // Check swear if enabled.
        if( mConfig.getConfig().getBoolean("swear.enabled") ) {

            if( uSwear.checkSwear(player, newMessage) ) {

                uAlerts.alertSwear(player, newMessage);
                event.setCancelled(true);
                return;
            }

        }

        // Check spam if enabled.
        if( mConfig.getConfig().getBoolean("spam.enabled") ) {

            if( uSpam.checkSpam(player, newMessage) ) {

                uAlerts.alertSpam(player, newMessage);
                event.setCancelled(true);
                return;
            }

        }

        // Check case if enabled.
        if( mConfig.getConfig().getBoolean("case.enabled") ) {

            if( uCase.checkCase(player, newMessage) ) {

                uAlerts.alertCase(player, newMessage);
                newMessage = newMessage.substring(0, 1).toUpperCase() + newMessage.substring(1).toLowerCase();
            }

        }

        event.setMessage(newMessage);

    }

}

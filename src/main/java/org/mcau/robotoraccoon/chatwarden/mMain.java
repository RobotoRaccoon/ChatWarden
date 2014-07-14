package org.mcau.robotoraccoon.chatwarden;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class mMain extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        // Events
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new mEvents(), this);

        // Commands
        getCommand( "chatwarden" ).setExecutor( new mCommands() );
        getCommand( "cw"         ).setExecutor( new mCommands() );

        // Config + Json
        new mConfig();
        mConfig.createAllFiles();

    }

    @Override
    public void onDisable() {

    }

    public static Plugin getPlugin() {
        return plugin;
    }

}

package org.mcau.robotoraccoon.chatwarden;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.mcau.robotoraccoon.chatwarden.utility.uParseJson;

import java.io.File;
import java.io.IOException;

public class mConfig {

    private static File configFile;
    private static File languageFile;

    private static FileConfiguration config;
    private static FileConfiguration languageConfig;

    public static FileConfiguration getConfig(){ return config; }
    public static FileConfiguration getLanguageConfig(){ return languageConfig; }

    public mConfig(){

        configFile = new File( mMain.getPlugin().getDataFolder(), "config.yml" );
        languageFile = new File( mMain.getPlugin().getDataFolder(), "lang.yml" );

        config = YamlConfiguration.loadConfiguration(configFile);
        languageConfig = YamlConfiguration.loadConfiguration( languageFile );

    }

    public static void createAllFiles(){

        if( !configFile.exists() ) {
            mMain.getPlugin().saveResource("config.yml", true);
        }
        if( !languageFile.exists() ) {
            mMain.getPlugin().saveResource("lang.yml", true);
        }

        loadConfigs();

    }

    public static boolean loadConfigs(){

        try{

            config = YamlConfiguration.loadConfiguration( new File( mMain.getPlugin().getDataFolder(), "config.yml" ) );
            languageConfig = YamlConfiguration.loadConfiguration( new File( mMain.getPlugin().getDataFolder(), "lang.yml" ) );

            mMain.getPlugin().reloadConfig();

            // Autokick Json
            uParseJson.main();

            return true;

        } catch (Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public static void saveConfigs(){

        try {

            config.save( configFile );
            languageConfig.save( languageFile );

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}

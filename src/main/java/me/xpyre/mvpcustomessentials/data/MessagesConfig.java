package me.xpyre.mvpcustomessentials.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfig {
    private static File file;
    private static YamlConfiguration config;

    public static void setupConfig(){
        file = new File(Bukkit.getPluginManager().getPlugin("MVPCustomEssentials").getDataFolder().toString() + "messages.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                config.addDefault("vanish-message", "&l&aYou have been vanished!");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public static YamlConfiguration getConfig(){
        return config;
    }
    public static void reloadConfig(){
        config = YamlConfiguration.loadConfiguration(file);
    }
}

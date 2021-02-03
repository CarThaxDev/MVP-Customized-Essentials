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
                config.addDefault("vanish-on-message", "&l&aYou have been vanished!");
                config.addDefault("vanish-off-message", "&l&cYou have been un-vanished!");
                config.addDefault("inventory-clear-message-self", "&l&aYou have cleared your own inventory.");
                config.addDefault("inventory-clear-message-sender", "&l&aYou have successfully cleared [RECEIVER]'s inventory.");
                config.addDefault("inventory-clear-message-receiver", "&l&a[SENDER] Has cleared your inventory.");
                config.addDefault("inventory-clear-message-sender-all", "&l&aYou have successfully cleared everyone's inventory!");
                config.addDefault("god-message-sender", "&l&aYou have godded [RECIEVER].");
                config.addDefault("god-message-receiver", "&l&aYou have been godded by [SENDER].");
                config.addDefault("god-message-sender-all", "&l&aYou successfully godded everyone.");
                config.options().copyDefaults(true);
                config.save(file);

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
    public static void saveConfig(){
        try {
            config.save(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

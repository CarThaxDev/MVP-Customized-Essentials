package me.xpyre.mvpcustomessentials;

import me.xpyre.mvpcustomessentials.commands.MessageCommand;
import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Messages Config Registration
        MessagesConfig.setupConfig();
        //Regular Config Registration
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        //Command Registration
        getCommand("msg").setExecutor(new MessageCommand());
        //Event Registration
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

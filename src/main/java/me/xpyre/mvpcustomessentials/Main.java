package me.xpyre.mvpcustomessentials;

import me.xpyre.mvpcustomessentials.commands.MessageCommand;
import me.xpyre.mvpcustomessentials.commands.chat.MuteChat;
import me.xpyre.mvpcustomessentials.commands.chat.UnmuteChat;
import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import me.xpyre.mvpcustomessentials.events.PlayerChatEventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class Main extends JavaPlugin {

    public static boolean isChatMuted;

    @Override
    public void onEnable() {
        // Messages Config Registration
        MessagesConfig.setupConfig();
        //Regular Config Registration
        isChatMuted = false;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        //Command Registration
        getCommand("msg").setExecutor(new MessageCommand());
        getCommand("mutechat").setExecutor(new MuteChat());
        getCommand("unmutechat").setExecutor(new UnmuteChat());
        //Event Registration
        getServer().getPluginManager().registerEvents(new PlayerChatEventHandler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static boolean getChatMutedState(){
        return isChatMuted;
    }
    public static boolean setChatMuted(Boolean newMutedState){
        try{
            isChatMuted = newMutedState;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

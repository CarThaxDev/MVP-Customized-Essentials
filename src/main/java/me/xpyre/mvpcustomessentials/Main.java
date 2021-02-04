package me.xpyre.mvpcustomessentials;

import me.xpyre.mvpcustomessentials.commands.chat.ClearChatCommand;
import me.xpyre.mvpcustomessentials.commands.chat.MessageCommand;
import me.xpyre.mvpcustomessentials.commands.chat.MuteChat;
import me.xpyre.mvpcustomessentials.commands.chat.UnmuteChat;
import me.xpyre.mvpcustomessentials.commands.essentials.FlyCommand;
import me.xpyre.mvpcustomessentials.commands.essentials.SmiteCommand;
import me.xpyre.mvpcustomessentials.commands.essentials.VanishCommand;
import me.xpyre.mvpcustomessentials.commands.inventory.InvseeCommand;
import me.xpyre.mvpcustomessentials.commands.inventory.RefillInventoryCommand;
import me.xpyre.mvpcustomessentials.commands.moderation.FreezePlayer;
import me.xpyre.mvpcustomessentials.commands.moderation.UnfreezePlayer;
import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import me.xpyre.mvpcustomessentials.events.PlayerChatEventHandler;
import org.bukkit.plugin.java.JavaPlugin;

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
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("freeze").setExecutor(new FreezePlayer());
        getCommand("unfreeze").setExecutor(new UnfreezePlayer());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("smite").setExecutor(new SmiteCommand());
        getCommand("refill").setExecutor(new RefillInventoryCommand());
        //Event Registration
        getServer().getPluginManager().registerEvents(new PlayerChatEventHandler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MessagesConfig.saveConfig();
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

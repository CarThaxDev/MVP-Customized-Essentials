package me.xpyre.mvpcustomessentials;

import me.xpyre.mvpcustomessentials.commands.chat.ClearChatCommand;
import me.xpyre.mvpcustomessentials.commands.chat.MessageCommand;
import me.xpyre.mvpcustomessentials.commands.chat.MuteChat;
import me.xpyre.mvpcustomessentials.commands.chat.UnmuteChat;
import me.xpyre.mvpcustomessentials.commands.essentials.*;
import me.xpyre.mvpcustomessentials.commands.gamemode.*;
import me.xpyre.mvpcustomessentials.commands.inventory.ClearInventoryCommand;
import me.xpyre.mvpcustomessentials.commands.inventory.InvseeCommand;
import me.xpyre.mvpcustomessentials.commands.inventory.RefillInventoryCommand;
import me.xpyre.mvpcustomessentials.commands.moderation.FreezePlayer;
import me.xpyre.mvpcustomessentials.commands.moderation.PunishCommand;
import me.xpyre.mvpcustomessentials.commands.moderation.UnfreezePlayer;
import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import me.xpyre.mvpcustomessentials.events.PlayerChatEventHandler;
import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Main extends JavaPlugin {

    public static boolean isChatMuted;
    public static Main instance;
    public static Connection con;

    @Override
    public void onEnable() {
        instance = this;
        isChatMuted = false;
        //Regular Config Registration
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        //Messages Config Registration
        MessagesConfig.setupConfig();
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
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("gamemodecreative").setExecutor(new GamemodeCreative());
        getCommand("gamemodesurvival").setExecutor(new GamemodeSurvival());
        getCommand("gamemodeadventure").setExecutor(new GamemodeAdventure());
        getCommand("gamemodespectator").setExecutor(new GamemodeSpectator());
        getCommand("clearinventory").setExecutor(new ClearInventoryCommand());
        getCommand("god").setExecutor(new GodCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("punish").setExecutor(new PunishCommand());
        //Event Registration
        getServer().getPluginManager().registerEvents(new PlayerChatEventHandler(), this);
        try {
            con = DriverManager.getConnection("jdbc:mysql:" + getConfig().getString("Database-URL") + "?user=" + getConfig().getString("Database-User") + "&password=" + getConfig().getString("Databse-Password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Util.setCon(con);
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
    public static Main getInstance(){
        return instance;
    }
}

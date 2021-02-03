package me.xpyre.mvpcustomessentials.commands.essentials;

import me.xpyre.mvpcustomessentials.Main;
import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    Main plugin;
    public VanishCommand(Main main){
        plugin = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length >= 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    if (!player.hasPermission("m.vanish.use")) {
                        player.sendMessage(ChatColor.RED + "You do not have the required permissions to use this command!");
                    } else {
                        for (Player player1 : Bukkit.getOnlinePlayers()) {
                            if (!player1.hasPermission("m.vanish.see")) {
                                player1.hidePlayer(plugin, player);
                            }
                        }
                        player.setPlayerListName(player.getDisplayName() + "*");
                        player.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("vanish-on-message")));
                    }
                }else if(args[0].equalsIgnoreCase("off")){
                    if (!player.hasPermission("m.vanish.use")) {
                        player.sendMessage(ChatColor.RED + "You do not have the required permissions to use this command!");
                    } else {
                        for (Player player1 : Bukkit.getOnlinePlayers()) {
                            if (!player1.hasPermission("m.vanish.see")) {
                                player1.showPlayer(plugin, player);
                            }
                        }
                        player.setPlayerListName(player.getDisplayName());
                        player.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("vanish-off-message")));
                    }
                }
            }
        }else{
            sender.sendMessage(ChatColor.RED + "You MUST be a player to run this command!");
        }
        return true;
    }
}

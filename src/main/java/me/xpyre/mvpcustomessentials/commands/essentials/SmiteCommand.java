package me.xpyre.mvpcustomessentials.commands.essentials;

import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SmiteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("m.smite")){
                if(args.length < 1){
                    player.sendMessage("You must provide a player to smite!");
                }else if(args[0].equals("*")){
                    for(Player player1 : Bukkit.getOnlinePlayers()){
                        player1.getWorld().spawnEntity(player1.getLocation(), EntityType.LIGHTNING);
                        player1.sendMessage(MessagesConfig.getConfig().getString("smite-message-receiver").replace("[SENDER]", player.getName()).replace("[RECEIVER]", player1.getName()));
                    }
                    player.sendMessage(MessagesConfig.getConfig().getString("smite-message-sender-all").replace("[SENDER]", player.getName()).replace("[RECEIVER]", "Everyone"));
                }else{
                    Player player1 = Bukkit.getPlayer(args[0]);
                    if(player1 != null){
                        player1.getWorld().spawnEntity(player1.getLocation(), EntityType.LIGHTNING);
                        player1.sendMessage(MessagesConfig.getConfig().getString("smite-message-receiver").replace("[SENDER]", player.getName()).replace("[RECEIVER]", player1.getName()));
                        player.sendMessage(MessagesConfig.getConfig().getString("smite-message-sender").replace("[SENDER]", player.getName()).replace("[RECEIVER]", player1.getName()));
                    }else{
                        player.sendMessage(ChatColor.RED + "That player does not exist!");
                    }
                }
            }
        }else{
            if(args.length < 1){
                sender.sendMessage("You must provide a player to smite!");
            }else if(args[0].equals("*")){
                for(Player player1 : Bukkit.getOnlinePlayers()){
                    player1.getWorld().spawnEntity(player1.getLocation(), EntityType.LIGHTNING);
                    player1.sendMessage(MessagesConfig.getConfig().getString("smite-message-receiver").replace("[SENDER]", "Console").replace("[RECEIVER]", player1.getName()));
                }
                sender.sendMessage(MessagesConfig.getConfig().getString("smite-message-sender-all").replace("[SENDER]", "Console").replace("[RECEIVER]", "Everyone"));
            }else{
                Player player1 = Bukkit.getPlayer(args[0]);
                if(player1 != null){
                    player1.getWorld().spawnEntity(player1.getLocation(), EntityType.LIGHTNING);
                    player1.sendMessage(MessagesConfig.getConfig().getString("smite-message-receiver").replace("[SENDER]", "Console").replace("[RECEIVER]", player1.getName()));
                    sender.sendMessage(MessagesConfig.getConfig().getString("smite-message-sender").replace("[SENDER]", "Console").replace("[RECEIVER]", player1.getName()));
                }else{
                    sender.sendMessage(ChatColor.RED + "That player does not exist!");
                }
            }

        }
        return true;
    }
}

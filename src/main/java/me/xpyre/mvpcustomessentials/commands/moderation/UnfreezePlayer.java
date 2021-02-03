package me.xpyre.mvpcustomessentials.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnfreezePlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("m.unfreeze")){
                player.sendMessage(ChatColor.RED + "You do not have the required permissions to use this command!");
            }else{
                if(args.length >= 1){
                    Player playerToFreeze = Bukkit.getPlayer(args[0]);
                    if(playerToFreeze != null) {
                        playerToFreeze.setWalkSpeed(0.2f);
                    }else{
                        player.sendMessage(ChatColor.RED + "That player does not exist!");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "You need to provide a player to freeze!");
                }
            }
        }else{
            if(args.length >= 1){
                Player playerToFreeze = Bukkit.getPlayer(args[0]);
                if(playerToFreeze != null) {
                    playerToFreeze.setWalkSpeed(0.2f);
                }else{
                    sender.sendMessage(ChatColor.RED + "That player does not exist!");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "You need to provide a player to freeze!");
            }
        }
        return true;
    }
}

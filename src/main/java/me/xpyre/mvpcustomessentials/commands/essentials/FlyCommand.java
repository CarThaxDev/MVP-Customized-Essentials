package me.xpyre.mvpcustomessentials.commands.essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("m.fly")){
                player.sendMessage(ChatColor.RED + "You do not have the required permissions to use this command!");
            }else{
                if(args.length < 1){
                    player.setAllowFlight(!player.getAllowFlight());
                }else{
                    Player player1 = Bukkit.getPlayer(args[0]);
                    if(player1 == null){
                        player.sendMessage(ChatColor.RED + "That player does not exist!");
                    }else{
                        player1.setAllowFlight(!player1.getAllowFlight());
                    }
                }
            }
        }else{
            if(args.length < 1){
                sender.sendMessage(ChatColor.RED + "You MUST provide a player.");
            }else{
                Player player1 = Bukkit.getPlayer(args[0]);
                if(player1 == null){
                    sender.sendMessage(ChatColor.RED + "That player does not exist!");
                }else{
                    player1.setAllowFlight(!player1.getAllowFlight());
                }
            }
        }
        return true;
    }
}

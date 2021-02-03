package me.xpyre.mvpcustomessentials.commands.essentials;

import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("m.god")){
                if(args.length < 1){
                    player.setInvulnerable(!player.isInvulnerable());
                }else if(args[0].equals("*")){
                    for(Player player1 : Bukkit.getOnlinePlayers()){
                        player1.setInvulnerable(!player1.isInvulnerable());
                        player1.sendMessage(MessagesConfig.getConfig().getString("god-message-receiver").replace("[SENDER]", player.getName()).replace("[RECEIVER]", player1.getName()));
                    }
                    player.sendMessage(MessagesConfig.getConfig().getString("god-message-sender-all").replace("[SENDER]", player.getName()).replace("[RECEIVER]", "Everyone"));
                }else{
                    Player player1 = Bukkit.getPlayer(args[0]);
                    player1.setInvulnerable(!player1.isInvulnerable());
                    player1.sendMessage(MessagesConfig.getConfig().getString("god-message-receiver").replace("[SENDER]", player.getName()).replace("[RECEIVER]", player1.getName()));
                    player.sendMessage(MessagesConfig.getConfig().getString("god-message-sender").replace("[SENDER]", player.getName()).replace("[RECEIVER]", player1.getName()));
                }
            }else{
                player.sendMessage(ChatColor.RED + "You do not have the required permission to run this command!");
            }
        }else{
            if(args.length < 1){
                sender.sendMessage(ChatColor.RED + "You must provide a player!");
            }else if(args[0].equals("*")){
                for(Player player1 : Bukkit.getOnlinePlayers()){
                    player1.setInvulnerable(!player1.isInvulnerable());
                }
            }else{
                Player player1 = Bukkit.getPlayer(args[0]);
                player1.setInvulnerable(!player1.isInvulnerable());
                player1.sendMessage(MessagesConfig.getConfig().getString("god-message-receiver").replace("[SENDER]", "Console").replace("[RECEIVER]", player1.getName()));
                sender.sendMessage(MessagesConfig.getConfig().getString("god-message-sender").replace("[SENDER]", "Console").replace("[RECEIVER]", player1.getName()));
            }
        }
        return true;
    }
}

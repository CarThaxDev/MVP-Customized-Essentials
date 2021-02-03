package me.xpyre.mvpcustomessentials.commands.inventory;

import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("m.clearinventory")) {
                if (args.length < 1) {
                    player.getInventory().clear();
                    player.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("inventory-clear-message-self").replace("[SENDER]", player.getName()).replace("[RECEIVER]", player.getName())));
                }else if(!args[0].equals("*")){
                    Player player1 = Bukkit.getPlayer(args[0]);
                    player1.getInventory().clear();
                    player.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("inventory-clear-message-sender").replace("[RECEIVER]", player1.getName()).replace("[SENDER]", player.getName())));
                    player1.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("inventory-clear-message-receiver").replace("[RECEIVER]", player1.getName()).replace("[SENDER]", player.getName())));
                }else{

                }
            }else{
                player.sendMessage(ChatColor.RED + "You do not have the required permissions to run this command!");
            }
        }else{
            if (args.length < 1) {
                sender.sendMessage("you MUST be a player to run this command!");
            }else if(!args[0].equals("*")){
                Player player1 = Bukkit.getPlayer(args[0]);
                player1.getInventory().clear();
                sender.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("inventory-clear-message-sender").replace("[RECEIVER]", player1.getName()).replace("[SENDER]", "Console")));
                player1.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("inventory-clear-message-receiver").replace("[RECEIVER]", player1.getName()).replace("[SENDER]", "Console")));
            }else{
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.getInventory().clear();
                    sender.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("inventory-clear-message-sender-all").replace("[RECEIVER]", player.getName()).replace("[SENDER]", "Console")));
                    player.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("inventory-clear-message-receiver").replace("[RECEIVER]", player.getName()).replace("[SENDER]", "Console")));
                }
            }
        }
        return true;
    }
}

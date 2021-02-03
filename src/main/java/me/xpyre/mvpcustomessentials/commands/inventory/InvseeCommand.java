package me.xpyre.mvpcustomessentials.commands.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length < 1){
                player.sendMessage(ChatColor.RED + "You must provide a player");
            }else{
                Player player1 = Bukkit.getPlayer(args[0]);
                if(player1 != null) {
                    player.openInventory(player1.getInventory());
                }else{
                    player.sendMessage(ChatColor.RED + "That player does not exist!");
                }
            }
        }else{
            sender.sendMessage(ChatColor.RED + "You MUST be a player to run this command!");
        }
        return true;
    }
}

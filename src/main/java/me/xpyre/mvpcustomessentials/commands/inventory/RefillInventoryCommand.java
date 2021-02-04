package me.xpyre.mvpcustomessentials.commands.inventory;

import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RefillInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("m.refill.inv")) {
                if (args.length >= 1) {
                    Player player1 = Bukkit.getPlayer(args[0]);
                    Util.replacePlayerInventory(player1);
                    player.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("refill-message-sender").replace("[SENDER]", player.getName()).replace("[RECEIVER]", player1.getName())));
                } else {
                    player.sendMessage(ChatColor.RED + "You must provide a player!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have the required permissions to run this command!");
            }
        } else {
            if (args.length >= 1) {
                Player player1 = Bukkit.getPlayer(args[0]);
                Util.replacePlayerInventory(player1);
                sender.sendMessage(Util.translateColorCodes(MessagesConfig.getConfig().getString("refill-message-sender").replace("[SENDER]", "Console").replace("[RECEIVER]", player1.getName())));
            } else {
                sender.sendMessage(ChatColor.RED + "You must provide a player!");
            }
        }
        return true;
    }
}

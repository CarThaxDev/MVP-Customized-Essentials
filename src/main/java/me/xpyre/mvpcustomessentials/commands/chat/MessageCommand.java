package me.xpyre.mvpcustomessentials.commands.chat;

import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player)commandSender;
            if(player.hasPermission("m.msg")) {
                if (strings.length <= 1) {
                    player.sendMessage(ChatColor.RED + "You are missing either the player to message or the message itself!");
                } else {
                    Player player1 = Bukkit.getPlayer(strings[0]);
                    if(player1 != null) {
                        if(player1 != player) {
                            StringBuilder message = new StringBuilder();
                            for (String string : strings) {
                                message.append(string).append(" ");

                            }
                            String msgFormatReceiverWithPlaceholders = MessagesConfig.getConfig().getString("message-format-receiver");
                            String msgFormatSenderWithPlaceholders = MessagesConfig.getConfig().getString("message-format-sender");
                            msgFormatReceiverWithPlaceholders = msgFormatSenderWithPlaceholders.replace("[SENDER]", player.getName()).replace("[RECEIVER]",player1.getName()).replace("[MESSAGE]", message.toString());
                            msgFormatSenderWithPlaceholders = msgFormatSenderWithPlaceholders.replace("[SENDER]", player.getName()).replace("[RECEIVER]",player1.getName()).replace("[MESSAGE]", message.toString());
                            player1.sendMessage(msgFormatReceiverWithPlaceholders);
                        }else{
                            player.sendMessage(ChatColor.RED + "You cannot message yourself!");
                        }
                    }else{
                        player.sendMessage(ChatColor.RED + "That player does not exist!");
                    }
                }
            }else{
                player.sendMessage(ChatColor.RED + "You don't have the required permissions to run this command!");
            }

        }
        return true;
    }
}

package me.xpyre.mvpcustomessentials.commands;

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
            if(strings.length <= 1){
                player.sendMessage(ChatColor.RED + "You did not provide enough information");
                return false;
            }else{
                Player player2 = Bukkit.getPlayer(strings[0]);
                String message = "";
                for(String string : strings){
                    message = message + string + " ";

                }
                player2.sendMessage(player.getName()+ "--->" + message);
                return true;
            }

        }else{
            return true;

        }

    }
}

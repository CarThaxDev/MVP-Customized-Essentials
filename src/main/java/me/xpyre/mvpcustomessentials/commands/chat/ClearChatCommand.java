package me.xpyre.mvpcustomessentials.commands.chat;

import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("m.clearchat")){
                clearChat();
            }else{
                player.sendMessage(ChatColor.RED + "You do not have the required permissions to run this command!");
            }
        }else{
            clearChat();
        }
        return true;
    }

    private void clearChat() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i <= 150; i++) {
                if (i < 150) {
                    if(!player.hasPermission("clearchat.bypass")){
                        player.sendMessage("");
                    }
                }else{
                    player.sendMessage(Util.translateColorCodes("&l&eChat has been cleared!"));
                }
            }
        }
    }
}

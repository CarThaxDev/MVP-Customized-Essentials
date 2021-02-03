package me.xpyre.mvpcustomessentials.commands.chat;

import me.xpyre.mvpcustomessentials.Main;
import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnmuteChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("m.unmutechat")){
                player.sendMessage(ChatColor.RED + "You do not have the required permission to use this command! If you believe this is a mistake, please contact a moderator.");
            }else{
                if(!Main.getChatMutedState()){
                    player.sendMessage(ChatColor.RED + "Chat isn't muted!");
                }else{
                    if(Main.setChatMuted(true)){
                        player.sendMessage(ChatColor.GREEN + "Successfully un-muted chat.");
                        Bukkit.broadcastMessage(Util.translateColorCodes("&l&aChat has been un-muted!"));
                    }else{
                        player.sendMessage(ChatColor.RED + "Failed to un-mute chat.");
                    }
                }
            }
        }else{
            if(!Main.getChatMutedState()){
                sender.sendMessage(ChatColor.RED + "Chat isn't muted!");
            }else{
                if(Main.setChatMuted(true)){
                    sender.sendMessage(ChatColor.GREEN + "Successfully un-muted chat.");
                }else{
                    sender.sendMessage(ChatColor.RED + "Failed to un-mute chat.");
                }
            }
        }
        return true;
    }
}

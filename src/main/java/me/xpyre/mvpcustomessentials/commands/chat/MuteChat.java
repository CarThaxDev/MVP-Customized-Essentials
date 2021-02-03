package me.xpyre.mvpcustomessentials.commands.chat;

import me.xpyre.mvpcustomessentials.Main;
import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("m.mutechat")){
                player.sendMessage(ChatColor.RED + "You do not have the required permission to use this command! If you believe this is a mistake, please contact a moderator.");
                return true;
            }else{
                if(Main.getChatMutedState()){
                    player.sendMessage(ChatColor.RED + "Chat is already muted!");
                }else{
                    if(Main.setChatMuted(false)){
                        player.sendMessage(ChatColor.GREEN + "Successfully muted chat.");
                        Bukkit.broadcastMessage(Util.translateColorCodes("&l&cChat has been muted!"));
                    }else{
                        player.sendMessage(ChatColor.RED + "Failed to mute chat.");
                    }
                }
                return true;
            }
        }else{
            if(!Main.getChatMutedState()){
                sender.sendMessage(ChatColor.RED + "Chat is already muted!!");
            }else{
                if(Main.setChatMuted(false)){
                    sender.sendMessage(ChatColor.GREEN + "Successfully muted chat.");
                }else{
                    sender.sendMessage(ChatColor.RED + "Failed to mute chat.");
                }
            }
            return true;
        }
    }
}

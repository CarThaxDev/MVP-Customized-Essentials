package me.xpyre.mvpcustomessentials.commands.gamemode;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeSpectator implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("m.gamemode")){
                player.setGameMode(GameMode.SPECTATOR);
            }else{
                player.sendMessage(ChatColor.RED + "You do not have the required permissions to run this command!");
            }
        }else{
            sender.sendMessage(ChatColor.RED + "You must be a player to run this command!");
        }
        return true;
    }
}

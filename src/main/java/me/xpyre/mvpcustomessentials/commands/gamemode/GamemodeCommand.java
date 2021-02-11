package me.xpyre.mvpcustomessentials.commands.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("m.gamemode")){
                if(args.length < 1){
                    p.sendMessage(ChatColor.RED + "You need to provide a gamemode!");
                }else if(args.length < 2){
                    if(args[0].equalsIgnoreCase("creative")){
                        p.setGameMode(GameMode.CREATIVE);
                    }else if(args[0].equalsIgnoreCase("survival")){
                        p.setGameMode(GameMode.SURVIVAL);
                    }else if(args[0].equalsIgnoreCase("adventure")){
                        p.setGameMode(GameMode.ADVENTURE);
                    }else if(args[0].equalsIgnoreCase("spectator")){
                        p.setGameMode(GameMode.SPECTATOR);
                    }
                }else{
                    Player p2 = Bukkit.getPlayer(args[1]);
                    if(p2 != null){
                        if(args[0].equalsIgnoreCase("creative")){
                            p2.setGameMode(GameMode.CREATIVE);
                        }else if(args[0].equalsIgnoreCase("survival")){
                            p2.setGameMode(GameMode.SURVIVAL);
                        }else if(args[0].equalsIgnoreCase("adventure")){
                            p2.setGameMode(GameMode.ADVENTURE);
                        }else if(args[0].equalsIgnoreCase("spectator")){
                            p2.setGameMode(GameMode.SPECTATOR);
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "You must provide a valid player!");
                    }
                }
            }else{
                p.sendMessage(ChatColor.RED + "You do not have the required permissions to run this command!");
            }
        }else{
            if(args.length < 2){
                sender.sendMessage("You must provide a gamemode AND a player!");
            }else{
                Player p = Bukkit.getPlayer(args[1]);
                if(p != null){
                    if(args[0].equalsIgnoreCase("creative")){
                        p.setGameMode(GameMode.CREATIVE);
                    }else if(args[0].equalsIgnoreCase("survival")){
                        p.setGameMode(GameMode.SURVIVAL);
                    }else if(args[0].equalsIgnoreCase("adventure")){
                        p.setGameMode(GameMode.ADVENTURE);
                    }else if(args[0].equalsIgnoreCase("spectator")){
                        p.setGameMode(GameMode.SPECTATOR);
                    }
                }else{
                    sender.sendMessage(ChatColor.RED + "You must provide a valid player!");
                }
            }
        }
        return true;
    }
}

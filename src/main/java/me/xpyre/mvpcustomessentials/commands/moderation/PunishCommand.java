package me.xpyre.mvpcustomessentials.commands.moderation;

import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PunishCommand implements CommandExecutor {
    YamlConfiguration config = null;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("m.punish")){
                player.sendMessage(ChatColor.RED + "You do not have the required permissions to run this command!");
            }else {
                if (args.length > 0) {
                    config = MessagesConfig.getConfig();
                    if (args[0].equalsIgnoreCase("tempban") || args[0].equalsIgnoreCase("tempmute")) {
                        if (args.length < 5) {
                            player.sendMessage(ChatColor.RED + "Insufficient Arguments!");
                        } else {
                            OfflinePlayer target = null;
                            for (Player player2 : Bukkit.getOnlinePlayers()) {
                                if (player2.getName().equals(args[4])) {
                                    target = player2;
                                }
                            }
                            if (target == null) {
                                for (OfflinePlayer player1 : Bukkit.getOfflinePlayers()) {
                                    if (player1.getName().equals(args[4]) && player1.hasPlayedBefore()) {
                                        target = player1;
                                    }
                                }
                            }
                            if (target == null) {
                                player.sendMessage(ChatColor.RED + "Player does not exist!");
                            } else {
                                if (args[3].equalsIgnoreCase("s")) {

                                }
                            }
                        }
                    }else if (args[0].equalsIgnoreCase("unmute") || args[0].equalsIgnoreCase("unban")) {
                        //WIP
                    }else {
                        if (args.length < 4) {
                            player.sendMessage(ChatColor.RED + "Insufficient Arguments!");
                        } else {
                            OfflinePlayer target = null;
                            for (Player player2 : Bukkit.getOnlinePlayers()) {
                                if (player2.getName().equals(args[3])) {
                                    target = player2;
                                }
                            }
                            if (target == null) {
                                for (OfflinePlayer player1 : Bukkit.getOfflinePlayers()) {
                                    if (player1.getName().equals(args[3]) && player1.hasPlayedBefore()) {
                                        target = player1;
                                    }
                                }
                            }
                            if (target == null) {
                                player.sendMessage(ChatColor.RED + "Player does not exist!");
                            } else {
                                if (target.isBanned()) {
                                    player.sendMessage(ChatColor.RED + "Player is already banned!");
                                } else {
                                    if (args[0].equalsIgnoreCase("ban")) {
                                        StringBuilder reason = new StringBuilder();
                                        for (int i = 4; i < args.length; i++) {
                                            reason.append(args[i]).append(" ");
                                        }
                                        Util.banPlayer(target, reason.toString(), player);
                                    } else if (args[0].equalsIgnoreCase("kick")) {
                                        StringBuilder reason = new StringBuilder();
                                        for (int i = 4; i < args.length; i++) {
                                            reason.append(args[i]).append(" ");
                                        }
                                        if (!target.isOnline()) {
                                            player.sendMessage("That player is not online!");
                                        } else {
                                            target.getPlayer().kickPlayer(reason.toString());
                                        }
                                    } else if (args[0].equalsIgnoreCase("mute")) {
                                        if (Util.mutePlayer(target)) {
                                            player.sendMessage(Util.translateColorCodes(config.getString("successfully-punished-message").replace("[PUNISHMENT]", "muted").replace("[RECEIVER]", target.getName())));
                                            if (target.isOnline()) {
                                                target.getPlayer().sendMessage(Util.translateColorCodes(config.getString("punished-message-receiver").replace("[PUNISHMENT]", "muted")));
                                            }
                                        } else {
                                            player.sendMessage(Util.translateColorCodes(config.getString("already-punished-message").replace("[PUNISHMENT]", "muted")));
                                        }
                                    } else if(args[0].equalsIgnoreCase("ipmute")){

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}

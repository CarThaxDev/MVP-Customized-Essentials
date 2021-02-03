package me.xpyre.mvpcustomessentials.utility;

import org.bukkit.ChatColor;

public class Util {
    public static String translateColorCodes(String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }
}

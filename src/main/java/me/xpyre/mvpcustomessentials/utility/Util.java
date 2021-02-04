package me.xpyre.mvpcustomessentials.utility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Util {
    private static HashMap<Player, ItemStack[]> playerItemStackMap = new HashMap<>();
    private static HashMap<Player, ItemStack[]> playerArmorMap = new HashMap<>();

    public static String translateColorCodes(String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }
    public static ItemStack[] getPlayerInventoryContents(Player player){
        return playerItemStackMap.get(player);
    }
    public static void saveInventory(Player player){
        List<ItemStack> is = Arrays.asList(player.getInventory().getContents());
        ItemStack[] armorContents = player.getInventory().getArmorContents();
        playerArmorMap.put(player, armorContents);
        ItemStack[] isl = is.toArray(new ItemStack[0]);
        playerItemStackMap.put(player, isl);
    }
    public static boolean replacePlayerInventory(Player player){
        try{
            ItemStack[] isl = playerItemStackMap.get(player);
            ItemStack[] armorList = playerArmorMap.get(player);
            player.getInventory().setArmorContents(armorList);
            player.getInventory().setContents(isl);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

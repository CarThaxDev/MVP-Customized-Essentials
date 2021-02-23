package me.xpyre.mvpcustomessentials.utility;

import me.xpyre.mvpcustomessentials.Main;
import me.xpyre.mvpcustomessentials.data.MessagesConfig;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.net.InetAddress;
import java.sql.Connection;
import java.util.*;

public class Util {
    private static HashMap<Player, ItemStack[]> playerItemStackMap = new HashMap<>();
    private static HashMap<Player, ItemStack[]> playerArmorMap = new HashMap<>();
    private static ArrayList<OfflinePlayer> mutedPlayers = new ArrayList<>();
    private static ArrayList<InetAddress> mutedIPs = new ArrayList<>();
    private static Connection con;

    private static HashMap<OfflinePlayer, Cooldown> tempmuteCooldownMap = new HashMap<>();


    public static void setCon(Connection conn){con = conn;}
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
    public static void replacePlayerInventory(Player player){
        try{
            ItemStack[] isl = playerItemStackMap.get(player);
            ItemStack[] armorList = playerArmorMap.get(player);
            player.getInventory().setArmorContents(armorList);
            player.getInventory().setContents(isl);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static boolean mutePlayer(OfflinePlayer player){
        if(!mutedPlayers.contains(player)){
            mutedPlayers.add(player);
            return true;
        }else{
            return false;
        }
    }
    public static String unMutePlayer(OfflinePlayer player){
        if(mutedPlayers.contains(player)){
            mutedPlayers.remove(player);
            return "SUCCESS";
        }else if(!mutedPlayers.contains(player)) {
            return "NOT_MUTED";
        }else if(tempmuteCooldownMap.containsKey(player)){
            unTempMutePlayer(player);
        }
        return "ERROR";
        }
    public static boolean tempMutePlayer(long timeMuted, OfflinePlayer p){
        Cooldown cooldown = new Cooldown(timeMuted, Main.getInstance(), p);
        if(!tempmuteCooldownMap.containsKey(p)){
            tempmuteCooldownMap.put(p, cooldown);
            return true;
        }else{
            return false;
        }
    }
    public static void banPlayer(OfflinePlayer target, String reason, Player bannedBy) {
        YamlConfiguration config = MessagesConfig.getConfig();
        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), config.getString("ban-message").replace("[REASON]", reason).replace("[PLAYER]", bannedBy.getName()), null, null);
        if(target.isOnline()){
            target.getPlayer().kickPlayer(config.getString("ban-message").replace("[REASON]", reason).replace("[PLAYER]", bannedBy.getName()));
        }
    }
    public static void tempBanPlayer(OfflinePlayer target, String reason, Player bannedBy, int time){
        YamlConfiguration config = MessagesConfig.getConfig();
        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), config.getString("ban-message").replace("[REASON]", reason).replace("[PLAYER]", bannedBy.getName()), new Date(System.currentTimeMillis() + time), bannedBy.getName());
    }
    public static boolean ipMutePlayer(Player player){
        if(!mutedIPs.contains(player.getAddress().getAddress())){
            mutedIPs.add(player.getAddress().getAddress());
            return true;
        }else{
            return false;
        }
    }
    public static boolean checkIfPlayerMuted(Player p){
        return mutedPlayers.contains(p) || mutedIPs.contains(p.getAddress().getAddress());
    }
    public static boolean flushMutesToConfig() {
        try {
            Main.getInstance().getConfig().set("mutes.players", mutedPlayers);
            Main.getInstance().getConfig().set("mutes.ips", mutedIPs);
            Main.getInstance().getConfig().set("mutes.temp", tempmuteCooldownMap);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void unTempMutePlayer(OfflinePlayer player) {
        tempmuteCooldownMap.remove(player);
    }
    public static void reloadMutes(){
        FileConfiguration config = Main.getInstance().getConfig();
        if(config.isSet("mutes.ips")) {
            mutedIPs = (ArrayList<InetAddress>) Main.getInstance().getConfig().getList("mutes.ips");
        }
        if(config.isSet("mutes.temp")){
            tempmuteCooldownMap = (HashMap<OfflinePlayer, Cooldown>) config.get("mutes.temp");
        }
        if(config.isSet("mutes.players")){
            mutedPlayers = (ArrayList<OfflinePlayer>) config.getList("mutes.players");
        }
    }
}

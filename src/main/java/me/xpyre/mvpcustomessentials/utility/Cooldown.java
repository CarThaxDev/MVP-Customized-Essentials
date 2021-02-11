package me.xpyre.mvpcustomessentials.utility;

import me.xpyre.mvpcustomessentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Date;

public class Cooldown {
    private long timeOver;
    private Date date;
    private Player player;

    public Cooldown(long cooldown, Main main, OfflinePlayer p){
        timeOver = System.currentTimeMillis() + cooldown;
        date = new Date();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {
                if(isCooldownDone()){
                    Util.unTempMutePlayer(p);
                }
            }
        }, 0L, 20L);
    }

    protected boolean isCooldownDone(){
        return System.currentTimeMillis() >= timeOver;
    }

    protected boolean forceCooldownOver(){
        try{
            timeOver = System.currentTimeMillis();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

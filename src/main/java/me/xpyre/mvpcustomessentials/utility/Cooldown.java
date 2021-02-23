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
    private int i = 0;

    public Cooldown(long cooldown, Main main, OfflinePlayer p){
        timeOver = System.currentTimeMillis() + cooldown;
        date = new Date();
        i = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, () -> {
            if(isCooldownDone()){
                Util.unTempMutePlayer(p);
                cancelTask();
            }
        }, 0L, 20L);
    }

    private void cancelTask() {
        Bukkit.getServer().getScheduler().cancelTask(i);
    }

    protected boolean isCooldownDone(){
        return System.currentTimeMillis() >= timeOver;
    }
}

package me.xpyre.mvpcustomessentials.events;

import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathEventHandler implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e){
        Util.saveInventory(e.getEntity());
    }

}

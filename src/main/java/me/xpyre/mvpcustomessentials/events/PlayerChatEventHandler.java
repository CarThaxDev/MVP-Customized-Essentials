package me.xpyre.mvpcustomessentials.events;

import me.xpyre.mvpcustomessentials.Main;
import me.xpyre.mvpcustomessentials.utility.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEventHandler implements Listener {
    @EventHandler
    public void onPlayerSendChatMessage(AsyncPlayerChatEvent e){
        if(Main.getChatMutedState()) {
            e.setCancelled(!e.getPlayer().hasPermission("mutechat.bypass"));
        }else if(Util.checkIfPlayerMuted(e.getPlayer())){
            e.setCancelled(true);
        }
    }
}

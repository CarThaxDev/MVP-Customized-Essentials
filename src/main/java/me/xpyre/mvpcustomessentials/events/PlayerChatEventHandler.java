package me.xpyre.mvpcustomessentials.events;

import me.xpyre.mvpcustomessentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChatEventHandler implements Listener {
    @EventHandler
    public void onPlayerSendChatMessage(AsyncPlayerChatEvent e){
        if(Main.getChatMutedState()) {
            e.setCancelled(!e.getPlayer().hasPermission("mutechat.bypass"));
        }
    }
}

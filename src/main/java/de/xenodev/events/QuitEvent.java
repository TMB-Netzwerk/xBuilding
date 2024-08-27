package de.xenodev.events;

import de.xenodev.xBuilding;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(xBuilding.getPrefix() + "§7Der Spieler §6" + player.getName() + " §7hat §cverlassen");
    }
}

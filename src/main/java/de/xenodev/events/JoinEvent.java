package de.xenodev.events;

import de.xenodev.utils.BoardBuilder;
import de.xenodev.xBuilding;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(xBuilding.getPrefix() + "§7Der Spieler §6" + player.getName() + " §7ist §abeigetreten");
        BoardBuilder.createBoard(player);

    }



}

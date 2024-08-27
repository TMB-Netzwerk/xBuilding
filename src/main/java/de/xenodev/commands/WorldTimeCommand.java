package de.xenodev.commands;

import de.xenodev.utils.TimeBuilder;
import de.xenodev.xBuilding;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldTimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;

            TimeBuilder timeBuilder = new TimeBuilder(player.getWorld().getName());
            Integer days = timeBuilder.getDays();
            Integer hours = timeBuilder.getHours();
            Integer minutes = timeBuilder.getMinutes();
            Integer seconds = timeBuilder.getSeconds();

            player.sendMessage("");
            player.sendMessage("§8----------» §a§lWeltzeit §8«----------");
            player.sendMessage("");
            player.sendMessage("§7§lWeltname: §e" + player.getWorld().getName().toUpperCase());
            player.sendMessage("§7§lZeit: §6" + days + " §7Tage, §6" + hours + "§7h, §6" + minutes + "§7m, §6" + seconds + "§7s");
            player.sendMessage("");
            player.sendMessage("§8----------» §a§lWeltzeit §8«----------");
            player.sendMessage("");
        }else{
            sender.sendMessage(xBuilding.getError() + " §7Du bist kein Spieler!");
        }

        return false;
    }
}

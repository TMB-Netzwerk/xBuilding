package de.xenodev.commands;

import de.xenodev.utils.NameFetcher;
import de.xenodev.utils.UUIDFetcher;
import de.xenodev.xBuilding;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class EnderseeCommand implements CommandExecutor, TabCompleter, Listener {

    private OfflinePlayer target;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 1){
                if(!player.hasPermission("tmb.command.endersee.other")){
                    player.sendMessage(xBuilding.getPrefix() + "§7Dir fehlt folgende Permission: §6" + "tmb.command.endersee.other");
                    return true;
                }

                target = Bukkit.getOfflinePlayer(UUIDFetcher.getUUID(args[0]));
                if(target == null || !target.isOnline()){
                    player.sendMessage(xBuilding.getError() + "§cDer Spieler §6" + NameFetcher.getName(target.getUniqueId()) + " §cist nicht online");
                    return true;
                }

                player.openInventory(target.getPlayer().getEnderChest());
                player.sendMessage(xBuilding.getPrefix() + "§7Du hast die Enderchest von §6" + target.getName() + " §7geöffnet");
            }else{
                if(!player.hasPermission("tmb.command.endersee")){
                    player.sendMessage(xBuilding.getPrefix() + "§7Dir fehlt folgende Permission: §6" + "tmb.command.endersee");
                    return true;
                }

                player.openInventory(player.getEnderChest());
                player.sendMessage(xBuilding.getPrefix() + "§7Du hast deine Enderchest von geöffnet");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();

        if(args.length == 1){
            for(Player players : Bukkit.getOnlinePlayers()){
                arrayList.add(players.getName());
            }
        }

        return arrayList;
    }

    @EventHandler
    public void handleEnderseeInventory(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory() == target.getPlayer().getEnderChest()){
            if(player.hasPermission("tmb.command.endersee.bypass")){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }

    }
}

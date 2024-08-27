package de.xenodev.commands;

import de.xenodev.utils.LocationBuilder;
import de.xenodev.xBuilding;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LocationCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;

            if(args.length == 1) {
                if(LocationBuilder.getLocationNames().contains(args[0])){
                    player.teleport(LocationBuilder.getLocation(args[0]));
                    player.sendMessage(xBuilding.getPrefix() + "§7Du hast dich zu §6" + args[0].toUpperCase() + " §7teleportiert");
                }else{
                    player.sendMessage(xBuilding.getError() + "§cDie Location §6" + args[0].toUpperCase() + " §cwurde nicht gefunden");
                }
            }else if(args.length == 2){
                if (!player.hasPermission("tmb.command.location")) {
                    player.sendMessage(xBuilding.getError() + "§7Dir fehlt folgende Permission: §6" + "tmb.command.locations");
                    return true;
                }

                if(args[0].equalsIgnoreCase("create")){
                    if(!LocationBuilder.getLocationNames().contains(args[1])){
                        LocationBuilder.setLocation(args[1], player.getLocation());
                        player.sendMessage(xBuilding.getPrefix() + "§7Du hast die Location §6" + args[1].toUpperCase() + " §7erstellt");
                    }else{
                        player.sendMessage(xBuilding.getError() + "§cDie Location §6" + args[1].toUpperCase() + " §cexistiert bereits");
                    }
                }else if(args[0].equalsIgnoreCase("delete")){
                    if(LocationBuilder.getLocationNames().contains(args[1])){
                        LocationBuilder.delLocation(args[1]);
                        player.sendMessage(xBuilding.getPrefix() + "§7Du hast die Location §6" + args[1].toUpperCase() + " §7gelöscht");
                    }else{
                        player.sendMessage(xBuilding.getError() + "§cDie Location §6" + args[1].toUpperCase() + " §cwurde nicht gefunden");
                    }
                }else{
                    player.sendMessage(xBuilding.getError() + "§cBitte Benutze: /location <create, delete> <name>");
                }
            }else{
                player.sendMessage(xBuilding.getError() + "§cBitte Benutze: /location <name>");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();

        if (args.length == 1) {
            for(String names : LocationBuilder.getLocationNames()){
                arrayList.add(names);
            }
            if(sender.hasPermission("tmb.command.location")) {
                arrayList.add("create");
                arrayList.add("delete");
            }
        }else if(args.length == 2){
            if(args[0].equalsIgnoreCase("delete")){
                for(String names : LocationBuilder.getLocationNames()){
                    arrayList.add(names);
                }
            }
        }

        return arrayList;
    }
}

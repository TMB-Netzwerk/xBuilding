package de.xenodev.commands;

import de.xenodev.utils.NameFetcher;
import de.xenodev.utils.UUIDFetcher;
import de.xenodev.xBuilding;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    private ArrayList<String> gameModes = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;

            if(args.length == 1){
                setGameModes();
                String modeName = args[0];

                if(!gameModes.contains(modeName)){
                    player.sendMessage(xBuilding.getError() + "§cDen Spielmodus " + modeName + " §cgibt es nicht");
                    return true;
                }

                if(!player.hasPermission("tmb.command." + modeName)){
                    player.sendMessage(xBuilding.getPrefix() + "§7Dir fehlt folgende Permission: §6" + "tmb.command." + modeName);
                    return true;
                }

                if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")){
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(xBuilding.getPrefix() + "§7Dein Spielmodus wurde auf §eSurvival §7gesetzt");
                }else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(xBuilding.getPrefix() + "§7Dein Spielmodus wurde auf §eCreative §7gesetzt");
                }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(xBuilding.getPrefix() + "§7Dein Spielmodus wurde auf §eAdventure §7gesetzt");
                }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(xBuilding.getPrefix() + "§7Dein Spielmodus wurde auf §eSpectator §7gesetzt");
                }
            }else if(args.length == 2){
                setGameModes();
                String modeName = args[0];

                if(!gameModes.contains(modeName)){
                    player.sendMessage(xBuilding.getError() + "§cDen Spielmodus §6" + modeName + " §cgibt es nicht");
                    return true;
                }

                if(!player.hasPermission("tmb.command." + modeName + ".other")){
                    player.sendMessage(xBuilding.getPrefix() + "§7Dir fehlt folgende Permission: §6" + "tmb.command." + modeName + ".other");
                    return true;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(UUIDFetcher.getUUID(args[1]));
                if(target == null || !target.isOnline()){
                    player.sendMessage(xBuilding.getError() + "§cDer Spieler §6" + NameFetcher.getName(target.getUniqueId()) + " §cist nicht online");
                    return true;
                }

                if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")){
                    target.getPlayer().setGameMode(GameMode.SURVIVAL);
                    target.getPlayer().sendMessage(xBuilding.getPrefix() + "§7Dein Spielmodus wurde auf §eSurvival §7gesetzt");
                    player.sendMessage(xBuilding.getPrefix() + "§7Der Spielmodus von §6" + target.getName() + " §7wurde auf §eSurvival §7gesetzt");
                }else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                    target.getPlayer().setGameMode(GameMode.CREATIVE);
                    target.getPlayer().sendMessage(xBuilding.getPrefix() + "§7Dein Spielmodus wurde auf §eCreative §7gesetzt");
                    player.sendMessage(xBuilding.getPrefix() + "§7Der Spielmodus von §6" + target.getName() + " §7wurde auf §eCreative §7gesetzt");
                }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                    target.getPlayer().setGameMode(GameMode.ADVENTURE);
                    target.getPlayer().sendMessage(xBuilding.getPrefix() + "§7Dein Spielmodus wurde auf §eAdventure §7gesetzt");
                    player.sendMessage(xBuilding.getPrefix() + "§7Der Spielmodus von §6" + target.getName() + " §7wurde auf §eAdventure §7gesetzt");
                }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                    target.getPlayer().setGameMode(GameMode.SPECTATOR);
                    target.getPlayer().sendMessage(xBuilding.getPrefix() + "§7Dein Spielmodus wurde auf §eSpectator §7gesetzt");
                    player.sendMessage(xBuilding.getPrefix() + "§7Der Spielmodus von §6" + target.getName() + " §7wurde auf §eSpectator §7gesetzt");
                }
            }else{
                player.sendMessage(xBuilding.getError() + "§cBitte Benutze: /gamemode <gamemode> <player>");
            }
        }

        return false;
    }

    private void setGameModes(){
        gameModes.add("0");
        gameModes.add("1");
        gameModes.add("2");
        gameModes.add("3");
        gameModes.add("survival");
        gameModes.add("creative");
        gameModes.add("adventure");
        gameModes.add("spectator");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();

        if(args.length == 1){
            setGameModes();
            arrayList = gameModes;
        }else if(args.length == 2){
            for(Player players : Bukkit.getOnlinePlayers()){
                arrayList.add(players.getName());
            }
        }

        return arrayList;
    }
}

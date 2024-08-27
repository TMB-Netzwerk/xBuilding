package de.xenodev.commands;

import de.xenodev.xBuilding;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class RenameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;

            if(!player.hasPermission("tmb.command.rename")){
                player.sendMessage(xBuilding.getPrefix() + "§7Dir fehlt folgende Permission: §6" + "tmb.command.rename");
                return true;
            }

            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if(itemStack == null || itemStack.getType().equals(Material.AIR)){
                player.sendMessage(xBuilding.getError() + "§cDu hast kein gültiges Item in der Hand");
                return true;
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            StringBuilder itemName = new StringBuilder(args[0]);
            if(args.length == 0){
                player.sendMessage(xBuilding.getError() + "§cBitte gib eine Nachricht ein");
                return true;
            }
            for(int i = 1; i < args.length; i++){
                itemName.append(" ").append(args[i]);
            }
            itemMeta.setDisplayName(itemName.toString().replaceAll("&", "§"));
            itemStack.setItemMeta(itemMeta);
            player.sendMessage(xBuilding.getPrefix() + "§7Du hast dein Item in " + itemName.toString().replaceAll("&", "§") + " §7umgenannt");
        }

        return false;
    }
}

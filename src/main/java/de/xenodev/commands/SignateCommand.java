package de.xenodev.commands;

import de.xenodev.xBuilding;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SignateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;

            if(!player.hasPermission("tmb.command.signate")){
                player.sendMessage(xBuilding.getPrefix() + "§7Dir fehlt folgende Permission: §6" + "tmb.command.signate");
                return true;
            }

            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if(itemStack == null || itemStack.getType().equals(Material.AIR)){
                player.sendMessage(xBuilding.getError() + "§cDu hast kein gültiges Item in der Hand");
                return true;
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            StringBuilder itemLore = new StringBuilder(args[0]);
            if(args.length == 0){
                player.sendMessage(xBuilding.getError() + "§cBitte gib eine Nachricht ein");
                return true;
            }
            for(int i = 1; i < args.length; i++){
                itemLore.append(" ").append(args[i]);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            itemMeta.setLore(Arrays.asList("", itemLore.toString().replaceAll("&", "§"), "§7-------------------------------------------", "§7Signiert von §e§l" + player.getName() + " §7am §a§l" + simpleDateFormat.format(new Date())));
            itemStack.setItemMeta(itemMeta);
            player.sendMessage(xBuilding.getPrefix() + "§7Du hast dein Item mit " + itemLore.toString().replaceAll("&", "§") + " §7signiert");
        }

        return false;
    }
}

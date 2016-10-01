package net.midnightgames.levdev.magicffa.listener;

import net.midnightgames.levdev.magicffa.MagicFFA;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levin on 18.09.2016.
 */
public class InteractEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event){
        if(event.getRightClicked() instanceof Villager){
            if(event.getRightClicked().getCustomName().equalsIgnoreCase(MagicFFA.getFfa().getPrefix() + ChatColor.GRAY.toString() + "Händler")){
                event.setCancelled(true);
                event.getPlayer().closeInventory();
                Inventory inventory = Bukkit.createInventory(null, 54, MagicFFA.getFfa().getPrefix() + ChatColor.GRAY.toString() + "Händler");

                List<String> quitList = new ArrayList<>();
                quitList.add(" ");
                quitList.add(ChatColor.GRAY + "» " + ChatColor.RED + "Drücke " + ChatColor.UNDERLINE + "hier" + ChatColor.RESET + ChatColor.RED.toString() + " um");
                quitList.add(ChatColor.RED + "den " + ChatColor.DARK_RED + "Händler" + ChatColor.RED + " zu verlassen!");

                ItemStack quit = new ItemStack(Material.BARRIER);
                ItemMeta quitMeta = quit.getItemMeta();
                quitMeta.setDisplayName(ChatColor.DARK_RED + "Verlassen");
                quitMeta.setLore(quitList);
                quit.setItemMeta(quitMeta);

                inventory.setItem(53, quit);

                event.getPlayer().openInventory(inventory);
            }
        }
    }

}

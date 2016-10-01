package net.midnightgames.levdev.magicffa.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Levin on 19.09.2016.
 */
public class ClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "Verlassen")){
            event.setCancelled(true);
            player.closeInventory();
        }
    }

}

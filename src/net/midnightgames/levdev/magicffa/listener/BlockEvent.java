package net.midnightgames.levdev.magicffa.listener;

import net.midnightgames.levdev.magicffa.MagicFFA;
import net.midnightgames.levdev.magicffa.utils.ActionbarUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by Levin on 18.09.2016.
 */
public class BlockEvent implements Listener {

    @EventHandler
    public void onBuild(BlockPlaceEvent event){
        if(event.getPlayer().hasPermission("ffa.build") == false){
            event.setCancelled(true);
            ActionbarUtil.sendActionBar(event.getPlayer(), MagicFFA.getFfa().getPrefix() + ChatColor.RED + "Du darfst hier nichts platzieren!", 10);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(event.getPlayer().hasPermission("ffa.build") == false){
            event.setCancelled(true);
            ActionbarUtil.sendActionBar(event.getPlayer(), MagicFFA.getFfa().getPrefix() + ChatColor.RED + "Du darfst hier nichts abbauen!", 10);
        }
    }

}

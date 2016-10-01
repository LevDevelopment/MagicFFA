package net.midnightgames.levdev.magicffa.listener;

import net.midnightgames.levdev.magicffa.MagicFFA;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Levin on 18.09.2016.
 */
public class JoinEvent implements Listener {

    private int i = 10;

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(event.getPlayer().hasPlayedBefore() == false){
            event.setJoinMessage(MagicFFA.getFfa().getPrefix() + ChatColor.DARK_PURPLE + event.getPlayer().getDisplayName() + ChatColor.LIGHT_PURPLE + " hat den Server zum 1. mal betreten!");

        } else {
            event.setJoinMessage(null);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
            player.sendMessage(MagicFFA.getFfa().getPrefix() + ChatColor.RED + "Test");

        }
    }

}

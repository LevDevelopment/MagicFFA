package net.midnightgames.levdev.magicffa.commands;

import net.midnightgames.levdev.magicffa.MagicFFA;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Levin on 18.09.2016.
 */
public class Command_Spawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("spawn")){
            if(commandSender instanceof Player){
                Player player = (Player) commandSender;
                if(args.length == 1){
                    if(player.hasPermission("magicffa.spawn.entities")) {
                        if (args[0].equalsIgnoreCase("main")) {

                            Villager villager = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
                            villager.setCanPickupItems(false);
                            villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 200));
                            villager.setCustomName(MagicFFA.getFfa().getPrefix() + ChatColor.GRAY.toString() + "Händler");
                        }
                    }
                }
            }
        }
        return true;
    }
}

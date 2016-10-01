package net.midnightgames.levdev.magicffa.commands;

import net.midnightgames.levdev.magicffa.MagicFFA;
import net.midnightgames.levdev.magicffa.utils.ActionbarUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Levin on 19.09.2016.
 */
public class Command_Ping implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("ping")){
            if(commandSender instanceof Player){
                Player player = (Player) commandSender;
                ActionbarUtil.sendActionBar(player, MagicFFA.getFfa().getPrefix() + ChatColor.YELLOW + "Errechne den Ping...", 20);
                Bukkit.getScheduler().runTaskLater(MagicFFA.getFfa(), () -> {
                    ActionbarUtil.sendActionBar(player, MagicFFA.getFfa().getPrefix() + ChatColor.RED + "Dein aktueller Ping: " + ChatColor.YELLOW + ((CraftPlayer) player).getHandle().ping + " ms");
                }, 21);
            }
        }
        return true;
    }
}

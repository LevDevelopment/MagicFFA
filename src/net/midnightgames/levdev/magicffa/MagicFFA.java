package net.midnightgames.levdev.magicffa;

import net.midnightgames.levdev.magicffa.commands.Command_Ping;
import net.midnightgames.levdev.magicffa.commands.Command_Spawn;
import net.midnightgames.levdev.magicffa.listener.BlockEvent;
import net.midnightgames.levdev.magicffa.listener.InteractEvent;
import net.midnightgames.levdev.magicffa.listener.JoinEvent;
import net.midnightgames.levdev.magicffa.listener.QuitEvent;
import net.midnightgames.levdev.magicffa.utils.ActionbarUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Levin on 18.09.2016.
 */
public class MagicFFA extends JavaPlugin {

    private static MagicFFA ffa;

    //private Register<Library> register;

    private final String PREFIX = ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + " FFA " + ChatColor.RESET;

    @Override
    public void onEnable() {
        new Thread(() -> {
            System.out.println(getPrefix());
            Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
            Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
            Bukkit.getPluginManager().registerEvents(new BlockEvent(), this);
            Bukkit.getPluginManager().registerEvents(new InteractEvent(), this);
            getCommand("spawn").setExecutor(new Command_Spawn());
            getCommand("ping").setExecutor(new Command_Ping());

            //register.getEventRegister().registerEventsPackage("net.midnightgames.levdev.magicffa.listener");
            //register.getEventRegister().registerEvents(JoinEvent.class);
            //register.getCommandRegister().registerCommand("", "", new);

            ActionbarUtil.nmsver = Bukkit.getServer().getClass().getPackage().getName();
            ActionbarUtil.nmsver = ActionbarUtil.nmsver.substring(ActionbarUtil.nmsver.lastIndexOf(".") + 1);
            if ((ActionbarUtil.nmsver.equalsIgnoreCase("v1_8_R1")) || (ActionbarUtil.nmsver.equalsIgnoreCase("v1_7_"))) {
                ActionbarUtil.useOldMethods = true;
            }

        }).start();
        
    }

    @Override
    public void onLoad() {
        ffa = this;
        ActionbarUtil.plugin = this;
        //register = new Register<>(Library.getLibrary());
    }

    public static MagicFFA getFfa() {
        return ffa;
    }

    public String getPrefix() {
        return PREFIX;
    }
}

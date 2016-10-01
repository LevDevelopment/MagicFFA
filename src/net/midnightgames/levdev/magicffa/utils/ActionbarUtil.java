package net.midnightgames.levdev.magicffa.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Levin on 18.09.2016.
 */
public class ActionbarUtil {

    public static Plugin plugin;
    public static boolean works = true;
    public static String nmsver;
    public static boolean useOldMethods = false;

    public static void sendActionBar(Player player, String message)
    {
        ActionbarMessageEvent actionBarMessageEvent = new ActionbarMessageEvent(player, message);
        Bukkit.getPluginManager().callEvent(actionBarMessageEvent);
        if (actionBarMessageEvent.isCancelled()) {
            return;
        }
        try {
            Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
            Object p = c1.cast(player);

            Class<?> c4 = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
            Class<?> c5 = Class.forName("net.minecraft.server." + nmsver + ".Packet");
            Object ppoc;
            if (useOldMethods)
            {
                Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatSerializer");
                Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
                Method m3 = c2.getDeclaredMethod("a", new Class[] { String.class });
                Object cbc = c3.cast(m3.invoke(c2, new Object[] { "{\"text\": \"" + message + "\"}" }));
                ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE }).newInstance(new Object[] { cbc, Byte.valueOf((byte)2) });
            }
            else
            {
                Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
                Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
                Object o = c2.getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });
                ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE }).newInstance(new Object[] { o, Byte.valueOf((byte)2) });
            }
            Method m1 = c1.getDeclaredMethod("getHandle", new Class[0]);
            Object h = m1.invoke(p, new Object[0]);
            Field f1 = h.getClass().getDeclaredField("playerConnection");
            Object pc = f1.get(h);
            Method m5 = pc.getClass().getDeclaredMethod("sendPacket", new Class[] { c5 });
            m5.invoke(pc, new Object[] { ppoc });
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            works = false;
        }
    }

    public static void sendActionBar(Player player, final String message, int duration)
    {
        sendActionBar(player, message);
        if (duration >= 0) {
            new BukkitRunnable()
            {
                public void run()
                {
                    ActionbarUtil.sendActionBar(player, "");
                }
            }.runTaskLater(plugin, duration + 1);
        }
        while (duration > 60)
        {
            duration -= 60;
            int sched = duration % 60;
            new BukkitRunnable()
            {
                public void run()
                {
                    ActionbarUtil.sendActionBar(player, message);
                }
            }.runTaskLater(plugin, sched);
        }
    }

    public static void sendActionBarToAllPlayers(String message)
    {
        sendActionBarToAllPlayers(message, -1);
    }

    public static void sendActionBarToAllPlayers(String message, int duration)
    {
        for (Player p : Bukkit.getOnlinePlayers()) {
            sendActionBar(p, message, duration);
        }
    }

}

package me.beefdev.crafter;

import me.beefdev.crafter.listener.CraftItemListener;
import me.beefdev.crafter.listener.PrepareItemCraftListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public final class Crafter {

    public static final String NAME_SPACE = "crafter-api";

    private static final CraftingManager craftingManager = new CraftingManager();
    private static Plugin plugin;

    public static void onEnable(Plugin plugin) {
        Crafter.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(new PrepareItemCraftListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new CraftItemListener(), plugin);
    }

    public static CraftingManager getCraftingManager() {
        return Crafter.craftingManager;
    }
}

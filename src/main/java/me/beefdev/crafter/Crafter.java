package me.beefdev.crafter;

import com.google.common.base.Preconditions;
import me.beefdev.crafter.listener.CraftItemListener;
import me.beefdev.crafter.listener.PrepareItemCraftListener;
import me.beefdev.crafter.listener.RecipeDiscoveryListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * The main class of the Crafter API
 */
public final class Crafter {

    public static final String NAME_SPACE = "crafter-api";

    private static final CraftingManager craftingManager = new CraftingManager();

    /**
     * A method used to enable all event handlers needed for the recipes to function, should be called inside the onEnable() method of your plugin
     * @param plugin The plugin used to register listeners
     */
    public static void onEnable(Plugin plugin) {
        Preconditions.checkNotNull(plugin, "Plugin can't be null");
        Preconditions.checkArgument(plugin.isEnabled(), "Plugin is disabled");

        Bukkit.getPluginManager().registerEvents(new PrepareItemCraftListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new CraftItemListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new RecipeDiscoveryListener(), plugin);
    }

    /**
     * @return The CraftingManager class used for creating and removing recipes
     */
    public static CraftingManager getCraftingManager() {
        return Crafter.craftingManager;
    }
}

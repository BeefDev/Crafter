package me.beefdev.crafter;

import me.beefdev.crafter.listener.CraftItemListener;
import me.beefdev.crafter.listener.PrepareItemCraftListener;
import me.beefdev.crafter.recipes.CraftingRecipe;
import me.beefdev.crafter.recipes.ShapedCraftingRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public final class Crafter {

    public static final String NAME_SPACE = "crafter-api";

    private static final CraftingManager craftingManager = new CraftingManager();
    private static Plugin plugin;

    public static void onEnable(Plugin plugin) {
        Crafter.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(new PrepareItemCraftListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new CraftItemListener(), plugin);
    }

    public void method() {
        List<ItemStack> ingredients = new ArrayList<>();
        ItemStack result = new ItemStack(Material.STONE);

        CraftingRecipe recipe = new ShapedCraftingRecipe("randomKey", ingredients, result);

        Crafter.getCraftingManager().registerRecipe(recipe);
    }

    public static CraftingManager getCraftingManager() {
        return Crafter.craftingManager;
    }
}

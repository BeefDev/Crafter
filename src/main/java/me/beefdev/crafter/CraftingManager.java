package me.beefdev.crafter;

import com.google.common.base.Preconditions;
import me.beefdev.crafter.recipes.CraftingRecipe;
import me.beefdev.crafter.util.BukkitRecipeMethods;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class used for managing the api, instance can be obtained used Crafter.getCraftingManager()
 */
public final class CraftingManager {

    private final Map<NamespacedKey, CraftingRecipe> RECIPES;

    CraftingManager() {
        this.RECIPES = new HashMap<>();
    }

    /**
     * @param key The key of the desired recipe
     * @return The recipe corresponding to the provided key, or null if none are found
     */
    public CraftingRecipe getRegisteredRecipe(NamespacedKey key) {
        Preconditions.checkNotNull(key, "Can't search using null key");
        return this.RECIPES.get(key);
    }

    /**
     * @return All custom registered recipes
     */
    public List<CraftingRecipe> getRegisteredRecipes() {
        return new ArrayList<>(this.RECIPES.values());
    }

    /**
     * A method used to check if a recipe matching this key is registered
     * @param key The key used to find the recipe
     * @return TRUE if a recipe by that key exists otherwise FALSE
     */
    public boolean isRegistered(NamespacedKey key) {
        Preconditions.checkNotNull(key, "Can't search using null key");
        return this.RECIPES.containsKey(key);
    }

    /**
     * Used for creating a new custom recipe
     * @param recipe The recipe being created
     */
    public void registerRecipe(CraftingRecipe recipe) {
        Preconditions.checkNotNull(recipe, "Can't register null recipe");
        Preconditions.checkArgument(BukkitRecipeMethods.getRecipe(recipe.getKey()) == null, "Duplicate keys, a recipe already exists with the key " + recipe.getKey());

        this.RECIPES.put(recipe.getKey(), recipe);
        Bukkit.addRecipe(recipe.toBukkit());
    }

    /**
     * Used to delete a recipe
     * @param key The key used by the desired recipe
     */
    public void unregisterRecipe(NamespacedKey key) {
        Preconditions.checkNotNull(key, "Can't search using null key");

        CraftingRecipe recipe = this.RECIPES.get(key);
        if(recipe == null) return;

        this.RECIPES.remove(recipe.getKey());

        BukkitRecipeMethods.removeRecipe(key);
    }
}

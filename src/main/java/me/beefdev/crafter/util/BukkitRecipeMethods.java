package me.beefdev.crafter.util;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility class used for replacing some Bukkit methods that are not present in older versions of the game
 */
public final class BukkitRecipeMethods {

    private BukkitRecipeMethods(){}

    /**
     * Get a recipe by looping through all recipes and checking their keys
     * @param key The key used to query the recipe
     * @return The recipe corresponding to the passed key, or null if none is found
     */
    public static Recipe getRecipe(NamespacedKey key) {
        Preconditions.checkNotNull(key);
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();

        while(recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();

            if(recipe instanceof Keyed) {
                Keyed keyed = (Keyed) recipe;
                if(keyed.getKey().equals(key)) {
                    return recipe;
                }
            }
        }

        return null;
    }

    /**
     * Remove a recipe by storing all recipes, clearing them and refilling them
     * @param key The key used to query the recipe
     */
    public static void removeRecipe(NamespacedKey key) {
        Preconditions.checkNotNull(key);
        List<Recipe> recipes = new ArrayList<>();
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();

        while(recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();

            if(recipe instanceof Keyed) {
                Keyed keyed = (Keyed) recipe;
                if(!keyed.getKey().equals(key)) {
                    recipes.add(recipe);
                }
            }
        }

        Bukkit.clearRecipes();

        for(Recipe recipe : recipes) Bukkit.addRecipe(recipe);
    }
}

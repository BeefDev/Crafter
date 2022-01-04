package me.beefdev.crafter;

import com.google.common.base.Preconditions;
import me.beefdev.crafter.recipes.CraftingRecipe;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public final class CraftingManager {

    private final Map<NamespacedKey, CraftingRecipe> RECIPES_NAME_SPACED_KEY;
    private final Map<List<ItemStack>, CraftingRecipe> RECIPES_INGREDIENT_KEY;
    private final Map<ItemStack, List<CraftingRecipe>> RECIPES_RESULT_KEY;

    CraftingManager() {
        this.RECIPES_NAME_SPACED_KEY = new HashMap<>();
        this.RECIPES_INGREDIENT_KEY = new HashMap<>();
        this.RECIPES_RESULT_KEY = new HashMap<>();
    }

    public CraftingRecipe getCraftingRecipe(NamespacedKey key) {
        Preconditions.checkNotNull(key);

        return this.RECIPES_NAME_SPACED_KEY.get(key);
    }

    public CraftingRecipe getCraftingRecipe(List<ItemStack> matrix) {
        Preconditions.checkNotNull(matrix);

        matrix = matrix.stream().filter(Objects::nonNull).collect(Collectors.toList());
        return this.RECIPES_INGREDIENT_KEY.get(matrix);
    }

    public List<CraftingRecipe> getCraftingRecipesFor(ItemStack result) {
        Preconditions.checkNotNull(result);

        return this.RECIPES_RESULT_KEY.containsKey(result) ? new ArrayList<>(this.RECIPES_RESULT_KEY.get(result)) : Collections.emptyList();
    }

    public List<CraftingRecipe> getCustomCraftingRecipes() {
        return new ArrayList<>(this.RECIPES_NAME_SPACED_KEY.values());
    }

    public void registerRecipe(CraftingRecipe recipe) {
        Preconditions.checkNotNull(recipe);
        Preconditions.checkArgument(Bukkit.getRecipe(recipe.getKey()) == null, "Duplicate keys, a recipe already exists with the key " + recipe.getKey());

        this.RECIPES_NAME_SPACED_KEY.put(recipe.getKey(), recipe);
        this.RECIPES_INGREDIENT_KEY.put(recipe.getIngredients(), recipe);

        List<CraftingRecipe> recipesForResult = this.RECIPES_RESULT_KEY.getOrDefault(recipe.getResult(), new ArrayList<>());
        recipesForResult.add(recipe);

        this.RECIPES_RESULT_KEY.put(recipe.getResult(), recipesForResult);

        Bukkit.addRecipe(recipe.toBukkit());
    }

    public void unregisterRecipe(NamespacedKey key) {
        Preconditions.checkNotNull(key);

        CraftingRecipe recipe = this.RECIPES_NAME_SPACED_KEY.get(key);
        if(recipe == null) return;

        this.RECIPES_RESULT_KEY.get(recipe.getResult()).remove(recipe);
        this.RECIPES_INGREDIENT_KEY.remove(recipe.getIngredients());
        this.RECIPES_NAME_SPACED_KEY.remove(recipe.getKey());

        Bukkit.removeRecipe(key);
    }

    public boolean isRegistered(NamespacedKey key) {
        return this.RECIPES_NAME_SPACED_KEY.containsKey(key);
    }
}

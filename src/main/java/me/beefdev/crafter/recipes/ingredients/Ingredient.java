package me.beefdev.crafter.recipes.ingredients;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * An interface used for handling ingredients in crafting recipes
 */
public interface Ingredient {

    /**
     * This method is used in the PrepareItemCraftEvent to compare ItemStacks in the crafting matrix in order to check if the recipe is fulfilled
     * @param item The item being compared to this ingredient, the item is never null
     * @return returns whether the item matches this ingredient, if any ingredients return false the recipe is not fulfilled and the item is not crafted
     * */
    boolean matches(ItemStack item);

    /**
     * @return A positive Integer representing the minimum amount of this ingredient in order to fulfill the recipe
     */
    int getAmount();

    /**
     * A utility method needed for the registration of CraftingRecipes
     * @return The material this Ingredient is suppossed to represent in the crafting grid
     */
    Material toMaterial();
}

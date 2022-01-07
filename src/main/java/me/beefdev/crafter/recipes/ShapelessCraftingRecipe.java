package me.beefdev.crafter.recipes;

import com.google.common.base.Preconditions;
import me.beefdev.crafter.recipes.ingredients.Ingredient;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of CraftingRecipe class dedicated to ShapelessRecipes. Any CraftingRecipe subclass dedicated to the handling of ShapelessRecipes should implement its methods in the same way as this class for the recipe to be handled correctly
 */
public class ShapelessCraftingRecipe extends CraftingRecipe {

    private final List<Ingredient> ingredients;

    /**
     * @param nameSpacedKey The key used for generating the NamespacedKey identifier of this recipe, must match the NamespacedKey regex [a-z0-9/._-]+
     * @param ingredients The ingredients of this recipe, size of the list must be greater than 0 and smaller than 10
     * @param result The item that will be returned if this recipe is crafted
     */
    public ShapelessCraftingRecipe(String nameSpacedKey, List<Ingredient> ingredients, ItemStack result) {
        super(nameSpacedKey, result);

        Preconditions.checkNotNull(ingredients, "Ingredient list can't be null");
        Preconditions.checkArgument(ingredients.size() <= 9, "Ingredient list length must be smaller or equal to 9 with no null items");

        this.ingredients = new ArrayList<>(ingredients);
    }

    @Override
    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public Recipe toBukkit() {
        ShapelessRecipe recipe = new ShapelessRecipe(this.key, this.result);

        for(Ingredient ingredient : this.ingredients) recipe.addIngredient(ingredient.toMaterial());
        return recipe;
    }
}

package me.beefdev.crafter.recipes;

import com.google.common.base.Preconditions;
import me.beefdev.crafter.recipes.ingredients.Ingredient;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of CraftingRecipe class dedicated to ShapedRecipes. Any CraftingRecipe subclass dedicated to the handling of ShapedRecipes should implement its methods in the same way as this class for the recipe to be handled correctly
 */
public class ShapedCraftingRecipe extends CraftingRecipe {

    private final List<Ingredient> ingredients;

    /**
     * @param nameSpacedKey The key used for generating the NamespacedKey identifier of this recipe, must match the NamespacedKey regex [a-z0-9/._-]+
     * @param ingredients The ingredients of this recipe, size of the list must be 9
     * @param result The item that will be returned if this recipe is crafted
     */
    public ShapedCraftingRecipe(String nameSpacedKey, List<Ingredient> ingredients, ItemStack result) {
        super(nameSpacedKey, result);

        Preconditions.checkNotNull(ingredients, "Ingredient list can't be null");
        Preconditions.checkArgument(ingredients.size() == 9, "Ingredient list length must be equal to 9, use null for empty ingredient slots");

        this.ingredients = new ArrayList<>(ingredients);
    }

    @Override
    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public Recipe toBukkit() {
        ShapedRecipe recipe = new ShapedRecipe(this.key, this.result);

        recipe.shape(
                String.format("%s%s%s", ingredients.get(0) == null ? " " : "0", ingredients.get(1) == null ? " " : "1", ingredients.get(2) == null ? " " : "2"),
                String.format("%s%s%s", ingredients.get(3) == null ? " " : "3", ingredients.get(4) == null ? " " : "4", ingredients.get(5) == null ? " " : "5"),
                String.format("%s%s%s", ingredients.get(6) == null ? " " : "6", ingredients.get(7) == null ? " " : "7", ingredients.get(8) == null ? " " : "8"));

        for(int index = 0; index<ingredients.size(); index++) {
            if(this.ingredients.get(index) != null) recipe.setIngredient(String.valueOf(index).charAt(0), this.ingredients.get(index).toMaterial());
        }

        return recipe;
    }
}

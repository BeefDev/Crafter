package me.beefdev.crafter.recipes;

import com.google.common.base.Preconditions;
import me.beefdev.crafter.Crafter;
import me.beefdev.crafter.recipes.ingredients.Ingredient;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * A class used to represent recipes, in general this should not be extended as the provided ShapedCraftingRecipe, ShapelessCraftingRecipe classes should be used for any recipe as the handling of recipe in the event handlers is based on their implementation. If the class is extended all moved must be overridden according to their contract so that event handlers can process the recipe correctly
 */
public abstract class CraftingRecipe implements Recipe, Keyed {

    protected final NamespacedKey key;
    protected final ItemStack result;

    /**
     * @param nameSpacedKey The key used for generating the NamespacedKey identifier of this recipe, must match the NamespacedKey regex [a-z0-9/._-]+
     * @param result The item that will be returned if this recipe is crafted
     */
    protected CraftingRecipe(String nameSpacedKey, ItemStack result) {
        Preconditions.checkNotNull(nameSpacedKey, "Key can't be null");
        Preconditions.checkNotNull(result, "Result can't be null");

        this.key = new NamespacedKey(Crafter.NAME_SPACE, nameSpacedKey);
        this.result = result.clone();
    }

    @Override
    @Nonnull
    public NamespacedKey getKey() {
        return this.key;
    }

    @Override
    @Nonnull
    public ItemStack getResult() {
        return this.result;
    }

    /**
     * Method used for converting custom CraftingRecipe implementations to bukkit recipes for their registration
     * @return The Bukkit recipe corresponding to this recipe, must return an instance ShapedRecipe or ShapelessRecipe
     */
    public abstract Recipe toBukkit();

    /**
     * Method which returns the ingredients of this recipe, the size of the ingredient list may be 1-9 if the toBukkit() method returns a ShapelessRecipe, however for a ShapedRecipe the size must be 9
     * @return The ingredients of this recipe in a list
     */
    public abstract List<Ingredient> getIngredients();

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof CraftingRecipe)) return false;

        CraftingRecipe recipe = (CraftingRecipe) object;
        return recipe.getKey().equals(this.key) && recipe.getIngredients().equals(this.getIngredients()) && recipe.getResult().equals(this.result);
    }
}

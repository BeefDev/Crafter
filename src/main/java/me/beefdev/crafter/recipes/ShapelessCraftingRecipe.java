package me.beefdev.crafter.recipes;

import com.google.common.base.Preconditions;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ShapelessCraftingRecipe extends CraftingRecipe {

    private final List<ItemStack> ingredients;

    public ShapelessCraftingRecipe(String nameSpacedKey, List<ItemStack> ingredients, ItemStack result) {
        super(nameSpacedKey, result);

        Preconditions.checkNotNull(ingredients);
        Preconditions.checkArgument(ingredients.size() <= 9);

        this.ingredients = new ArrayList<>(ingredients);
    }

    public List<ItemStack> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<ItemStack> ingredients) {
        Preconditions.checkNotNull(ingredients);
        Preconditions.checkArgument(ingredients.size() <= 9);

        this.ingredients.clear();
        this.ingredients.addAll(ingredients.stream().map(ItemStack::clone).collect(Collectors.toList()));
    }

    @Override
    public Recipe toBukkit() {
        ShapelessRecipe recipe = new ShapelessRecipe(this.key, this.result);

        for(ItemStack ingredient : this.ingredients) recipe.addIngredient(ingredient.getType());
        return recipe;
    }
}

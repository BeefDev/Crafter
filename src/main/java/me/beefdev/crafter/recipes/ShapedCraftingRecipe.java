package me.beefdev.crafter.recipes;

import com.google.common.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ShapedCraftingRecipe extends CraftingRecipe {

    private final List<ItemStack> ingredients;

    public ShapedCraftingRecipe(String nameSpacedKey, List<ItemStack> ingredients, ItemStack result) {
        super(nameSpacedKey, result);

        Preconditions.checkNotNull(ingredients);
        Preconditions.checkArgument(ingredients.size() == 9);

        this.ingredients = new ArrayList<>(ingredients);
    }

    public ItemStack getIngredientAt(int index) {
        Preconditions.checkArgument(index >= 0 && index < 9);

        return this.ingredients.get(index);
    }

    public void setIngredientAt(int index, ItemStack item) {
        Preconditions.checkNotNull(item);
        Preconditions.checkArgument(index >= 0 && index < 9);

        this.ingredients.set(index, item);
    }

    public List<ItemStack> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<ItemStack> ingredients) {
        Preconditions.checkNotNull(ingredients);
        Preconditions.checkArgument(ingredients.size() == 9);

        this.ingredients.clear();
        this.ingredients.addAll(ingredients.stream().map(ItemStack::clone).collect(Collectors.toList()));
    }

    @Override
    public Recipe toBukkit() {
        ShapedRecipe recipe = new ShapedRecipe(this.key, this.result);

        recipe.shape(
                String.format("%s%s%s", ingredients.get(0) == null ? " " : "0", ingredients.get(1) == null ? " " : "1", ingredients.get(2) == null ? " " : "2"),
                String.format("%s%s%s", ingredients.get(3) == null ? " " : "3", ingredients.get(4) == null ? " " : "4", ingredients.get(5) == null ? " " : "5"),
                String.format("%s%s%s", ingredients.get(6) == null ? " " : "6", ingredients.get(7) == null ? " " : "7", ingredients.get(8) == null ? " " : "8"));

        for(int index = 0; index<ingredients.size(); index++) {
            if(this.ingredients.get(index) != null) recipe.setIngredient(String.valueOf(index).charAt(0), this.ingredients.get(index).getType());
        }

        return recipe;
    }
}

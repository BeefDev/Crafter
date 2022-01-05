package me.beefdev.crafter.recipes.ingredients;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public final class IngredientMaterial implements Ingredient {

    private final Material material;
    private final int minimum;

    public IngredientMaterial(Material material) {
        Preconditions.checkNotNull(material, "material can't be null");
        this.material = material;
        this.minimum = 1;
    }

    public IngredientMaterial(Material material, int minimum) {
        Preconditions.checkNotNull(material, "material can't be null");
        Preconditions.checkArgument(minimum > 0, "amount must be positive");
        this.material = material;
        this.minimum = minimum;
    }

    @Override
    public boolean matches(@Nonnull ItemStack item) {
        return item.getType() == this.material;
    }

    @Override
    public int getAmount() {
        return this.minimum;
    }

    @Override
    public Material toMaterial() {
        return this.material;
    }
}

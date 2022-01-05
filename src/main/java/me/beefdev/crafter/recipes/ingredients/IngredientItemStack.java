package me.beefdev.crafter.recipes.ingredients;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public final class IngredientItemStack implements Ingredient {

    private final ItemStack item;

    public IngredientItemStack(ItemStack item) {
        Preconditions.checkNotNull(item, "item can't be null");
        this.item = item;
    }

    @Override
    public boolean matches(@Nonnull ItemStack item) {
        return this.item.isSimilar(item);
    }

    @Override
    public int getAmount() {
        return this.item.getAmount();
    }

    @Override
    public Material toMaterial() {
        return this.item.getType();
    }
}

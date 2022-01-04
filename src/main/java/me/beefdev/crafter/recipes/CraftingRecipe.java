package me.beefdev.crafter.recipes;

import com.google.common.base.Preconditions;
import me.beefdev.crafter.Crafter;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class CraftingRecipe implements Recipe, Keyed {

    protected final NamespacedKey key;
    protected final ItemStack result;

    protected CraftingRecipe(String nameSpacedKey, ItemStack result) {
        Preconditions.checkNotNull(nameSpacedKey);
        Preconditions.checkNotNull(result);

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

    public abstract Recipe toBukkit();
    public abstract List<ItemStack> getIngredients();

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof CraftingRecipe)) return false;

        CraftingRecipe that = (CraftingRecipe) object;
        return that.getKey().equals(this.key) && that.getIngredients().equals(this.getIngredients()) && that.getResult().equals(this.result);
    }
}

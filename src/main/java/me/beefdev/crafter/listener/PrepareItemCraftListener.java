package me.beefdev.crafter.listener;

import me.beefdev.crafter.Crafter;
import me.beefdev.crafter.recipes.ingredients.Ingredient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class PrepareItemCraftListener implements Listener {

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        if(event.getRecipe() instanceof ShapedRecipe) {
            ShapedRecipe recipe = (ShapedRecipe) event.getRecipe();

            if(!Crafter.getCraftingManager().isRegistered(recipe.getKey())) return;

            List<Ingredient> actualIngredients = Crafter.getCraftingManager().getRegisteredRecipe(recipe.getKey()).getIngredients();
            List<ItemStack> providedIngredients = Arrays.asList(event.getInventory().getMatrix());

            for(int index = 0; index < providedIngredients.size(); index++) {
                if(providedIngredients.get(index) == null && actualIngredients.get(index) == null) continue;
                if((providedIngredients.get(index) == null || actualIngredients.get(index) == null) || (!actualIngredients.get(index).matches(providedIngredients.get(index))) || (providedIngredients.get(index).getAmount() < actualIngredients.get(index).getAmount())) {
                    event.getInventory().setResult(null);
                    return;
                }
            }
        } else if(event.getRecipe() instanceof ShapelessRecipe) {
            ShapelessRecipe recipe = (ShapelessRecipe) event.getRecipe();

            if(!Crafter.getCraftingManager().isRegistered(recipe.getKey())) return;

            List<Ingredient> actualIngredients = Crafter.getCraftingManager().getRegisteredRecipe(recipe.getKey()).getIngredients();
            ItemStack[] providedIngredients = event.getInventory().getMatrix();

            for (ItemStack providedIngredient : providedIngredients) {
                if (providedIngredient == null) continue;

                Optional<Ingredient> match = actualIngredients.stream().filter(ingredient -> ingredient.matches(providedIngredient) && providedIngredient.getAmount() >= ingredient.getAmount()).findFirst();
                if (!match.isPresent()) {
                    event.getInventory().setResult(null);
                    return;
                }

                actualIngredients.remove(match.get());
            }
        }
    }
}

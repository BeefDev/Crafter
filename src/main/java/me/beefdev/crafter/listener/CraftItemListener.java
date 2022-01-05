package me.beefdev.crafter.listener;

import me.beefdev.crafter.Crafter;
import me.beefdev.crafter.recipes.ingredients.Ingredient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class CraftItemListener implements Listener {

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if(event.getRecipe() instanceof ShapedRecipe) {
            ShapedRecipe recipe = (ShapedRecipe) event.getRecipe();

            if(!Crafter.getCraftingManager().isRegistered(recipe.getKey())) return;

            List<Ingredient> actualIngredients = Crafter.getCraftingManager().getRegisteredRecipe(recipe.getKey()).getIngredients();
            List<ItemStack> providedIngredients = Arrays.asList(event.getInventory().getMatrix());

            if(event.getClick().isShiftClick()) {
                while(true) {
                    for(int index = 0; index < providedIngredients.size(); index++) {
                        if(providedIngredients.get(index) == null) continue;

                        event.getInventory().getMatrix()[index].setAmount(providedIngredients.get(index).getAmount() - (actualIngredients.get(index).getAmount()));
                    }

                    boolean stop = false;
                    for(int index = 0; index < providedIngredients.size(); index++) {
                        if(actualIngredients.get(index) != null && providedIngredients.get(index) == null) {
                            stop = true;
                            break;
                        }
                        if(providedIngredients.get(index) == null) continue;
                        if(providedIngredients.get(index).getAmount() < actualIngredients.get(index).getAmount()) {
                            stop = true;
                            break;
                        }
                    }

                    if(stop) break;

                    HashMap<Integer, ItemStack> left = event.getWhoClicked().getInventory().addItem(event.getRecipe().getResult());
                    if(!left.isEmpty()) break;
                }

                for(int index = 0; index < providedIngredients.size(); index++) {
                    if(actualIngredients.get(index) != null) {
                        if(event.getInventory().getMatrix()[index] == null) {
                            ItemStack[] matrix = event.getInventory().getMatrix().clone();
                            ItemStack item = providedIngredients.get(index);
                            item.setAmount(1);
                            matrix[index] = item;
                            event.getInventory().setMatrix(matrix);
                        } else event.getInventory().getMatrix()[index].setAmount(event.getInventory().getMatrix()[index].getAmount() + 1);
                    }
                }
            } else {
                for(int index = 0; index < providedIngredients.size(); index++) {
                    if(providedIngredients.get(index) == null) continue;

                    event.getInventory().getMatrix()[index].setAmount(providedIngredients.get(index).getAmount() - (actualIngredients.get(index).getAmount() - 1));
                }
            }
        }
    }
}

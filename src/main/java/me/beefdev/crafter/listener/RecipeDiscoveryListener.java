package me.beefdev.crafter.listener;

import me.beefdev.crafter.Crafter;
import me.beefdev.crafter.recipes.CraftingRecipe;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class RecipeDiscoveryListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for(CraftingRecipe recipe : Crafter.getCraftingManager().getRegisteredRecipes()) {
            NamespacedKey key = recipe.getKey();

            if(!player.hasDiscoveredRecipe(key)) {
                player.discoverRecipe(key);
            }
        }
    }
}

# Crafter
An easy to use crafting API for spigot that allows developers to quickly make custom recipes without having to handle crafting events themselves or deal with bukkits crafting API.

Create recipes in only a few lines of code
<br><br>
Shaped recipes:
```
Ingredient materialIngredient = new IngredientMaterial(Material.NETHERITE_INGOT);

List<Ingredient> ingredients = List.of(
    materialIngredient,materialIngredient,materialIngredient,
    materialIngredient,null,materialIngredient,
    null,null,null
);
ItemStack result = new ItemStack(Material.NETHERITE_HELMET);

CraftingRecipe recipe = new ShapedCraftingRecipe("netherite-helmet", ingredients, result);

Crafter.getCraftingManager().registerRecipe(recipe);
```

Shapeless recipes:
```
Ingredient itemIngredient = new IngredientItemStack(item);
List<Ingredient> ingredients = List.of(itemIngredient, itemIngredient, itemIngredient, itemIngredient);
ItemStack result = new ItemStack(Material.BEDROCK);
        
CraftingRecipe recipe = new ShapelessCraftingRecipe("enchanted-stone", ingredients, result);
        
Crafter.getCraftingManager().registerRecipe(recipe);
```

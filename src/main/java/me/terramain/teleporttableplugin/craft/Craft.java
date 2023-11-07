package me.terramain.teleporttableplugin.craft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

public class Craft {
    private List<String> shape;
    private List<CraftItem> materials;

    public Craft(List<String> shape, List<CraftItem> materials) {
        this.shape = shape;
        this.materials = materials;
    }
    public Craft(List<String> shape, List<CraftItem> materials, ItemStack result) {
        this.shape = shape;
        this.materials = materials;
        generate(result);
    }

    public void generate(ItemStack result){
        ShapedRecipe recipe = new ShapedRecipe(result);

        String[] shape = new String[this.shape.size()];
        for (int i = 0; i < this.shape.size(); i++) shape[i] = this.shape.get(i);
        recipe.shape(shape);

        for (CraftItem craftItem : materials) recipe.setIngredient(craftItem.getChar(),craftItem.getMaterial());

        Bukkit.addRecipe(recipe);



        ShapedRecipe recipe1 = new ShapedRecipe(result);
        recipe1.shape("**");
        recipe1.shape("*A");

        recipe1.setIngredient('*', Material.EMERALD);
        recipe1.setIngredient('A', Material.STICK);
    }
}

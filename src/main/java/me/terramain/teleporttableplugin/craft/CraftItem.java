package me.terramain.teleporttableplugin.craft;

import org.bukkit.Material;

public class CraftItem {
    private char c;
    private Material material;

    public CraftItem(char c, Material material) {
        this.c = c;
        this.material = material;
    }

    public char getChar() {
        return c;
    }

    public Material getMaterial() {
        return material;
    }
}

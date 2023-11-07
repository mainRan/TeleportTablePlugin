package me.terramain.teleporttableplugin.teleportTable;

import org.bukkit.Material;

import java.util.Objects;

public class TablePassword {
    public Material item1;
    public Material item2;
    public Material item3;

    public TablePassword(Material item1, Material item2, Material item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TablePassword that = (TablePassword) o;
        return item1 == that.item1 && item2 == that.item2 && item3 == that.item3;
    }
    @Override public int hashCode() {
        return Objects.hash(item1, item2, item3);
    }
}

package me.terramain.guiapi;


import de.tr7zw.nbtapi.NBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class GenItem {
    ItemStack itemStack;

    public GenItem(Material material){
        itemStack = new ItemStack(material);
    }
    public GenItem(Material material, int amount){
        itemStack = new ItemStack(material,amount);
    }
    public GenItem(Material material, String name){
        itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RESET+name);
        itemStack.setItemMeta(itemMeta);
    }
    public GenItem(Material material, int amount, String name){
        itemStack = new ItemStack(material,amount);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RESET+name);
        itemStack.setItemMeta(itemMeta);
    }

    public GenItem setName(String name){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RESET+name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }
    public GenItem setLore(List<String> lore){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }
    public GenItem setLore(String lore){
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> loreLines = Arrays.stream(lore.split("\n")).toList();
        itemMeta.setLore(loreLines);
        itemStack.setItemMeta(itemMeta);
        return this;
    }
    public GenItem addLoreLine(String line){
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();
        lore.add(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }
    public GenItem addEnchant(Enchantment enchantment, int level){
        itemStack.addUnsafeEnchantment(enchantment,level);
        return this;
    }
    public GenItem setAmount(int amount){
        itemStack.setAmount(amount);
        return this;
    }
    public GenItem addTextNBT(String path,String value){
        NBT.modify(itemStack, nbt -> {nbt.setString(path, value);});
        return this;
    }
    public GenItem addIntNBT(String path,int value){
        NBT.modify(itemStack, nbt -> {nbt.setInteger(path, value);});
        return this;
    }
    public GenItem addDoubleNBT(String path,double value){
        NBT.modify(itemStack, nbt -> {nbt.setDouble(path, value);});
        return this;
    }
    public GenItem addBooleanNBT(String path,boolean value){
        NBT.modify(itemStack, nbt -> {nbt.setBoolean(path, value);});
        return this;
    }

    public ItemStack compile(){
        return itemStack;
    }

}

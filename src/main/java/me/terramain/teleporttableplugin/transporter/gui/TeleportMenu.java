package me.terramain.teleporttableplugin.transporter.gui;

import me.terramain.guiapi.GUI;
import me.terramain.guiapi.GUIType;
import me.terramain.guiapi.GenItem;
import me.terramain.guiapi.guiFunctions.GUIClickData;
import me.terramain.guiapi.guiFunctions.GUICloseData;
import me.terramain.guiapi.guiFunctions.GUIFunctions;
import me.terramain.guiapi.guiFunctions.GUIOpenData;
import me.terramain.teleporttableplugin.teleportTable.TableLogic;
import me.terramain.teleporttableplugin.teleportTable.TablePassword;
import me.terramain.teleporttableplugin.teleportTable.TeleportTable;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TeleportMenu implements GUIFunctions {

    public static GUI getMenu(TablePassword password){
        return new GUI(
                new TeleportMenu(password),
                GUIType.CHEST
        );
    }

    public TablePassword tablePassword;
    public TeleportMenu(TablePassword tablePassword){
        this.tablePassword=tablePassword;
    }

    @Override
    public void onCreate(GUI gui) {
        //gui = new GUI(this, GUIType.CHEST);

        for (int i = 1; i <= 9; i++) {
            gui.setItem(1,i, new GenItem(Material.GRAY_STAINED_GLASS_PANE,"").compile());//стекло
            gui.setItem(3,i, new GenItem(Material.GRAY_STAINED_GLASS_PANE,"").compile());//стекло
        }
        gui.setItem(2,1, new GenItem(Material.GRAY_STAINED_GLASS_PANE,"").compile());//стекло
        gui.setItem(2,9, new GenItem(Material.GRAY_STAINED_GLASS_PANE,"").compile());//стекло

        gui.setItem(1,9,new GenItem(Material.BLAZE_ROD,"предметы").compile());

        List<TeleportTable> teleportTables = TableLogic.getTablesFromPassword(table.getPassword());
        if (teleportTables.size()==5){//алтари
            gui.setItem(10, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
            gui.setItem(11, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setName(ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
            gui.setItem(15, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setName(ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
            gui.setItem(16, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setName(ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
        }
        else if (teleportTables.size()==4){//алтари
            gui.setItem(10, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setName(ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
            gui.setItem(11, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setName(ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
            gui.setItem(15, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setName(ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
            gui.setItem(16, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").compile());
        }
        else if (teleportTables.size()==3){//алтари
            gui.setItem(10, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setName(ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
            gui.setItem(11, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setName(ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
            gui.setItem(15, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").setName(ChatColor.DARK_GRAY + "алтарь не найден").compile());
            gui.setItem(16, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").setName(ChatColor.DARK_GRAY + "алтарь не найден").compile());
        }
        else if (teleportTables.size()==2){//алтари
            gui.setItem(10, new GenItem(Material.ENDER_PEARL, ChatColor.GREEN + "алтарь").setLore(ChatColor.YELLOW+"вы можете переместиться туда").compile());
            gui.setItem(11, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").compile());
            gui.setItem(15, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").compile());
            gui.setItem(16, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").compile());
        }
        else if (teleportTables.size()<=1){//алтари
            gui.setItem(10, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").compile());
            gui.setItem(11, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").compile());
            gui.setItem(15, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").compile());
            gui.setItem(16, new GenItem(Material.MAGMA_CREAM, ChatColor.DARK_GRAY + "алтарь не найден").compile());
        }
    }

    @Override
    public void onClick(GUIClickData guiClickData) {
        if (guiClickData.ofPlayerInventory()) {
            guiClickData.setCancelled(false);
            return;
        }
        GUI gui = guiClickData.getGui();

        List<TeleportTable> teleportTables = TableLogic.getTablesFromPassword(table.getPassword());
        teleportTables.remove(table);

        ItemStack clickItem = gui.getItem( guiClickData.getSlot() );
        if (clickItem==null) {
            guiClickData.setCancelled(true);
            return;
        }
        Player player = guiClickData.getWhoClicked();
        if (clickItem.getType()==Material.MAGMA_CREAM) player.sendMessage("алтарь телепортер не найден.");
        if (clickItem.getType()==Material.ENDER_PEARL) {
            player.sendMessage("перемещение...");

            if (guiClickData.getSlot()==10) teleportTables.get(0).teleportPlayer(player);
            if (guiClickData.getSlot()==11) teleportTables.get(1).teleportPlayer(player);
            if (guiClickData.getSlot()==15) teleportTables.get(2).teleportPlayer(player);
            if (guiClickData.getSlot()==16) teleportTables.get(3).teleportPlayer(player);

            player.closeInventory();
        }

        guiClickData.setCancelled(true);
    }

    @Override public void onOpen(GUIOpenData guiOpenData) {}
    @Override public void onClose(GUICloseData guiCloseData) {}
}

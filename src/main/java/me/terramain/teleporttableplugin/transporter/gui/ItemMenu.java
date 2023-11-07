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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;


public class ItemMenu implements GUIFunctions {

    public static GUI getMenu(){
        return new GUI(
                new ItemMenu(),
                GUIType.HOPPER
        );
    }
    public ItemMenu(){}

    @Override
    public void onCreate(GUI gui) {
        //gui = new GUI(this, GUIType.HOPPER);

        gui.setItem(0, new GenItem(Material.GRAY_STAINED_GLASS_PANE, "").compile());
        gui.setItem(4, new GenItem(Material.GRAY_STAINED_GLASS_PANE, "").compile());
    }

    @Override public void onClick(GUIClickData guiClickData) {
        if (guiClickData.ofPlayerInventory()) {
            guiClickData.setCancelled(false);
            return;
        }
        guiClickData.setCancelled( guiClickData.getSlot() == 0 || guiClickData.getSlot() == 4 );
    }

    @Override public void onOpen(GUIOpenData guiOpenData) {}

    @Override public void onClose(GUICloseData guiCloseData) {

        ItemStack material1 = guiCloseData.getGui().getItem(1);
        ItemStack material2 = guiCloseData.getGui().getItem(2);
        ItemStack material3 = guiCloseData.getGui().getItem(3);

        if ( material1!=null && material2!=null && material3!=null ){
            TablePassword tablePassword = new TablePassword(
                    material1.getType(),
                    material2.getType(),
                    material3.getType()
            );
            TeleportTable table = new TeleportTable(tableLocation, tablePassword);
            if ( TableLogic.getTablesFromPassword(tablePassword).size() >= 5 ){//если колличество алтарей с этим кодом слишком большое.
                tableLocation.getBlock().setType(Material.AIR);

                TNTPrimed tntPrimed = (TNTPrimed) tableLocation.getWorld().spawnEntity(tableLocation, EntityType.PRIMED_TNT);
                tntPrimed.setFuseTicks(0);
                tntPrimed.setYield(4);
            }
            else {
                TableLogic.teleportTableList.add(table);
            }
        }
        else {
            tableLocation.getBlock().setType(Material.AIR);

            TNTPrimed tntPrimed = (TNTPrimed) tableLocation.getWorld().spawnEntity(tableLocation, EntityType.PRIMED_TNT);
            tntPrimed.setFuseTicks(0);
            tntPrimed.setYield(2);
        }


        //TableLogic.removeMenuOpenFromPlayer(guiCloseData.getPlayer(), "main");
        //TableLogic.removeMenuOpenFromPlayer(guiCloseData.getPlayer(),"setting");
    }
}

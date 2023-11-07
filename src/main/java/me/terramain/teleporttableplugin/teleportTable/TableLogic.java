package me.terramain.teleporttableplugin.teleportTable;

import me.terramain.guiapi.GUI;
import me.terramain.guiapi.GenItem;
import me.terramain.teleporttableplugin.Main;
import me.terramain.teleporttableplugin.craft.Craft;
import me.terramain.teleporttableplugin.craft.CraftItem;
import me.terramain.teleporttableplugin.teleportTable.gui.MainMenu;
import me.terramain.teleporttableplugin.teleportTable.gui.SettingMenu;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TableLogic implements Listener {
    public static final ItemStack altar = new GenItem(Material.END_PORTAL_FRAME, ChatColor.GOLD+"Алтарь телепортер")
            .addEnchant(Enchantment.ARROW_INFINITE,1)
            .compile();

    public static void craft(){
        new Craft(
                List.of(
                        "ABA",
                        "CDC",
                        "EFE"
                ),
                List.of(
                        new CraftItem('A', Material.DIAMOND),
                        new CraftItem('B', Material.ENDER_EYE),
                        new CraftItem('C', Material.EMERALD),
                        new CraftItem('D', Material.CRAFTING_TABLE),
                        new CraftItem('E', Material.ENDER_PEARL),
                        new CraftItem('F', Material.GOLD_INGOT)
                ),
                altar
        );
    }


    public static List<TeleportTable> teleportTableList = new ArrayList<>();
    public static List<TeleportTable> getTablesFromPassword(TablePassword tablePassword){
        if (teleportTableList.size()==0) return new ArrayList<>();
        List<TeleportTable> returnList = new ArrayList<>();
        for (TeleportTable table : teleportTableList) {
            if ( table.getPassword().equals(tablePassword) ){
                returnList.add(table);
            }
        }
        return returnList;
    }
    public static TeleportTable getTableFromLocation(Location location){
        if (teleportTableList.size()==0) return null;
        for (TeleportTable table : teleportTableList) {
            if (table.getLocation().equals(location)){
                return table;
            }
        }
        return null;
    }
    public static void removeTableFromLoc(Location location){
        if (teleportTableList.size()==0) return;
        for (int i = 0; i < teleportTableList.size(); i++) {
            if (teleportTableList.get(i).getLocation().equals(location)){
                teleportTableList.remove(i);
                i--;
            }
        }
    }


    @EventHandler public void buildTable(BlockCanBuildEvent e){//устоновка блока поратала
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if (!e.isBuildable()) return;
        if (
                e.getMaterial()==Material.END_PORTAL_FRAME
                        &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(altar.getItemMeta().getDisplayName())
        ) {
            GUI gui = SettingMenu.getMenu(block.getLocation());
            Main.guiPlugin.guiManager.registerGUI(gui);
            gui.openToPlayer(player);
        }
    }
    @EventHandler public void openTeleportTable(PlayerInteractEvent e){
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();
        TeleportTable table = getTableFromLocation(block.getLocation());
        if (table!=null){
            GUI gui = MainMenu.getMenu(table);
            Main.guiPlugin.guiManager.registerGUI(gui);
            gui.openToPlayer(player);
        }
    }
    @EventHandler public void destroyBlock(BlockBreakEvent e){
        removeTableFromLoc(e.getBlock().getLocation());
    }
    @EventHandler public void destroyBlockOnSurvival(PlayerInteractEvent e){
        if (!e.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
        Block block = e.getClickedBlock();

        TeleportTable table = getTableFromLocation(block.getLocation());
        if (table!=null) {
            removeTableFromLoc(block.getLocation());
            block.setType(Material.AIR);
            block.getWorld().dropItem(block.getLocation(),altar);
        }
    }

}

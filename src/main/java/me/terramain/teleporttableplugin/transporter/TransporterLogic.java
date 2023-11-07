package me.terramain.teleporttableplugin.transporter;

import de.tr7zw.nbtapi.NBT;
import me.terramain.guiapi.GUI;
import me.terramain.guiapi.GenItem;
import me.terramain.teleporttableplugin.Main;
import me.terramain.teleporttableplugin.craft.Craft;
import me.terramain.teleporttableplugin.craft.CraftItem;
import me.terramain.teleporttableplugin.teleportTable.TeleportTable;
import me.terramain.teleporttableplugin.teleportTable.gui.MainMenu;
import me.terramain.teleporttableplugin.transporter.gui.TeleportMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TransporterLogic implements Listener {
    public static final ItemStack transporter = new GenItem(Material.COMPASS, ChatColor.GOLD+"Транспортер")
            .addEnchant(Enchantment.ARROW_INFINITE,1)
            .addTextNBT("transporter","transporter")
            .compile();

    public static void craft(){
        new Craft(
                List.of(
                        "ABA",
                        "CDC",
                        "EFE"
                ),
                List.of(
                        new CraftItem('A', Material.NETHER_STAR),
                        new CraftItem('B', Material.ELYTRA),
                        new CraftItem('C', Material.ENDER_EYE),
                        new CraftItem('D', Material.RECOVERY_COMPASS),
                        new CraftItem('E', Material.DIAMOND_BLOCK),
                        new CraftItem('F', Material.LODESTONE)
                ),
                transporter
        );
    }
    @EventHandler public void openTeleportTable(PlayerInteractEvent e){
        if (!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        TeleportMenu teleportMenu = new TeleportMenu()

    }
}

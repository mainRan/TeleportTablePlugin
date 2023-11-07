package me.terramain.teleporttableplugin;

import me.terramain.guiapi.GUIPlugin;
import me.terramain.teleporttableplugin.teleportTable.Config;
import me.terramain.teleporttableplugin.teleportTable.TableLogic;
import me.terramain.teleporttableplugin.transporter.TransporterLogic;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin {
    public static Main plugin;
    public static GUIPlugin guiPlugin;

    @Override
    public void onEnable() {
        plugin=this;
        guiPlugin=new GUIPlugin(this);

        TableLogic.craft();
        TransporterLogic.craft();
        Bukkit.getPluginManager().registerEvents(new TableLogic(),this);

        try {
            Config.loadVirusBlocks();
        } catch (IOException e) {e.printStackTrace();}
    }

    @Override
    public void onDisable() {
        try {
            Config.saveVirusBlocks();
        } catch (IOException e) {e.printStackTrace();}
    }
}

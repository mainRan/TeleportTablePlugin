package me.terramain.teleporttableplugin.teleportTable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TeleportTable {
    private final Location location;
    private TablePassword password;

    public TeleportTable(Location location, TablePassword password) {
        this.location = location;
        this.password = password;
    }
    public TeleportTable(Location location) {
        this.location = location;
    }
    public TeleportTable(String line) {
        String[] args = line.split(" ");
        this.location = new Location(
                Bukkit.getWorld(args[0]),
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
        );
        this.password = new TablePassword(
                Material.getMaterial(args[4]),
                Material.getMaterial(args[5]),
                Material.getMaterial(args[6])
        );
    }

    public Location getLocation() {return location;}
    public TablePassword getPassword() {return password;}

    public void setPassword(TablePassword password) {this.password = password;}

    @Override
    public String toString() {
        return
                location.getWorld().getName()+" "+
                        location.getBlockX()+" "+
                        location.getBlockY()+" "+
                        location.getBlockZ()+" "+
                        password.item1.toString()+" "+
                        password.item2.toString()+" "+
                        password.item3.toString()
                ;
    }

    public void teleportPlayer(Player player){
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    Block block1 = location.clone().add(x,y,z).getBlock();
                    Block block2 = location.clone().add(x,y+1,z).getBlock();
                    if (block1.getType().isTransparent() && block2.getType().isTransparent()){
                        player.teleport(block1.getLocation().add(0.5,0.5,0.5).setDirection(player.getLocation().getDirection()));
                        player.sendMessage("телепортация...");
                        return;
                    }
                    else if (block2.isLiquid()){
                        Block block3 = block2;
                        while (block3.isLiquid()){//вверх по тикучему блоку
                            block3 = block3.getLocation().add(0,1,0).getBlock();
                        }
                        Block block4 = block3.getLocation().add(0,1,0).getBlock();
                        if (block4.getType().isTransparent() || block4.isLiquid()) {//блок над блоком над водой пустотный?
                            player.teleport(block3.getLocation().add(0.5,0,0.5).setDirection(player.getLocation().getDirection()));
                            player.sendMessage("телепортация!");
                            return;
                        }
                    }
                }
            }
        }
        player.sendMessage("упс! кажется алтарь заблокирован блоками со всех сторон!");
    }
}

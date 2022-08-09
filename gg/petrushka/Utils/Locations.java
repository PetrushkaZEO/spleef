package gg.petrushka.Utils;

import jdk.nashorn.internal.ir.BaseNode;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Locations {

    private static Location lobbyLocation;

    public static void teleportToLobby(Player player){
        Bukkit.createWorld(new WorldCreator("spleef"));
        lobbyLocation = new Location(Bukkit.getWorld("spleef"), 100, 100, 100);
        player.teleport(lobbyLocation);
        for(int i = 0; i < player.getInventory().getSize(); i++){
            player.getInventory().setItem(i, new ItemStack(Material.AIR));
            player.getInventory().setItem(0, new ItemStack(Material.COMPASS));
        }
    }
}

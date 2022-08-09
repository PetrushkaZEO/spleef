package gg.petrushka.Utils;

import gg.petrushka.Arena.Compass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class LobbyEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            player.getInventory().setItem(i, new ItemStack(Material.AIR));
            player.getInventory().setItem(0, new ItemStack(Material.COMPASS));
            Locations.teleportToLobby(player);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Player player = e.getPlayer();
        if(!player.getInventory().getItemInMainHand().equals(new ItemStack(Material.COMPASS))) return;
        Compass.openArenasMenu(player);
    }
}

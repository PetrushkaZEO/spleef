package gg.petrushka.Arena;

import gg.petrushka.GameLogic.Game;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;

public class MenuEvents implements Listener {

    @EventHandler
    public void clickIcon(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        if(!inv.equals(Compass.inv)) return;
        int slot = e.getSlot();
        if(slot + 1 > ArenasList.arenaList.size()) return;
        if(ArenasList.arenaList.get(slot).getPlayersOnArena().contains(player)){
            player.closeInventory();
            return;
        }
        for(int i = 0; i < ArenasList.arenaList.size(); i++) {
            Arena arena = ArenasList.arenaList.get(i);
            if(arena.getPlayersOnArena().contains(player)){
                arena.kickFromArena(player);
            }
        }

        Arena arena = ArenasList.arenaList.get(slot);
        if(arena.getPlayersOnArena().size() >= arena.getMaxPlayersOnArena()) return;
        if(arena.getArenaState() == ArenaState.GAME){
            player.sendMessage(ChatColor.RED + "Игра на этой арене уже идёт!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 10, 10);
            return;
        }

        arena.joinArena(player);
        if(arena.getPlayersOnArena().size() == arena.getMinimumPlayersOnArena()){
            Game.start(arena);
            return;
        }

        player.closeInventory();
    }

    @EventHandler
    public void dragIcon(InventoryDragEvent e){
        if(!e.getInventory().equals(Compass.inv)) return;
        e.setCancelled(true);
    }

  @EventHandler
    public void switchIcon(InventoryPickupItemEvent e){
        if(!e.getInventory().equals(Compass.inv)) return;
        e.setCancelled(true);
  }
}

package gg.petrushka.Arena;

import gg.petrushka.GameLogic.Game;
import gg.petrushka.Utils.Locations;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ArenaEvents implements Listener {

    @EventHandler
    public void snowBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (!block.getType().equals(Material.SNOW_BLOCK)) return;
        e.setDropItems(false);
    }

    @EventHandler
    public void fall(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.getLocation().getBlockY() > 167) return;
        for (int i = 0; i < ArenasList.arenaList.size(); i++) {
            Arena arena = ArenasList.arenaList.get(i);
            if (arena.getPlayersOnArena().contains(player)) {
                Game.round(player, arena);
                return;
            }
        }
    }

    @EventHandler
    public void fallInLobby(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.getLocation().getBlockY() > 70) return;
        for (int i = 0; i < ArenasList.arenaList.size(); i++) {
            Arena arena = ArenasList.arenaList.get(i);
            if (arena.getPlayersOnArena().contains(player)) {
                return;
            }
        }
        Locations.teleportToLobby(player);
    }

    @EventHandler
    public void noDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void quitEvent(PlayerQuitEvent e) {
        Player loser = e.getPlayer();
        for (int i = 0; i < ArenasList.arenaList.size(); i++) {
            Arena arena = ArenasList.arenaList.get(i);
            if (arena.getPlayersOnArena().contains(loser)) {
                for (int a = 0; a < arena.getPlayersOnArena().size(); a++) {
                    Player winner = arena.getPlayersOnArena().get(a);
                    if(winner == loser) return;
                    Game.end(arena, winner, loser);
                }
            }
        }
    }
}

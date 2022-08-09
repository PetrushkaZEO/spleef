package gg.petrushka.GameLogic;


import gg.petrushka.Arena.Arena;
import gg.petrushka.Arena.ArenaManager;
import gg.petrushka.Arena.ArenaState;
import gg.petrushka.Arena.ArenasList;
import gg.petrushka.Utils.Locations;
import gg.petrushka.Utils.SpleefScoreBoard;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ConcurrentHashMap;

public class Game {

    private static Location firstPlayerSpawnLocation;
    private static Location secondPlayerSpawnLocation;
    private static ConcurrentHashMap<Player, Integer> score = new ConcurrentHashMap<>();

    public static void start(Arena arena){
        if(arena.equals(ArenasList.arenaList.get(0))){
            arena.regenArena(10, 10);
            firstPlayerSpawnLocation = new Location(Bukkit.getWorld("spleef"), 11, 171, 19);
            firstPlayerSpawnLocation.setYaw(-90);
            firstPlayerSpawnLocation.setPitch(0);
            secondPlayerSpawnLocation = new Location(Bukkit.getWorld("spleef"), 49, 171, 19);
            secondPlayerSpawnLocation.setYaw(90);
            secondPlayerSpawnLocation.setPitch(0);
        } else {
            arena.regenArena(200, 200);
            firstPlayerSpawnLocation = new Location(Bukkit.getWorld("spleef"), 201, 171, 209);
            firstPlayerSpawnLocation.setYaw(-90);
            firstPlayerSpawnLocation.setPitch(0);
            secondPlayerSpawnLocation = new Location(Bukkit.getWorld("spleef"), 239, 171, 209);
            secondPlayerSpawnLocation.setYaw(90);
            secondPlayerSpawnLocation.setPitch(0);
        }

        ArenaManager.arenaMessage(arena, ChatColor.GREEN + "Игра началась! Останься последним на платформе, чтобы победить!");
        ArenaManager.arenaSound(arena, Sound.BLOCK_NOTE_PLING);
        arena.setArenaState(ArenaState.GAME);
        newStep(arena);
    }

    private static void getGameItems(Player player){
        for(int i = 0; i < player.getInventory().getSize(); i++){
            player.getInventory().setItem(i, new ItemStack(Material.AIR));
            player.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SPADE));
        }
    }



    public static void end(Arena arena, Player winner, Player loser){
        winner.sendTitle(ChatColor.GREEN + "Победа!", "", 10, 20, 10);
        loser.sendTitle(ChatColor.RED + "Поражение", "", 10, 20, 10);
        GameStats.score.remove(winner);
        GameStats.score.remove(loser);
        arena.getPlayersOnArena().remove(winner);
        arena.getPlayersOnArena().remove(loser);
        Locations.teleportToLobby(winner);
        Locations.teleportToLobby(loser);
        arena.setArenaState(ArenaState.OFFLINE);
        SpleefScoreBoard.clearSb(winner, loser);
    }

    public static void end(Arena arena, Player winner){
        winner.sendTitle(ChatColor.GREEN + "Победа!", "", 10, 20, 10);
        GameStats.score.remove(winner);
        arena.getPlayersOnArena().remove(winner);
        Locations.teleportToLobby(winner);
        arena.setArenaState(ArenaState.OFFLINE);
    }

    private static void newStep(Arena arena){
        Player firstPlayer = arena.getPlayersOnArena().get(0);
        Player secondPlayer = arena.getPlayersOnArena().get(1);
        if(arena.equals(ArenasList.arenaList.get(0))) {
            arena.regenArena(10, 10);
        } else {
            arena.regenArena(200, 200);
        }
        firstPlayer.teleport(firstPlayerSpawnLocation);
        secondPlayer.teleport(secondPlayerSpawnLocation);
        getGameItems(firstPlayer);
        getGameItems(secondPlayer);
        SpleefScoreBoard.updateSb(firstPlayer, secondPlayer);
    }

    public static void round(Player player, Arena arena){
        for(int i = 0; i < arena.getPlayersOnArena().size(); i++) {
            Player alive = arena.getPlayersOnArena().get(i);
            if (!alive.equals(player)) {
                int y = alive.getLocation().getBlockY();
                if (player.getLocation().getBlockY() < y) {
                    newStep(arena);
                    GameStats.addScore(alive);
                    SpleefScoreBoard.updateSb(player, alive);
                    if (GameStats.score.get(alive) >= 3) {
                        end(arena, alive, player);
                    }
                }
            }
        }
    }
}

package gg.petrushka.Arena;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class ArenaManager {

    public static void arenaSound(Arena arena, Sound sound) {
        for (Player player : arena.getPlayersOnArena()) {
            if (player != null) {
                player.playSound(player.getLocation(), sound, 10, 10);
            }
        }
    }

    public static void arenaMessage(Arena arena, String message) {
        for (Player player : arena.getPlayersOnArena()) {
            if (player != null) {
                player.sendMessage(message);
            }
        }
    }
}

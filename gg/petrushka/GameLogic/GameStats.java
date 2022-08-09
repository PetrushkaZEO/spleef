package gg.petrushka.GameLogic;

import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

public class GameStats {

    public static ConcurrentHashMap<Player, Integer> score = new ConcurrentHashMap<>();

    public static void addScore(Player player){
        int a = score.getOrDefault(player, 0);
        a++;
        score.put(player, a);
    }
}

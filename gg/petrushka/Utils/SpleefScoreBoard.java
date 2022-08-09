package gg.petrushka.Utils;

import gg.petrushka.GameLogic.GameStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class SpleefScoreBoard {

    public static void clearSb(Player player, Player player1){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        player.setScoreboard(scoreboard);
        player1.setScoreboard(scoreboard);
    }
    public static void updateSb(Player player, Player player1){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("AncientValley", "");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW + " Счёт ");

        Score countPlayer = objective.getScore(ChatColor.WHITE + player.getName() + ": " + GameStats.score.getOrDefault(player, 0));
        Score countOpponent = objective.getScore(ChatColor.WHITE + player1.getName() + ": " + GameStats.score.getOrDefault(player1, 0));

        countPlayer.setScore(1);
        countOpponent.setScore(0);

        player.setScoreboard(scoreboard);
        player1.setScoreboard(scoreboard);
    }
}

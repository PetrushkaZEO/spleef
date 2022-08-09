package gg.petrushka.Arena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private String name;
    private List<Player> playersOnArena = new ArrayList<>();
    private int maxPlayersOnArena;
    private int minimumPlayersOnArena;

    private ArenaState arenaState;


    public Arena(String name, int maxPlayersOnArena, int minimumPlayersOnArena, ArenaState arenaState) {
        this.name = name;
        this.maxPlayersOnArena = maxPlayersOnArena;
        this.minimumPlayersOnArena = minimumPlayersOnArena;
        this.arenaState = arenaState;
    }

    public ArenaState getArenaState() {
        return arenaState;
    }

    public void setArenaState(ArenaState arenaState) {
        this.arenaState = arenaState;
    }


    public void regenArena(int x, int z){
        int a = x + 41;
        int b = x + 19;
        for (int i = x; i < a; i++){
            for(int j = z; j < b; j++){
                Bukkit.getWorld("spleef").getBlockAt(i, 170, j).setType(Material.SNOW_BLOCK);
            }
        }
    }

    public void joinArena(Player player){
        if(playersOnArena.contains(player)) return;
        playersOnArena.add(player);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 10);
        player.sendMessage(ChatColor.GREEN + "Вы успешно встали в очередь!");
    }

    public void kickFromArena(Player player){
        if(!playersOnArena.contains(player)) return;
        playersOnArena.remove(player);
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 10, 10);
        player.sendMessage(ChatColor.RED + "Вы покинули очередь!");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayersOnArena() {
        return playersOnArena;
    }

    public void setPlayersOnArena(List<Player> playersOnArena) {
        this.playersOnArena = playersOnArena;
    }

    public int getMaxPlayersOnArena() {
        return maxPlayersOnArena;
    }

    public void setMaxPlayersOnArena(int maxPlayersOnArena) {
        this.maxPlayersOnArena = maxPlayersOnArena;
    }

    public int getMinimumPlayersOnArena() {
        return minimumPlayersOnArena;
    }

    public void setMinimumPlayersOnArena(int minimumPlayersOnArena) {
        this.minimumPlayersOnArena = minimumPlayersOnArena;
    }
}

package gg.petrushka;

import gg.petrushka.Arena.ArenaEvents;
import gg.petrushka.Arena.ArenasList;
import gg.petrushka.Arena.Compass;
import gg.petrushka.Arena.MenuEvents;
import gg.petrushka.GameLogic.Game;
import gg.petrushka.Utils.LobbyEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Spleef extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println("Я родился!");

        Bukkit.getPluginManager().registerEvents(new MenuEvents(), this);
        Bukkit.getPluginManager().registerEvents(new LobbyEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ArenaEvents(), this);

        ArenasList.createArenas();
        Compass.menu();


    }

    @Override
    public void onDisable() {

    }
}

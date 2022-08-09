package gg.petrushka.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenasList {

    public static List<Arena> arenaList = new ArrayList<>();

    public static void createArenas(){
        Arena arena = new Arena("1 арена", 2, 2, ArenaState.OFFLINE);
        Arena arena1 = new Arena("2 арена", 2, 2, ArenaState.OFFLINE);

        arenaList.add(arena);
        arenaList.add(arena1);
    }
}

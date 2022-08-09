package gg.petrushka.Arena;

import gg.petrushka.Utils.Icon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Compass implements Listener {

   public static Inventory inv;

   public static void menu(){inv = Bukkit.createInventory(null, 54, "Арены");}


   public static String arenaStateToString(ArenaState arenaState){
       String state = null;
       switch (arenaState){
           case GAME:
               state = ChatColor.RED + "В игре";
               break;
           case OFFLINE:
               state = ChatColor.GREEN + "Свободна";
               break;
       }

       return state;
   }
    public static void openArenasMenu(Player player){


        for(int i = 0; i < ArenasList.arenaList.size(); i++){
            Arena arena = ArenasList.arenaList.get(i);
            Icon icon = new Icon(
                    new ItemStack(Material.GLASS, 1, (short) 14),
                    ChatColor.WHITE + arena.getName(),
                    Arrays.asList(
                            ChatColor.YELLOW + "Состояние: " + ChatColor.WHITE + arenaStateToString(arena.getArenaState()),
                           ChatColor.YELLOW + "Игроков в комнате: ",
                            ChatColor.WHITE + "" + arena.getPlayersOnArena().size() + "/" + arena.getMaxPlayersOnArena()
                    ),
                    arena,
                    i
            );
            inv.setItem(icon.getPosition(), new ItemStack(icon.getIcon()));
            player.openInventory(inv);
        }
    }
}

package gg.petrushka.Utils;

import gg.petrushka.Arena.Arena;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Icon {

    private ItemStack icon;
    private String name;
    private List<String> lore = new ArrayList<>();
    Arena arena;
    int position;

    public Icon(ItemStack icon, String name, List<String> lore, Arena arena, int position) {
        this.icon = icon;
        this.name = name;
        this.lore = lore;
        this.arena = arena;
        this.position = position;

        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        icon.setItemMeta(meta);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }


    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }
}

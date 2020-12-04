package me.yuval270.nbtstats.listeners;

import lombok.RequiredArgsConstructor;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

@RequiredArgsConstructor
public class InventoryManipulationListener implements Listener {
    private final NbtStats main;

    @EventHandler
    public void inventoryClickListener(InventoryClickEvent event){
        PlayerStats stats = main.getStatsManager().getPlayer((Player) event.getWhoClicked());
        if (stats != null)
            stats.updateStats();
    }

    @EventHandler
    public void inventoryDragListener(InventoryDragEvent event){
        PlayerStats stats = main.getStatsManager().getPlayer((Player) event.getWhoClicked());
        if (stats != null)
            stats.updateStats();
    }
}

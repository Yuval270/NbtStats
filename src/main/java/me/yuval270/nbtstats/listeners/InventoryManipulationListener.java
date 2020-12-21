package me.yuval270.nbtstats.listeners;

import lombok.RequiredArgsConstructor;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

@RequiredArgsConstructor
public class InventoryManipulationListener implements Listener {
    private final NbtStats main;

    @EventHandler
    public void inventoryClickListener(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        PlayerStats stats = main.getStatsManager().getPlayer(player.getUniqueId());
        if (stats != null)
            stats.updateStats();
    }

    @EventHandler
    public void inventoryDragListener(InventoryDragEvent event){
        Player player = (Player) event.getWhoClicked();
        PlayerStats stats = main.getStatsManager().getPlayer(player.getUniqueId());
        if (stats != null)
            stats.updateStats();
    }
    @EventHandler
    public void inventoryDragListener(PlayerItemHeldEvent event){
        PlayerStats stats = main.getStatsManager().getPlayer(event.getPlayer().getUniqueId());
        if (stats != null)
            stats.updateStats();
    }
}

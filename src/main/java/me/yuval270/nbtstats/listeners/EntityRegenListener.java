package me.yuval270.nbtstats.listeners;

import lombok.RequiredArgsConstructor;
import me.yuval270.nbtstats.NbtStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

@RequiredArgsConstructor
public class EntityRegenListener implements Listener {
    private final NbtStats main;

    @EventHandler
    public void onRegen(EntityRegainHealthEvent event){
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

}

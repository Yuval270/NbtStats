package me.yuval270.nbtstats.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.RequiredArgsConstructor;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.PlayerStats;
import me.yuval270.nbtstats.stats.info.Stats;
import me.yuval270.nbtstats.stats.types.AllStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class PlayerInteractListener implements Listener {
    private final NbtStats main;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        PlayerStats stats = main.getStatsManager().getPlayer(player);

        if (item != null && stats != null) {

            AllStats allStats = stats.getAllStats();
            NBTItem nbtItem = new NBTItem(item);

            if (nbtItem.hasKey(Stats.NBT_CLASS))
                if (!player.hasPermission(nbtItem.getString(Stats.NBT_CLASS))) {
                    event.setCancelled(true);
                    return;
                }
            if (nbtItem.hasKey(Stats.NBT_LEVEL_DUMMY)){
                int requiredLevel = nbtItem.getInteger(Stats.NBT_LEVEL_DUMMY);
                double level = allStats.getLevelStat().getValue();
                if (level < requiredLevel)
                    event.setCancelled(true);

            }

        }
    }
}

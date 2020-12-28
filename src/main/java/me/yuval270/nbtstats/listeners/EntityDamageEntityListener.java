package me.yuval270.nbtstats.listeners;

import lombok.RequiredArgsConstructor;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.PlayerStats;
import me.yuval270.nbtstats.stats.types.AllStats;
import me.yuval270.nbtstats.stats.types.WeaponStats;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

@RequiredArgsConstructor
public class EntityDamageEntityListener implements Listener {
    private final NbtStats main;

    @EventHandler
    public void onEntityDamageEntityListener(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if (damager instanceof Player && !(damaged instanceof Player)) { // player attacks
            Player player = (Player) damager;
            PlayerStats stats = main.getStatsManager().getPlayer(player.getUniqueId());
            if (stats != null) {
                WeaponStats weaponStats = stats.getWeaponStats();
                double finalDamage = weaponStats.getFinalDamage();
                double lifeSteal = weaponStats.getLifeStealAmount(finalDamage);
                event.setDamage(finalDamage);
                stats.updateHealth(player.getHealth() + lifeSteal);
            }
            return;
        }

        if (damaged instanceof Player && !(damager instanceof Player)) {
            Player player = (Player) damaged;
            PlayerStats stats = main.getStatsManager().getPlayer(player.getUniqueId());
            if (stats != null) {
                AllStats allStats = stats.getAllStats();
                if (allStats.canDodge()) {
                    event.setCancelled(true);
                    return;
                }
                double defense = allStats.getDefenseStat().getValue();
                double damage = (event.getDamage() - defense) * allStats.getResistanceStat().getValue();
                if (damage <= 0){
                    event.setCancelled(true);
                    return;
                }
                if (allStats.canBlock())
                    damage = damage / 2;
                // TODO
                event.setDamage(0);
                stats.removeHealth(damage);

            }
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            PlayerStats stats = main.getStatsManager().getPlayer(player.getUniqueId());
            if (stats != null){
                stats.updateHealth(player.getHealth() - event.getDamage());

            }
        }

    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        PlayerStats stats = main.getStatsManager().getPlayer(event.getPlayer().getUniqueId());
        if (stats != null)
           stats.reset();
    }

}
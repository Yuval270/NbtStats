package me.yuval270.nbtstats.listeners;

import lombok.RequiredArgsConstructor;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.PlayerStats;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {
    private final NbtStats main;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL)
            main.getStatsManager().addPlayer(player);
    }

    @EventHandler
    public void gamemodeSwitch(PlayerGameModeChangeEvent event) {
        if (event.getNewGameMode() == GameMode.SURVIVAL)
            main.getStatsManager().addPlayer(event.getPlayer());
        else
            main.getStatsManager().removePlayer(event.getPlayer().getUniqueId());
    }
}


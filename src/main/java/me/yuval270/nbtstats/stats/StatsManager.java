package me.yuval270.nbtstats.stats;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.PlayerStats;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatsManager {
    private Set<PlayerStats> playerStats = new HashSet<>();

    private final NbtStats main;
    public StatsManager(NbtStats main){
        this.main = main;
    }

    public PlayerStats getPlayer(Player player){
        return playerStats.stream().filter(playerStats1 -> player.equals(playerStats1.getPlayer())).findFirst().orElse(null);
    }
    public void addPlayer(Player player){
        if (getPlayer(player) != null)
            playerStats.add(new PlayerStats(player, main));
    }
    public boolean removePlayer(Player player){
        return playerStats.removeIf(playerStats1 -> playerStats1.getPlayer().equals(player));
    }

}

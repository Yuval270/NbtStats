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
import java.util.UUID;

public class StatsManager {
    //private Set<PlayerStats> playerStats = new HashSet<>();
    private BiMap<UUID, PlayerStats> playerStats = HashBiMap.create();
    private final NbtStats main;
    public StatsManager(NbtStats main){
        this.main = main;
    }

    public PlayerStats getPlayer(UUID uuid){
        return playerStats.get(uuid);
        
       // return playerStats.stream().filter(playerStats1 -> player.equals(playerStats1.getPlayer())).findFirst().orElse(null);
    }
    public void addPlayer(Player player){
        playerStats.put(player.getUniqueId(), new PlayerStats(player, main));
        System.out.println(playerStats.containsKey(player.getUniqueId()));
        //  if (getPlayer(player) != null
         //  playerStats.add(new PlayerStats(player, main));
    }
    public void removePlayer(UUID uuid){
        PlayerStats stats = getPlayer(uuid);
        if (stats != null){
            stats.cancel();
            playerStats.remove(uuid);

        }

     //   return playerStats.removeIf(playerStats1 -> playerStats1.getPlayer().equals(player));
    }

}

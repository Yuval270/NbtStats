package me.yuval270.nbtstats;

import lombok.Getter;
import me.yuval270.nbtstats.listeners.*;
import me.yuval270.nbtstats.stats.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public final class NbtStats extends JavaPlugin {
    public static final Random random = new Random();
    @Getter
    private StatsManager statsManager;
    // TODO speed

    @Override
    public void onEnable() {
        saveDefaultConfig();
        statsManager = new StatsManager(this);
        loadEvents();
        getCommand("nbt").setExecutor(new NbtCmd(this));
    }
    private void loadEvents(){
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageEntityListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryManipulationListener(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityRegenListener(this), this);

    }

}

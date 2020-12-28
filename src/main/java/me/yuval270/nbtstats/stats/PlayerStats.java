package me.yuval270.nbtstats.stats;

import lombok.Getter;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.types.AllStats;
import me.yuval270.nbtstats.stats.types.ArmorStats;
import me.yuval270.nbtstats.stats.types.WeaponStats;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PlayerStats {
    private final NbtStats main;
    @Getter
    private WeaponStats weaponStats;
    @Getter
    private ArmorStats armorStats;
    @Getter
    private AllStats allStats;
    @Getter
    private double health;
    @Getter
    private Player player;
    BukkitTask regen;

    public PlayerStats(Player player, final NbtStats main) {
        this.player = player;
        weaponStats = new WeaponStats(player);
        armorStats = new ArmorStats(player);
        allStats = new AllStats(player);
        double proportion = getMaxHealth() / 20;
        this.health = Math.ceil(player.getHealth() * proportion);
        this.main = main;
        regen();
    }


    public double getMaxHealth() {
        return armorStats.getHealthStat().getValue() * armorStats.getConstitutionStat().getValue();
    }


    public void updateHealth(double health){
        double proportion = getMaxHealth() / 20;
        this.health = Math.ceil(health * proportion);
        player.setHealth(health);
    }

    public void removeHealth(double hp) {
        health -= hp;
        if (health <= 0)
            player.setHealth(0);
        else {
            double proportion = getMaxHealth() / 20;
            player.setHealth(Math.ceil(health / proportion));
        }
    }
    public void updateStats(){
        weaponStats = new WeaponStats(player);
        armorStats = new ArmorStats(player);
        allStats = new AllStats(player);

        weaponStats = new WeaponStats(player);
        armorStats = new ArmorStats(player);
        allStats = new AllStats(player);

        double proportion = getMaxHealth() / 20;
        double newHealth = Math.ceil(player.getHealth() * proportion);
        if (health > newHealth)
            player.setHealth(newHealth);
    }

    public void reset() {
        weaponStats = new WeaponStats(player);
        armorStats = new ArmorStats(player);
        allStats = new AllStats(player);
        health = getMaxHealth();
    }
    public void cancel(){
        regen.cancel();
    }

    private void regen() {
         regen = new BukkitRunnable() {
            @Override
            public void run() {
                double maxHealth = getMaxHealth();
                //System.out.println("vitality " + allStats.getVitalityStat().getValue());
                //System.out.println("max health" + maxHealth);
                //System.out.println("regen stat " + maxHealth * allStats.getHealthRegenStat().getValue());
                double amountToRegen = allStats.getVitalityStat().getValue() + maxHealth * allStats.getHealthRegenStat().getValue();
                //System.out.println("a,mount to regen" + amountToRegen);
                //System.out.println("helth " + health);
                //System.out.println("player current hleath " + player.getHealth());
                if (health + amountToRegen > maxHealth){
                    health = maxHealth;
                    //System.out.println(true);
                }
                else
                    health += amountToRegen;
                double proportion =  Math.ceil(maxHealth / 20);
                //System.out.println("new health " + health);
                //System.out.println("new health final" + Math.ceil(health / proportion));
                player.setHealth(Math.ceil(health / proportion));
                for (int i = 0; i < 3; i++) {
                    //System.out.println(" ");
                }
            }
        }.runTaskTimer(main, 50, 50);
    }


}

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

    public void addHealth(double hp) {
        health += hp;
        double proportion = getMaxHealth() / 20;
        player.setHealth(Math.ceil(health / proportion));
    }
    public void updateHealth(){
        double proportion = getMaxHealth() / 20;
        this.health = Math.ceil(player.getHealth() * proportion);
    }

    public void removeHealth(double hp) {
        health -= hp;
        if (health <= 0)
            player.setHealth(0);
        else {
            int proportion = (int) getMaxHealth() / 20;
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
                double amountToRegen = allStats.getVitalityStat().getValue() + maxHealth * allStats.getHealthRegenStat().getValue();
                if (health + amountToRegen > maxHealth)
                    health = maxHealth;
                else
                    health += amountToRegen;
                int proportion = (int) Math.ceil(getMaxHealth() / 20);
                System.out.println(proportion);
                System.out.println(health);
                player.setHealth(Math.ceil(health / proportion));

            }
        }.runTaskTimer(main, 50, 50);
    }


}

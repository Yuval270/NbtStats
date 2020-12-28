package me.yuval270.nbtstats.stats.types;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.info.Stat;
import me.yuval270.nbtstats.stats.info.Stats;
import org.bukkit.entity.Player;

import java.util.Random;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import me.yuval270.nbtstats.stats.info.Stat;
import me.yuval270.nbtstats.stats.info.Stats;
import org.bukkit.entity.Player;

import java.util.Random;

@Getter
public class WeaponStats {
    private Stat minDamageStat;
    private Stat maxDamageStat;
    private Stat percentDamageStat;

    private Stat critDamageStat;
    private Stat critChanceStat;
    private Stat critFactorStat;

    private Stat lifeStealStat;
    private Stat lifeStealChanceStat;

    private BiMap<String, Stat> stats = HashBiMap.create();

    public WeaponStats(Player player) {

        minDamageStat = new Stat(Stats.NBT_MIN_DAMAGE, player, Target.WEAPON, Stats.DEFAULT_MIN_DAMAGE);
        maxDamageStat = new Stat(Stats.NBT_MAX_DAMAGE, player, Target.WEAPON, Stats.DEFAULT_MAX_DAMAGE);
        percentDamageStat = new Stat(Stats.NBT_PERCENT_DAMAGE, player, Target.WEAPON, Stats.DEFAULT_PERCENT_DAMAGE);

        critDamageStat = new Stat(Stats.NBT_CRIT_DAMAGE, player, Target.WEAPON, Stats.DEFAULT_CRIT_DAMAGE);
        critChanceStat = new Stat(Stats.NBT_CRIT_CHANCE, player, Target.WEAPON, Stats.DEFAULT_CRIT_CHANCE);
        critFactorStat = new Stat(Stats.NBT_CRIT_FACTOR, player, Target.WEAPON, Stats.DEFAULT_CRIT_FACTOR);

        lifeStealStat = new Stat(Stats.NBT_LIFE_STEAL, player, Target.WEAPON, Stats.DEFAULT_LIFE_STEAL);
        lifeStealChanceStat = new Stat(Stats.NBT_LIFE_STEAL_CHANCE, player, Target.WEAPON, Stats.DEFAULT_LIFE_STEAL_CHANCE);
    }


    public double getRandomDamageInRange() {
        return ((Math.random() * (maxDamageStat.getValue() - minDamageStat.getValue())) + minDamageStat.getValue());
    }

    public double getCritDamage() {
        double critDamage = 0;

        int chance = NbtStats.random.nextInt(100 - 1) + 1;
        if (critChanceStat.getValue() >= chance)
            critDamage = critDamageStat.getValue();
        return critDamage * critFactorStat.getValue();
    }

    public double getFinalDamage() {
        return getRandomDamageInRange() * percentDamageStat.getValue() + getCritDamage();
    }

    public double getLifeStealAmount(double finalDamage) {
        double lifeSteal = 0;

        int chance = NbtStats.random.nextInt(100 - 1) + 1;
        if (lifeStealChanceStat.getValue() >= chance)
            lifeSteal = finalDamage * lifeStealStat.getValue();
        return lifeSteal;
    }
}
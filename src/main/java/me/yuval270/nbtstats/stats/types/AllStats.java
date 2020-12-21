package me.yuval270.nbtstats.stats.types;

import lombok.Getter;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.info.Stat;
import me.yuval270.nbtstats.stats.info.Stats;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

@Getter
public class AllStats {
    private Stat defenseStat;
    private Stat resistanceStat;

    private Stat healthRegenStat;
    private Stat vitalityStat;

    private Stat dodgeStat;
    private Stat blockStat;

    private Stat levelStat;
    private Stat speedStat;

    public AllStats(Player player) {
        defenseStat = new Stat(Stats.NBT_DEFENSE, player, Target.ALL, Stats.DEFAULT_DEFENSE);
        resistanceStat = new Stat(Stats.NBT_RESISTANCE, player, Target.ALL, Stats.DEFAULT_RESISTANCE);

        healthRegenStat = new Stat(Stats.NBT_HEALTH_REGEN, player, Target.ALL, Stats.DEFAULT_HEALTH_REGEN);
        vitalityStat = new Stat(Stats.NBT_VITALITY, player, Target.ALL, Stats.DEFAULT_VITALITY);

        dodgeStat = new Stat(Stats.NBT_DODGE, player, Target.ALL, Stats.DEFAULT_DODGE);
        blockStat = new Stat(Stats.NBT_BLOCK, player, Target.ALL, Stats.DEFAULT_BLOCK);

        speedStat = new Stat(Stats.NBT_SPEED, player, Target.ALL, 0);
        levelStat = new Stat(Stats.NBT_LEVEL, player, Target.ALL, 0);

        updateSpeed(player);

    }

    public void updateSpeed(Player player) {
      //  player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speedStat.getValue());

    }

    public boolean canDodge() {
        if (dodgeStat.isCooldown())
            return false;
        int chance = NbtStats.random.nextInt(100 - 1) + 1;
        if (chance <= dodgeStat.getValue()) {
            dodgeStat.startCooldown(Stats.DODGE_COOLDOWN);
            return true;
        }
        return false;
    }

    public boolean canBlock() {
        int chance = NbtStats.random.nextInt(100 - 1) + 1;
        return chance <= blockStat.getValue();
    }

}
package me.yuval270.nbtstats.stats.types;

import lombok.Getter;
import me.yuval270.nbtstats.stats.info.Stat;
import me.yuval270.nbtstats.stats.info.Stats;
import org.bukkit.entity.Player;
@Getter
public class ArmorStats {
    private Stat healthStat;
    private Stat constitutionStat;

    public ArmorStats(Player player) {
        healthStat = new Stat(Stats.NBT_HEALTH, player, Target.ARMOR, Stats.DEFAULT_HEALTH);
        constitutionStat = new Stat(Stats.NBT_CONSTITUTION, player, Target.ARMOR, Stats.DEFAULT_CONSTITUTION);
    }

}

package me.yuval270.nbtstats.stats.info;

import me.yuval270.nbtstats.NbtStats;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.ItemStack;

public class Stats {
    private static final FileConfiguration config = NbtStats.getPlugin(NbtStats.class).getConfig();

    public static double getValue(String path) {
        return config.getDouble(path);
    }

    // Armor nbt tags:
    public static final String NBT_HEALTH = "Health";
    public static final String NBT_CONSTITUTION = "Constitution";

    //Weapon nbt tags:
    public static final String NBT_MIN_DAMAGE = "MinDamage";
    public static final String NBT_MAX_DAMAGE = "MaxDamage";
    public static final String NBT_PERCENT_DAMAGE = "PercentDamage";

    public static final String NBT_CRIT_CHANCE = "CritChance";
    public static final String NBT_CRIT_DAMAGE = "CritDamage";
    public static final String NBT_CRIT_FACTOR = "CirtFactor";

    public static final String NBT_LIFE_STEAL = "LifeSteal";
    public static final String NBT_LIFE_STEAL_CHANCE = "LifeStealChance";

    // All nbt tags:
    public static final String NBT_DEFENSE = "Defense";
    public static final String NBT_RESISTANCE = "Resistance";

    public static final String NBT_HEALTH_REGEN = "HealthRegen";
    public static final String NBT_VITALITY = "Vitality";

    public static final String NBT_BLOCK = "Block";
    public static final String NBT_DODGE = "Dodge";

    public static final String NBT_LEVEL = "Level";
    public static final String NBT_LEVEL_DUMMY = "LevelDummy";

    public static final String NBT_CLASS = "Class";
    public static final String NBT_SPEED = "Level";
        
    
    
    //todo: changing it all into non static 
    // Armor default values:
 /*   public static final double DEFAULT_HEALTH = getValue("default_health");
    public static final double DEFAULT_CONSTITUTION = (getValue("default_constitution") + 100) / 100;

    // Weapon default values:
    public static final double DEFAULT_MIN_DAMAGE = getValue("default_min_damage");
    public static final double DEFAULT_MAX_DAMAGE = getValue("default_max_damage");
    public static final double DEFAULT_PERCENT_DAMAGE = (getValue("default_percent_damage") + 100) / 100;

    public static final double DEFAULT_CRIT_CHANCE = getValue("default_crit_change");
    public static final double DEFAULT_CRIT_DAMAGE = getValue("default_crit_damage");
    public static final double DEFAULT_CRIT_FACTOR = (getValue("default_crit_factor") + 100) / 100;
// TODO incase someone changes slot or shit
    public static final double DEFAULT_LIFE_STEAL = (getValue("default_life_steal") + 100) / 100;
    public static final double DEFAULT_LIFE_STEAL_CHANCE = getValue("default_life_steal_chance");

    // All default values:
    public static final double DEFAULT_DEFENSE = getValue("default_defense");
    public static final double DEFAULT_RESISTANCE = 1 - (getValue("default_resistance") / 100);

    public static final double DEFAULT_HEALTH_REGEN = getValue("default_health_regen") / 100;
    public static final double DEFAULT_VITALITY = getValue("default_vitality");

    public static final double DEFAULT_BLOCK = getValue("default_block");
    public static final double DEFAULT_DODGE = getValue("default_dodge");
    public static final int DODGE_COOLDOWN = config.getInt("dodge_cooldown") * 20;*/




}

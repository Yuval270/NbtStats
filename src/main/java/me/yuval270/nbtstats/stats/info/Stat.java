package me.yuval270.nbtstats.stats.info;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.types.Target;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class Stat {
    private final double defaultValue;
    private final String nbtTag;
    private final Target target;
    @Getter
    private boolean cooldown = false;
    @Getter
    private double value;


    public Stat(final String nbtTag, final Player player, final Target target, final double defaultValue) {
        this.nbtTag = nbtTag;
        this.target = target;
        this.defaultValue = defaultValue;
        value = defaultValue;
        updateStats(player);
    }
    public void startCooldown(int time) {
        cooldown = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown = false;
            }
        }.runTaskLater(NbtStats.getProvidingPlugin(NbtStats.class), time);
    }

    public void updateStats(final Player player) {
        value = defaultValue;
        if (target == Target.ARMOR)
            for (ItemStack item : player.getInventory().getArmorContents())
                checkItem(item, false);

        else if (target == Target.ALL) {
            for (ItemStack item : player.getInventory().getArmorContents())
                checkItem(item, false);
            checkItem(player.getInventory().getItemInMainHand(), true);
        }
         else if (target == Target.WEAPON)
            checkItem(player.getInventory().getItemInMainHand(), true);


    }
    private void checkItem(ItemStack item, boolean isWeapon){
        if (item != null){
            if (isWeapon && item.getType() == Material.WOODEN_PICKAXE){
                NBTItem nbtItem = new NBTItem(item);
                if (nbtItem.hasKey(nbtTag))
                    value += nbtItem.getDouble(nbtTag);
            }
            else if (!isWeapon && item.getType() != Material.AIR){
                NBTItem nbtItem = new NBTItem(item);
                if (nbtItem.hasKey(nbtTag))
                    value += nbtItem.getDouble(nbtTag);
            }
        }

    }

}

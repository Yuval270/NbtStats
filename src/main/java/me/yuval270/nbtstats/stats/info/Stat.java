package me.yuval270.nbtstats.stats.info;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.yuval270.nbtstats.NbtStats;
import me.yuval270.nbtstats.stats.types.Target;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Stat {
    private final double defaultValue;
    private final String nbtTag;
    private final Target target;
    @Getter
    private boolean coolDown = false;
    @Getter
    private double value;


    public Stat(final String nbtTag, final Player player, final Target target, final double defaultValue) {
        this.nbtTag = nbtTag;
        this.target = target;
        this.defaultValue = defaultValue;
        value = defaultValue;
        updateStats(player);
    }
    public void startCooldown(int time){
        coolDown = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                coolDown = false;
            }
        }.runTaskLater(NbtStats.getProvidingPlugin(NbtStats.class), time);
    }

    public void updateStats(final Player player) {
        value = defaultValue;
        if (target == Target.ARMOR) {
            for (ItemStack item : player.getInventory().getArmorContents())
                if (item != null) {
                    NBTItem nbtItem = new NBTItem(item);
                    if (nbtItem.hasKey(nbtTag))
                        value += nbtItem.getDouble(nbtTag);
                }
        }
        else if (target == Target.ALL) {
            for (ItemStack item : player.getInventory().getArmorContents()) {
                if (item != null) {
                    NBTItem nbtItem = new NBTItem(item);
                    if (nbtItem.hasKey(nbtTag))
                        value += nbtItem.getDouble(nbtTag);
                }
            }
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (itemInHand != null) {
                NBTItem nbtItem = new NBTItem(itemInHand);
                if (nbtItem.hasKey(nbtTag))
                    value += nbtItem.getDouble(nbtTag);
            }

        } else if (target == Target.WEAPON) {
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (itemInHand != null && itemInHand.getType() == Material.WOODEN_PICKAXE) {
                NBTItem nbtItem = new NBTItem(itemInHand);
                if (nbtItem.hasKey(nbtTag))
                    value += nbtItem.getDouble(nbtTag);
            }
        }
    }
}

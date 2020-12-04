package me.yuval270.nbtstats;

import lombok.RequiredArgsConstructor;
import me.yuval270.nbtstats.stats.PlayerStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class NbtCmd implements CommandExecutor {
    private final NbtStats main;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // nbt setkey
        // n bt showkeys

        if (args.length == 1 && args[0].equalsIgnoreCase("showkeys")){



        }
            if (args[0].equalsIgnoreCase("setkey")){

            }

        return false;
    }
    private void setKeys(Player player, ItemStack item){
        PlayerStats stats = main.getStatsManager().getPlayer(player);
        if (stats != null){
            stats.getWeaponStats().
        }
    }
}

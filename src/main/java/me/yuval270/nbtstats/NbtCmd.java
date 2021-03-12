package me.yuval270.nbtstats;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.RequiredArgsConstructor;
import me.yuval270.nbtstats.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class NbtCmd implements CommandExecutor { // ** THIS IS A DEBUG COMMAND**
    private final NbtStats main;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // nbt setkey
        // n bt showkeys
        Player player = (Player) sender;

        if (args.length == 2 && args[0].equalsIgnoreCase("haskey")) {
            NBTItem nbti = new NBTItem(player.getInventory().getItemInMainHand());
            if (nbti.hasKey(args[1]))
                player.sendMessage(args[1] + "has " + nbti.getDouble(args[1]) + "points");

            else
                player.sendMessage("cant find key " + args[1]);
            return false;
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("setkey")) {
            NBTItem nbti = new NBTItem(player.getInventory().getItemInMainHand());
            nbti.setDouble(args[1], Double.parseDouble(args[2]));
            player.getInventory().setItemInMainHand(nbti.getItem());
            player.sendMessage("set " + args[1] + " at level " + args[2]);
            return false;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("health")) {
            PlayerStats stats = main.getStatsManager().getPlayer(player.getUniqueId());
            System.out.println(stats == null);
            player.sendMessage(String.valueOf(stats.getMaxHealth()));
            player.sendMessage(String.valueOf(stats.getHealth()));

        }
        player.sendMessage(ChatColor.YELLOW + "nbt setkey [key] [level]");
        player.sendMessage(ChatColor.YELLOW + "nbt haskey [key]");
        return false;
    }

}

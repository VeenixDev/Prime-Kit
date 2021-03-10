package de.veenix.kit.commands;

import de.veenix.kit.inventories.SelectorInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        ((Player) sender).openInventory(SelectorInventory.getInventory());

        return true;
    }
}

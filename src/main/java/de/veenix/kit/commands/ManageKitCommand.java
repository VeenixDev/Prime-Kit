package de.veenix.kit.commands;

import de.veenix.kit.Core;
import de.veenix.kit.inventories.SelectorInventory;
import de.veenix.kit.utils.InventoryUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManageKitCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        if (args.length == 2) {
            Player player = (Player) sender;
            final String kitName = args[1];
            switch (args[0]) {
                // Create a new Kit
                case "create":
                    player.sendMessage(Core.getPrefix() + Core.getInstance().getConfig().getString((InventoryUtils.saveKitToFile(kitName.replace("&", "§"), player) ? "message" : "error") + ".kitCreate")
                            .replace("&", "§")
                            .replace("{{Kit}}", kitName));
                    SelectorInventory.init();
                    break;

                // Load a existing kit
                case "load":
                    player.sendMessage(Core.getPrefix() + Core.getInstance().getConfig().getString((InventoryUtils.loadKitFromFile(kitName, player) ? "message" : "error") + ".kitLoad")
                            .replace("&", "§")
                            .replace("{{Kit}}", kitName));
                    break;

                // Remove a existing kit
                case "delete":
                    player.sendMessage(Core.getPrefix() + Core.getInstance().getConfig().getString((InventoryUtils.removeKitFromFile(kitName.replace("&", "§")) ? "message" : "error") + ".kitDelete")
                            .replace("&", "§")
                            .replace("{{Kit}}", kitName));
                    SelectorInventory.init();
                    break;
            }
        } else return false;

        return true;
    }
}

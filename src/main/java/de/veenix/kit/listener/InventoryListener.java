package de.veenix.kit.listener;

import de.veenix.kit.Core;
import de.veenix.kit.config.SelectedKitConfig;
import de.veenix.kit.inventories.DefaultInventory;
import de.veenix.kit.inventories.SelectorInventory;
import de.veenix.kit.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        // Do in Player inventory
        if (item.equals(DefaultInventory.kitSelector)) {
            if(player.hasPermission(Core.getInstance().getCommand("kit").getPermission())) {
                Bukkit.getScheduler().runTask(Core.getInstance(), () -> player.openInventory(SelectorInventory.getInventory()));
                event.setCancelled(true);
                return;
            } else {
                player.sendMessage(Core.getInstance().getCommand("kit").getPermissionMessage());
            }
        }

        if(event.getInventory().equals(SelectorInventory.getInventory())) {
            if(item.getType().equals(Material.AIR) || item.equals(SelectorInventory.placeholder)) {
                event.setCancelled(true);
                return;
            }

            String kitName = item.getItemMeta().getDisplayName();

            SelectedKitConfig.instance.getConfig().set(player.getUniqueId().toString(), kitName);
            SelectedKitConfig.instance.save();
            InventoryUtils.loadKitFromFile(kitName, player);
            Bukkit.getScheduler().runTask(Core.getInstance(), player::closeInventory);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getItem() == null || event.getItem().getType().equals(Material.AIR)) return;

        Player player = event.getPlayer();
        if (event.getItem().equals(DefaultInventory.kitSelector)) {
            if(player.hasPermission(Core.getInstance().getCommand("kit").getPermission())) {
                Bukkit.getScheduler().runTask(Core.getInstance(), () -> player.openInventory(SelectorInventory.getInventory()));
                event.setCancelled(true);
            } else {
                player.sendMessage(Core.getInstance().getCommand("kit").getPermissionMessage());
            }
        }
    }
}

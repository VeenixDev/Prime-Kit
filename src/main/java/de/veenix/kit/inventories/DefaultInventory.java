package de.veenix.kit.inventories;

import de.veenix.kit.Core;
import de.veenix.kit.config.SelectedKitConfig;
import de.veenix.kit.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DefaultInventory {

    private static final Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER);

    public static final ItemStack kitSelector = new ItemStack(Material.ENDER_CHEST, 1);

    private DefaultInventory() {}

    public static void load(Player player) {
        if(SelectedKitConfig.instance.getConfig().getString(player.getUniqueId().toString() + ".kit") == null) {
            player.getInventory().setContents(inventory.getContents());

            // Needed to clear Armor
            player.getInventory().setArmorContents(new ItemStack[]{new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)});
            return;
        }
        String kitName = SelectedKitConfig.instance.getConfig().getString(player.getUniqueId().toString() + ".kit");

        InventoryUtils.loadKitFromFile(kitName, player);
        player.sendMessage(Core.getInstance().getConfig().getString("message.kitSelect")
                .replace("&", "§")
                .replace("{{Kit}}", kitName));
    }

    public static void init() {
        // Kit Selector Item
        ItemMeta selectorMeta = kitSelector.getItemMeta();
        List<String> selectorLore = new ArrayList<>();
        selectorLore.add(Core.getInstance().getConfig().getString("lore.kitSelector").replace("&", "§"));
        selectorMeta.setDisplayName("§5§lKit Selector");
        selectorMeta.setLore(selectorLore);
        kitSelector.setItemMeta(selectorMeta);

        inventory.setItem(4, kitSelector);
    }

    public static Inventory getInventory() {
        return inventory;
    }
}

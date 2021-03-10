package de.veenix.kit.inventories;

import de.veenix.kit.Core;
import de.veenix.kit.config.KitConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SelectorInventory {

    private static final Inventory inventory = Bukkit.createInventory(null, 9 * 4, Core.getInstance().getConfig().getString("selector.title").replace("&", "§"));

    public static final ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);

    private SelectorInventory() {}

    public static void init() {
        inventory.clear();

        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName("§9 ");
        placeholder.setItemMeta(placeholderMeta);

        for(int i = 0; i < 36; i++) {
            if(i == 9) i = 27;

            inventory.setItem(i, placeholder);
        }

        ConfigurationSection configSection = KitConfig.instance.getConfig().getConfigurationSection("kits");

        for (String kits : configSection.getKeys(false)) {
            ConfigurationSection kitSection = configSection.getConfigurationSection(kits);

            ItemStack kit = new ItemStack(kitSection.getItemStack("0"));
            ItemMeta originalMeta = kit.getItemMeta();
            ItemMeta meta = kit.getItemMeta();
            meta.setDisplayName(kits.replace("&", "§"));
            kit.setItemMeta(meta);

            inventory.addItem(kit);
            kit.setItemMeta(originalMeta);
        }
    }

    public static Inventory getInventory() {
        return inventory;
    }
}

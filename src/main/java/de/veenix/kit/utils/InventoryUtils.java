package de.veenix.kit.utils;

import de.veenix.kit.config.KitConfig;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryUtils {

    /**
     * Deletes a existing Kit from the  config-file
     *
     * @param name The name of the Kit
     * @return If the Kit was deleted
     */
    public static boolean removeKitFromFile(String name) {
        YamlConfiguration config = KitConfig.instance.getConfig();

        ConfigurationSection kitsSection = config.getConfigurationSection("kits");

        if(!kitsSection.contains(name)) return false;

        kitsSection.set(name, null);
        KitConfig.instance.save();

        return true;
    }

    /**
     * Saves a Inventory from a Player into a config-file.
     * The Items will be saved with the correct slot id.
     * If a slot is empty the slot wont be saved in the config
     *
     * @param name The name of the Kit
     * @param player The Player who contains the Items inside his Inventory
     * @return If the Kit was saved
     */
    public static boolean saveKitToFile(String name, Player player) {
        try {
            YamlConfiguration config = KitConfig.instance.getConfig();

            ConfigurationSection kitSection = config.getConfigurationSection("kits").createSection(name);

            PlayerInventory inventory = player.getInventory();

            //Save Items in Inventory if existing
            for (int i = 0; i < 36; i++) {
                if (inventory.getItem(i) == null || inventory.getItem(i).getType().equals(Material.AIR)) continue;

                kitSection.set(String.valueOf(i), inventory.getItem(i));
            }

            // Save Armor if existing
            if (inventory.getHelmet() != null && !inventory.getHelmet().getType().equals(Material.AIR))
                kitSection.set("helmet", inventory.getHelmet());

            if (inventory.getChestplate() != null && !inventory.getChestplate().getType().equals(Material.AIR))
                kitSection.set("chestplate", inventory.getChestplate());

            if (inventory.getLeggings() != null && !inventory.getLeggings().getType().equals(Material.AIR))
                kitSection.set("leggings", inventory.getLeggings());

            if (inventory.getBoots() != null && !inventory.getBoots().getType().equals(Material.AIR))
                kitSection.set("boots", inventory.getBoots());

            KitConfig.instance.save();

        } catch (NullPointerException exception) {
            return false;
        }

        return true;
    }

    /**
     * Loads a existing kit from the config-file.
     *
     * @param name The name of the kits
     * @param player The Player in whos inventory get loaded
     * @return If the kit was loaded
     */
    public static boolean loadKitFromFile(String name, Player player) {
        try {
            YamlConfiguration config = KitConfig.instance.getConfig();

            ConfigurationSection kitSection = config.getConfigurationSection("kits").getConfigurationSection(name);

            PlayerInventory inventory = player.getInventory();

            if(kitSection == null) return false;

            // Clears the Player Inventory
            inventory.clear();
            // Needed to clear Armor
            inventory.setArmorContents(new ItemStack[]{new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)});

            for (String path : kitSection.getKeys(false)) {
                int slotId;

                try {
                    slotId = Integer.parseInt(path);
                }catch (NumberFormatException exception) {
                    break;
                }
                inventory.setItem(slotId, kitSection.getItemStack(path));
            }

            if(kitSection.contains("helmet"))
                inventory.setHelmet(kitSection.getItemStack("helmet"));

            if(kitSection.contains("chestplate"))
                inventory.setChestplate(kitSection.getItemStack("chestplate"));

            if(kitSection.contains("leggings"))
                inventory.setLeggings(kitSection.getItemStack("leggings"));

            if(kitSection.contains("boots"))
                inventory.setBoots(kitSection.getItemStack("boots"));

        } catch (NullPointerException exception) {
            return false;
        }

        return true;
    }

}

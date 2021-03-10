package de.veenix.kit;

import de.veenix.kit.commands.ManageKitCommand;
import de.veenix.kit.commands.KitCommand;
import de.veenix.kit.config.KitConfig;
import de.veenix.kit.config.SelectedKitConfig;
import de.veenix.kit.inventories.DefaultInventory;
import de.veenix.kit.inventories.SelectorInventory;
import de.veenix.kit.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private static String prefix;

    @Override
    public void onLoad() {
        KitConfig.instance.init();
        SelectedKitConfig.instance.init();

        loadConfig();

        // Load Inventories
        DefaultInventory.init();
        SelectorInventory.init();
    }

    @Override
    public void onEnable() {

        // Register Commands
        getCommand("mkit").setExecutor(new ManageKitCommand());
        getCommand("kit").setExecutor(new KitCommand());

        // Register Event-Listeners
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new FoodLevelListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new MoveListener(), this);

        Bukkit.getConsoleSender().sendMessage("§aKit plugin fully loaded!");
    }

    private void loadConfig() {
        // The prefix which is used before every message send to a player
        getConfig().addDefault("chatPrefix", "&7[&bKit&7]: &r");
        // Replaces {{Kit}} with the kit-name
        getConfig().addDefault("message.kitSelect", "&a{{Kit}} is now selected");
        // Replaces {{Enemy}} with the enemy the player died against
        getConfig().addDefault("message.death", "&cYou were killed by &6{{Enemy}}");
        // Replaces {{Victim}} with the player who he/she killed
        getConfig().addDefault("message.kill", "&aYou killed &6{{Victim}}");
        // Replaces {{Command}} with the usage of the /kit command
        getConfig().addDefault("message.useKit", "&7Use \"{{Command}}\" to select a other kit");

        // Replaces {{Kit}} with the name of the created kit
        getConfig().addDefault("message.kitCreate", "&a{{Kit}} was successfully created");
        getConfig().addDefault("error.kitCreate", "&c{{Kit}} couldn't be saved");
        // Replaces {{Kit}} with the name of the loaded kit
        getConfig().addDefault("message.kitLoad", "&a{{Kit}} was successfully loaded");
        getConfig().addDefault("error.kitLoad", "&c{{Kit}} couldn't be loaded");
        // Replaces {{Kit}} with the name of the deleted kit
        getConfig().addDefault("message.kitDelete", "&a{{Kit}} was successfully deleted");
        getConfig().addDefault("error.kitDelete", "&c{{Kit}} couldn't be deleted");

        // LORE
        getConfig().addDefault("lore.kitSelector", "&8Choose which kit you want to play");

        // Inventory
        getConfig().addDefault("selector.title", "&7Kit Selector");

        // Settings
        getConfig().addDefault("settings.deathBelow", 0d);

        getConfig().options().copyDefaults(true);
        saveConfig();

        prefix = getConfig().getString("chatPrefix").replace("&", "§");
    }

    public static String getPrefix() {
        return prefix;
    }

    public static Core getInstance() {
        return JavaPlugin.getPlugin(Core.class);
    }
}

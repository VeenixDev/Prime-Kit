package de.veenix.kit.config;

import de.veenix.kit.Core;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class KitConfig {

    private final File configFile = new File(Core.getInstance().getDataFolder(), "data/kits.conf");
    private YamlConfiguration configuration;

    public static final KitConfig instance = new KitConfig();

    private KitConfig() {}

    public void init() {
        // Create config file if it doesn't exist
        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            }

            // Creates default Kits section
            configuration = YamlConfiguration.loadConfiguration(configFile);

            if(!configuration.contains("kits"))
                configuration.createSection("kits");

            configuration.save(configFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void save() {
        try {
            configuration.save(configFile);
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public YamlConfiguration getConfig() {
        return configuration;
    }
}

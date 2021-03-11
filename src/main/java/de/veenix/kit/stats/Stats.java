package de.veenix.kit.stats;

import de.veenix.kit.Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Stats {

    public static Stats instance = new Stats();

    private Connection connection;

    private Stats() {}

    public void init() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"
                            + Core.getInstance().getConfig().getString("database.host") + ":" + Core.getInstance().getConfig().getInt("database.port") + "/" + Core.getInstance().getConfig().getString("database.database"),
                    Core.getInstance().getConfig().getString("database.username"), Core.getInstance().getConfig().getString("database.password"));

            PreparedStatement statsTableCreate = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + Core.getInstance().getConfig().getString("database.tables.stats") + " (id VARCHAR(32) not null, kills INT, deaths INT)");
            statsTableCreate.execute();
        } catch (SQLException ignored) {
            // Unsuccessful db connection is handled in onEnable of the plugin
        }
    }

    public Connection getConnection() {
        return connection;
    }

}

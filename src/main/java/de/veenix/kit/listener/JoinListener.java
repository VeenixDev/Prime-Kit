package de.veenix.kit.listener;

import de.veenix.kit.inventories.DefaultInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        DefaultInventory.load(event.getPlayer());
    }
}

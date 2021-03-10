package de.veenix.kit.listener;

import de.veenix.kit.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    private final double deathBelow = Core.getInstance().getConfig().getDouble("settings.deathBelow");

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        if(player.isDead()) return;

        if(event.getPlayer().getLocation().getY() <= deathBelow) {
            player.setHealth(0f);
        }
    }

}

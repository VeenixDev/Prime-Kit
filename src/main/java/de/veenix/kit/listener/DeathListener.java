package de.veenix.kit.listener;

import de.veenix.kit.Core;
import de.veenix.kit.config.SelectedKitConfig;
import de.veenix.kit.inventories.DefaultInventory;
import de.veenix.kit.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.util.Vector;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage("");
        Player player = Bukkit.getPlayer(event.getEntity().getUniqueId());
        Player killer = player.getKiller();

        if(player.getLastDamageCause() == null) return;

        if (player.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) && killer != null) {
            player.sendMessage(Core.getPrefix() + Core.getInstance().getConfig().getString("message.death")
                    .replace("&", "§")
                    .replace("{{Enemy}}", killer.getDisplayName()));

            killer.sendMessage(Core.getPrefix() + Core.getInstance().getConfig().getString("message.kill")
                    .replace("&", "§")
                    .replace("{{Victim}}", player.getDisplayName()));
        }

        event.getDrops().clear();
        event.setKeepInventory(true);

        final String kitName = SelectedKitConfig.instance.getConfig().getString(player.getUniqueId().toString());

        if (kitName != null && InventoryUtils.loadKitFromFile(kitName, player)) {
            player.sendMessage(Core.getPrefix() + Core.getInstance().getConfig().getString("message.useKit")
                    .replace("&", "§")
                    .replace("{{Command}}", Core.getInstance().getCommand("kit").getUsage()));
        } else {
            DefaultInventory.load(player);
        }

        player.setVelocity(new Vector(0, 0, 0).normalize());
        Bukkit.getScheduler().runTaskLater(Core.getInstance(), () -> player.spigot().respawn(), 5L);
    }
}

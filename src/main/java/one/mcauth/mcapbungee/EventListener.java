package one.mcauth.mcapbungee;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Arrays;
import java.util.UUID;

public class EventListener implements Listener {
    Main plugin;

    public EventListener(Main main) {
        plugin = main;
    }

    @EventHandler
    public void onPlayerKicked(ServerKickEvent event) {
        if (event.getState() == ServerKickEvent.State.CONNECTING && Arrays.stream(event.getKickReasonComponent()).findFirst().isPresent()) {
            if (Arrays.stream(event.getKickReasonComponent()).findFirst().get().toString().toLowerCase().contains("whitelist")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(UUID.randomUUID(), new TextComponent("You must be whitelisted to join this server!"));
            }
        }
    }
}

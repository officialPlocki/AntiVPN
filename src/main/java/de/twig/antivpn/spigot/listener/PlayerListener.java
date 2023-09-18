package de.twig.antivpn.spigot.listener;

import de.twig.antivpn.util.VPNUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.io.IOException;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {
        new Thread(() -> {
            try {
                if(new VPNUtil().checkConnection(event.getAddress().getHostName())) {
                    event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "§cBitte deaktiviere deine VPN.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}

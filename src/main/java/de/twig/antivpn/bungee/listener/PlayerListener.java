package de.twig.antivpn.bungee.listener;

import de.twig.antivpn.util.VPNUtil;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(LoginEvent event) {
        new Thread(() -> {
            try {
                if(new VPNUtil().checkConnection(event.getConnection().getVirtualHost().getHostName())) {
                    event.setCancelReason(ComponentSerializer.parse("§cBitte deaktiviere deine VPN."));
                    event.setCancelled(true);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}

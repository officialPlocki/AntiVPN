package de.twig.antivpn.bungee;

import de.twig.antivpn.bungee.listener.PlayerListener;
import de.twig.antivpn.util.ConfigUtil;
import net.md_5.bungee.api.plugin.Plugin;

public class AntiVPN extends Plugin {

    @Override
    public void onEnable() {
        if(!new ConfigUtil().getAPIKey().equals("WRITE-API-KEY-HERE-FROM-IPHUNTER.INFO")) {
            this.getProxy().getPluginManager().registerListener(this, new PlayerListener());
        } else {
            this.getProxy().getConsole().sendMessage("Bitte AntiVPN API Key eintragen.");
        }
    }

}

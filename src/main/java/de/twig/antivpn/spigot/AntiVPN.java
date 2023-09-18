package de.twig.antivpn.spigot;

import de.twig.antivpn.spigot.listener.PlayerListener;
import de.twig.antivpn.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiVPN extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if(new ConfigUtil().getAPIKey().equals("WRITE-API-KEY-HERE-FROM-IPHUNTER.INFO")) this.getPluginLoader().disablePlugin(this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

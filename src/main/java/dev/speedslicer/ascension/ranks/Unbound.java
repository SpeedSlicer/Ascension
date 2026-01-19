package dev.speedslicer.ascension.ranks;

import dev.speedslicer.ascension.Ascension;
import dev.speedslicer.ascension.listeners.UnboundListener;
import dev.speedslicer.ascension.util.RankBase;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import static dev.speedslicer.ascension.Ascension.LOGGER;
import static org.bukkit.Bukkit.getServer;

public class Unbound implements RankBase {
    UnboundListener listener;
    Plugin plugin;
    public Unbound(Plugin plugin, double levelRequirement) {
        listener = new UnboundListener(this, levelRequirement);
        this.plugin = plugin;
        getServer().getPluginManager().registerEvents(listener, plugin);
        LOGGER.info("[Ascension] Unbound Rank Created");
        LOGGER.info("[Ascension] Unbound Average Level Requirement: " + levelRequirement);

        this.setupGroup();
    }
    @Override
    public Plugin getPlugin() {
        return plugin;
    }
    @Override
    public String getDisplayName() {
        return "Unbound";
    }
    @Override
    public String getName() {
        return "unbound";
    }
    @Override
    public Listener getListener() {
        return listener;
    }

    @Override
    public void refreshConfigValues() {

    }


}

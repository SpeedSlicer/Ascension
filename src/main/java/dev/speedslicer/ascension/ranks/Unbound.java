package dev.speedslicer.ascension.ranks;

import dev.speedslicer.ascension.Ascension;
import dev.speedslicer.ascension.listeners.UnboundListener;
import dev.speedslicer.ascension.util.RankBase;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class Unbound implements RankBase {
    UnboundListener listener;

    public Unbound(Plugin plugin, String levelRequirement) {
        listener = new UnboundListener(this, (double) Ascension.opts.get(levelRequirement));
        getServer().getPluginManager().registerEvents(listener, plugin);
        this.setupGroup();
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

package dev.speedslicer.ascension;

import dev.speedslicer.ascension.listeners.UnboundListener;
import dev.speedslicer.ascension.ranks.Unbound;
import dev.speedslicer.ascension.util.RankBase;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class Ascension extends JavaPlugin {
    public static LuckPerms lApi;
    public List<RankBase> rankBase;
    public static HashMap<String, Object> opts;
    @Override
    public void onEnable() {
        lApi = Objects.requireNonNull(Bukkit.getServicesManager().getRegistration(LuckPerms.class)).getProvider();
        this.saveDefaultConfig();
        rankBase = new ArrayList<>();
        rankBase.add(new Unbound(this, "unbound.levelRequirement"));
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

}

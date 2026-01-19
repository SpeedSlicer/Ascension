package dev.speedslicer.ascension;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.speedslicer.ascension.commands.CommandAscensionGuide;
import dev.speedslicer.ascension.listeners.UnboundListener;
import dev.speedslicer.ascension.ranks.Unbound;
import dev.speedslicer.ascension.util.RankBase;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class Ascension extends JavaPlugin {
    public static LuckPerms lApi;
    public List<RankBase> rankBase;
    public static HashMap<String, Object> opts;
    FileConfiguration config;
    AuraSkillsApi auraSkills;
    public static final Logger LOGGER = LogManager.getLogManager().getLogger("Ascension");
    @Override
    public void onEnable() {
        LOGGER.info("[Ascension] Plugin Enabled");
        config = this.getConfig();
        config.addDefault("unbound-averageLevelRequirement", 5D);
        config.options().copyDefaults(true);
        saveConfig();
        lApi = Objects.requireNonNull(Bukkit.getServicesManager().getRegistration(LuckPerms.class)).getProvider();
        auraSkills = Objects.requireNonNull(AuraSkillsApi.get());
        rankBase = new ArrayList<>();
        rankBase.add(new Unbound(this, config.getDouble("unbound-averageLevelRequirement")));
        this.getCommand("guide").setExecutor(new CommandAscensionGuide());

    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        LOGGER.info("[Ascension] Plugin Disabled");

    }

}

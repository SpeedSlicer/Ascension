package dev.speedslicer.ascension.listeners;

import dev.aurelium.auraskills.api.event.skill.SkillLevelUpEvent;
import dev.speedslicer.ascension.ranks.Unbound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UnboundListener implements Listener {
    Unbound connected;
    double levelRequirement;
    public UnboundListener(Unbound unbound, double levelRequirement) {
        connected = unbound;
        this.levelRequirement = levelRequirement;
    }
    @EventHandler
    public void SkillLevelUpEvent(SkillLevelUpEvent event) {
        if (!connected.playerHasRank(event.getPlayer())) {
            if (event.getUser().getSkillAverage() >= levelRequirement) {

            }
        }

    }

    public void setLevelRequirement(double levelRequirement) {
        this.levelRequirement = levelRequirement;
    }
}

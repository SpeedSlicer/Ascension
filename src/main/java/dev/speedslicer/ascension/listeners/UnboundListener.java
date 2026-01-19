package dev.speedslicer.ascension.listeners;

import dev.aurelium.auraskills.api.event.skill.SkillLevelUpEvent;
import dev.speedslicer.ascension.Ascension;
import dev.speedslicer.ascension.ranks.Unbound;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class UnboundListener implements Listener {
    Unbound connected;
    double levelRequirement;
    public UnboundListener(Unbound unbound, double levelRequirement) {
        connected = unbound;
        this.levelRequirement = levelRequirement;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLevelUp(SkillLevelUpEvent event) {
        if (!connected.playerHasRank(event.getPlayer())) {
            if (event.getUser().getSkillAverage() >= levelRequirement) {
                Ascension.LOGGER.info("[Ascension] group " + event.getPlayer().getName() + " has ascended to UNBOUND rank.");

                event.getPlayer().getServer().getScheduler().runTaskLater(connected.getPlugin(), () -> {
                    event.getPlayer().sendTitle(
                            "§5§lASCENDING",
                            "§d» §fTo §6§lUNBOUND §d«",
                            20,
                            60,
                            20
                    );

                    }, 60L);
                connected.addUserRank(event.getPlayer().getUniqueId());
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) book.getItemMeta();

                if (meta == null) return;

                meta.setTitle("Unbound");
                meta.setAuthor("The One Who Watches");

                meta.addPage(
                        "§5§lASCENSION I\n\n" +
                                "§0You have transcended\n" +
                                "§0beyond the ordinary.\n\n" +
                                "§0now, you are §6§lUNBOUND§f,\n" +
                                "§6Benefits Unlocked:\n" +
                                "§c• Land Claim §7(1 Chunk), /claim to claim!\n" +
                                "§b• /ec Access\n" +
                                "§c• Combat Realms I\n\n"
                );
                book.setItemMeta(meta);
                event.getPlayer().getInventory().addItem(book);
                event.getPlayer().getServer().getScheduler().runTaskLater(connected.getPlugin(), () -> {
                    event.getPlayer().openBook(book);
                }, 160L);

            }
        }

    }

    public void setLevelRequirement(double levelRequirement) {
        this.levelRequirement = levelRequirement;
    }
}

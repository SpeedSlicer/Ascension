package dev.speedslicer.ascension.commands;

import dev.speedslicer.ascension.Ascension;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

public class CommandAscensionGuide implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("ascension.command.guide")) {
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) book.getItemMeta();

                if (meta == null) return false;

                meta.setTitle("Ascension Guide");
                meta.setAuthor("The One Who Watches");
                meta.addPage(
                        "§0§lASCENSION I\n" +
                                "§8━━━━━━━━━━━━━━\n\n" +

                                "§9§lUnbound\n\n" +

                                "§8§lRequirements\n" +
                                "§7• Skill Average §a5 §8(§755 levels§8)\n\n" +

                                "§8§lRewards\n" +
                                "§7• §bCombat Realms I\n" +
                                "§7• §d/ec §8- Ender Chest\n" +
                                "§7• §a1 Chunk Claim\n"
                );
                book.setItemMeta(meta);
                player.getInventory().addItem(book);
                player.getServer().getScheduler().runTaskLater(Ascension.getPlugin(Ascension.class), () -> {
                    player.openBook(book);
                }, 10L);

            }
        }
        return true;

    }
}
package dev.speedslicer.ascension.util;

import dev.speedslicer.ascension.Ascension;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.node.Node;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;


public interface RankBase {
    default String getName() { return null;}
    default String getDisplayName() { return null;}
    default Listener getListener() {return null;}
    default void refreshConfigValues() { }
    public default boolean playerHasRank(Player player) {
        return player.hasPermission("group." + getName());
    }
    public default void addUserRank(UUID userUuid) {
        Group group = Ascension.lApi.getGroupManager().getGroup(getName());
        if (group == null) {
            System.out.println("Group " + getName() + " does not exist.");
            return;
        }

        Ascension.lApi.getUserManager().modifyUser(userUuid, user -> {
            Node groupNode = Node.builder("group." + getName()).build();
            user.data().add(groupNode);
            System.out.println("Added user " + userUuid + " to group " + getName());
        });

    }
    public default void setupGroup() {
        Group group = Ascension.lApi.getGroupManager().getGroup(getName());
        if (group == null) {
            Ascension.lApi.getGroupManager().createAndLoadGroup(getName()).thenAccept(group1 -> {
                System.out.println("Group '" + group1.getName() + "' created successfully.");
                Ascension.lApi.getGroupManager().saveGroup(group1);
            });
        }
        else {
            System.out.println("Group '" + group.getName() + "' already setup");
        }
    }
}

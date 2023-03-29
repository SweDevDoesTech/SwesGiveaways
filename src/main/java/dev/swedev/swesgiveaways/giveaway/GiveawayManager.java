package dev.swedev.swesgiveaways.giveaway;

import dev.swedev.swesgiveaways.Plugin;
import dev.swedev.swesgiveaways.misc.Utils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GiveawayManager {

    private final Plugin plugin;

    @Getter
    private int maxGiveaways;

    @Getter
    private List<Giveaway> activeGiveaways;

    public GiveawayManager(Plugin plugin) {
        this.plugin = plugin;
        this.maxGiveaways = plugin.getConfig().getInt("giveaways.max-giveaways");
        this.activeGiveaways = new ArrayList<>();
    }

    public void startGiveaway(ItemStack item, long expirationTime, Player player) {
        Giveaway giveaway = new Giveaway(plugin, item, expirationTime, player);
        giveaway.start();
        activeGiveaways.add(giveaway);

        for (Player loopPlayer : plugin.getServer().getOnlinePlayers()) {
            //if (loopPlayer.equals(player)) return;

            Utils.sendCommandMessage(loopPlayer, "&6&lNew giveaway started (Click to enter)", "giveaway enter");
        }
    }

    public void enterGiveaway(Player player, int giveawayIndex) {
        activeGiveaways.get(giveawayIndex).enter(player);
    }

    public void cancelGiveaway(int giveawayIndex) {
        activeGiveaways.get(giveawayIndex).cancel();
        activeGiveaways.remove(giveawayIndex);
    }

    public List<Giveaway> getActivateGiveaways(Player player) {
        /*
        for (Giveaway giveaway : activeGiveaways) {
            if (giveaway.getPlayerWhoStarted() == player) {
                return g;
            }
        }
        */

        return activeGiveaways.stream().filter(giveaway -> giveaway.getPlayerWhoStarted() == player).toList();
    }

    public void assignId(Player player) {
        int index = 0;
        for (Giveaway giveaway : getActivateGiveaways(player)) {
            giveaway.setLocalId(index);
            index++;
        }
    }
}

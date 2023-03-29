package dev.swedev.swesgiveaways.giveaway;

import dev.swedev.swesgiveaways.Plugin;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Giveaway {

    private final Plugin plugin;

    @Getter
    private final ItemStack item;

    @Getter
    private long expirationTime;

    @Getter
    private final OfflinePlayer playerWhoStarted;

    @Getter
    private final List<Player> enteredPlayers;

    @Getter
    @Setter
    private int localId;

    private int taskId;

    public Giveaway(Plugin plugin, ItemStack item, long expirationTime, Player playerWhoStarted) {
        this.plugin = plugin;
        this.item = item;
        this.expirationTime = expirationTime;
        this.playerWhoStarted = playerWhoStarted;

        this.enteredPlayers = new ArrayList<>();
    }

    public Giveaway(Plugin plugin, ItemStack item, long expirationTime, OfflinePlayer playerWhoStarted) {
        this.plugin = plugin;
        this.item = item;
        this.expirationTime = expirationTime;
        this.playerWhoStarted = playerWhoStarted;

        this.enteredPlayers = new ArrayList<>();
    }

    public void start() {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            //plugin.getServer().broadcastMessage(String.valueOf(expirationTime));

            if (expirationTime > 0)
                expirationTime--;

            if (expirationTime <= 0) {
                if (enteredPlayers.size() > 0) {
                    Random random = new Random();
                    int number = random.nextInt(enteredPlayers.size());
                    enteredPlayers.get(number).sendMessage("You won the giveaway!");
                }
                plugin.getDataStore().remove(plugin.getGiveawayManager().getActiveGiveaways().indexOf(this));
                plugin.getGiveawayManager().getActiveGiveaways().remove(this);

                Bukkit.getScheduler().cancelTask(taskId);
            }
        }, 0, 1);


    }

    public void enter(Player player) {
        enteredPlayers.add(player);
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(taskId);
        for (Player player : enteredPlayers) {
            enteredPlayers.remove(player);
        }
    }
}

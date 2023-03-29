package dev.swedev.swesgiveaways.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import dev.swedev.swesgiveaways.Plugin;
import dev.swedev.swesgiveaways.inventory.GiveawayListInventory;
import org.bukkit.entity.Player;

@CommandAlias("giveaways|giveaway")
public class MainCommand extends BaseCommand {

    private final Plugin plugin;

    public MainCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Default
    public void onCommand(Player player) {
        new GiveawayListInventory(plugin).open(player);
    }
}

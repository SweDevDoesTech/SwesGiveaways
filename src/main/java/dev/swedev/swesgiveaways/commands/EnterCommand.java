package dev.swedev.swesgiveaways.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import dev.swedev.swesgiveaways.Plugin;
import org.bukkit.entity.Player;

@CommandAlias("giveaways|giveaway")
public class EnterCommand extends BaseCommand {

    private final Plugin plugin;

    public EnterCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Subcommand("enter")
    public void onCommand(Player player) {
        plugin.getGiveawayManager().enterGiveaway(player, 0);
    }
}

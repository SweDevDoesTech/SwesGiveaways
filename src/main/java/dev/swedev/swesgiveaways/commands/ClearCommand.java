package dev.swedev.swesgiveaways.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.swedev.swesgiveaways.Plugin;
import dev.swedev.swesgiveaways.giveaway.Giveaway;
import org.bukkit.command.CommandSender;

@CommandAlias("giveaways|giveaway")
public class ClearCommand extends BaseCommand {

    private final Plugin plugin;

    public ClearCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Subcommand("clear")
    @CommandPermission("giveaways.command.admin.clear")
    public void onCommand(CommandSender sender) {
        plugin.getGiveawayManager().cancelGiveaway(0);
    }
}

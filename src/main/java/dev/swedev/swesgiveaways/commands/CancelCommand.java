package dev.swedev.swesgiveaways.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Subcommand;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import dev.swedev.swesgiveaways.Plugin;
import dev.swedev.swesgiveaways.giveaway.Giveaway;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

@CommandAlias("giveaways|giveaway")
public class CancelCommand extends BaseCommand {

    private final Plugin plugin;

    public CancelCommand(Plugin plugin) {
        this.plugin = plugin;
        plugin.getCommandManager().getCommandCompletions().registerCompletion("giveaways", context -> {
            if (context.getSender() instanceof Player player) {
                return plugin.getGiveawayManager().getActivateGiveaways(player).stream().map(giveaway -> String.valueOf(giveaway.getLocalId())).toList();
            }
            return null;
        });
    }

    @Subcommand("cancel")
    @CommandCompletion("@giveaways")
    public void onCommand(Player player, String[] args) {
        if (args.length < 1)
            return;

        plugin.getGiveawayManager().cancelGiveaway(Integer.parseInt(args[0]));
    }
}

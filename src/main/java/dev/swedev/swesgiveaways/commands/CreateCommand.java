package dev.swedev.swesgiveaways.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import dev.swedev.swesgiveaways.Messages;
import dev.swedev.swesgiveaways.Plugin;
import dev.swedev.swesgiveaways.misc.Utils;
import org.bukkit.entity.Player;

@CommandAlias("giveaways|giveaway")
public class CreateCommand extends BaseCommand {

    private final Plugin plugin;

    public CreateCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Subcommand("create")
    public void onCommand(Player player, String[] args) {
        if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
            player.sendMessage(Messages.NO_ITEM.get());
            return;
        }

        if (args.length == 1) {
            String time = Utils.parseTimeChar(args[0]);
            if (time == null) {
                player.sendMessage("You must specify a time unit.");
                return;
            }

            plugin.getGiveawayManager().startGiveaway(player.getInventory().getItemInMainHand(), Long.parseLong(time), player);
        }
    }
}

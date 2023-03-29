package dev.swedev.swesgiveaways.inventory;

import dev.swedev.swesgiveaways.Plugin;
import dev.swedev.swesgiveaways.giveaway.Giveaway;
import dev.swedev.swesgiveaways.misc.Utils;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GiveawayListInventory {

    private int taskId;

    @Getter
    private final PaginatedGui gui = Gui.paginated()
            .title(Component.text(Utils.colorize("&6&lGiveaways")))
            .rows(4)
            .pageSize(27)
            .create();

    public GiveawayListInventory(Plugin plugin) {
        gui.setDefaultClickAction(event -> event.setCancelled(true));

        gui.setItem(4, 3, ItemBuilder.from(Material.PAPER).setName(Utils.colorize("&b&lPrevious Page")).asGuiItem(event -> gui.previous()));
        gui.setItem(4, 7, ItemBuilder.from(Material.PAPER).setName(Utils.colorize("&b&lNext Page")).asGuiItem(event -> gui.next()));

        List<GuiItem> giveawayItems = new ArrayList<>();

        int index = 0;
        for (Giveaway giveaway : plugin.getGiveawayManager().getActiveGiveaways()) {
            List<String> giveawayLore = new ArrayList<>();
            giveawayLore.add("");
            if (giveaway.getExpirationTime() < 1200) {
                giveawayLore.add(Utils.colorize("&7Expires in: &b" + giveaway.getExpirationTime() / 20 + " seconds"));
            } else if (giveaway.getExpirationTime() >= 1200) {
                giveawayLore.add(Utils.colorize("&7Expires in: &b" + giveaway.getExpirationTime() / 60 / 20 + " minutes"));
            }
            giveawayLore.add("");
            giveawayLore.add(Utils.colorize("&7&oClick to enter."));

            giveawayItems.add(ItemBuilder.from(giveaway.getItem()).asGuiItem(event -> {

            }));

            ItemMeta itemMeta = giveawayItems.get(index).getItemStack().getItemMeta();
            itemMeta.setLore(giveawayLore);
            itemMeta.setDisplayName(Utils.colorize("&6Giveaway of x" + giveaway.getItem().getAmount() + " " + giveaway.getItem().getType().name()));
            giveawayItems.get(index).getItemStack().setItemMeta(itemMeta);

            gui.addItem(giveawayItems.get(index));

            index++;
        }
    }

    public void open(Player player) {
        gui.open(player);
    }
}

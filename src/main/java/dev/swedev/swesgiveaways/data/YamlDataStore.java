package dev.swedev.swesgiveaways.data;

import dev.swedev.swesgiveaways.Plugin;
import dev.swedev.swesgiveaways.giveaway.Giveaway;
import dev.swedev.swesgiveaways.giveaway.GiveawayManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class YamlDataStore extends DataStore {

    private final Plugin plugin;

    public YamlDataStore(Plugin plugin) {
        super("yaml");

        this.plugin = plugin;
    }

    @Override
    public void load() {
        if (plugin.getData().get().getConfigurationSection("") == null) {
            plugin.getLogger().info("Configuration section is null.");
            return;
        }

        for (String index : plugin.getData().get().getConfigurationSection("").getKeys(false)) {
            ItemStack item = plugin.getData().get().getItemStack(index + ".item");
            long expiration = plugin.getData().get().getLong(index + ".expiration");
            OfflinePlayer player = plugin.getData().get().getOfflinePlayer(index + "player");

            Giveaway giveaway = new Giveaway(plugin, item, expiration, player);
            giveaway.start();
            plugin.getGiveawayManager().getActiveGiveaways().add(giveaway);
            plugin.getLogger().info("Loaded giveaway: " + index);
        }
        /*
        for (int index : plugin.getData().get().getConfigurationSection("").getKeys(false).size()) {

        }
        */
    }

    @Override
    public void save() {
        /*
        for (int i = 0; i < plugin.getGiveawayManager().getActiveGiveaways().size(); i++) {
            ItemStack item = plugin.getData().get().getItemStack(i + 1 + ".item");
            long expirationTime = plugin.getData().get().getLong(i + 1 + ".expiration");
            OfflinePlayer player = (Player) plugin.getData().get().getOfflinePlayer(i + 1 + "player");
        }
        */

        // ConfigurationSection is null for some reason right now, fix that...
        ConfigurationSection section = plugin.getData().get().getConfigurationSection("");
        int index = (section == null) ? 0 : section.getKeys(false).size();
        GiveawayManager manager = plugin.getGiveawayManager();

        for (int i = 0; i < manager.getActiveGiveaways().size(); i++) {
            /*
            if (section.get(String.valueOf(i)) != null) {
                ItemStack item = manager.getActiveGiveaways().get(i).getItem();
                long expiration = manager.getActiveGiveaways().get(i).getExpirationTime();
                OfflinePlayer player = manager.getActiveGiveaways().get(i).getPlayerWhoStarted();

                plugin.getData().get().set(i + ".item", item);
                plugin.getData().get().set(i + ".expiration", expiration);
                plugin.getData().get().set(i + ".player", player);

                break;
            }
            */

            ItemStack item = manager.getActiveGiveaways().get(i).getItem();
            long expiration = manager.getActiveGiveaways().get(i).getExpirationTime();
            OfflinePlayer player = manager.getActiveGiveaways().get(i).getPlayerWhoStarted();

            plugin.getData().get().set(i + ".item", item);
            plugin.getData().get().set(i + ".expiration", expiration);
            plugin.getData().get().set(i + ".player", player);
        }

        plugin.getData().save();
    }

    @Override
    public void remove(int index) {
        plugin.getData().get().set(String.valueOf(index), null);
        plugin.getData().save();
    }
}

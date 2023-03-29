package dev.swedev.swesgiveaways;

import co.aikar.commands.BukkitCommandManager;
import dev.swedev.swesgiveaways.commands.*;
import dev.swedev.swesgiveaways.config.Config;
import dev.swedev.swesgiveaways.data.DataStore;
import dev.swedev.swesgiveaways.data.YamlDataStore;
import dev.swedev.swesgiveaways.giveaway.GiveawayManager;
import dev.swedev.swesgiveaways.misc.Utils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    @Getter
    private static Plugin instance;

    @Getter
    private DataStore dataStore;

    @Getter
    private BukkitCommandManager commandManager;

    @Getter
    private GiveawayManager giveawayManager;

    @Getter
    Config data;

    @Override
    public void onEnable() {
        instance = this;

        new Config(this, "config.yml");
        data = new Config(this, "data.yml");

        giveawayManager = new GiveawayManager(this);

        initDataStore("yaml", true);

        commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new MainCommand(this));
        commandManager.registerCommand(new CreateCommand(this));
        commandManager.registerCommand(new EnterCommand(this));
        commandManager.registerCommand(new ClearCommand(this));
        commandManager.registerCommand(new CancelCommand(this));
    }

    @Override
    public void onDisable() {
        getDataStore().save();
    }

    public void initDataStore(String method, boolean load) {
        DataStore.getMethods().add(new YamlDataStore(this));

        if (method != null)
            dataStore = DataStore.getMethod(method);
        else {
            getLogger().info(Utils.colorize("&cNo valid storage method provided."));
            getLogger().info(Utils.colorize("&cCheck the config and try again."));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        try {
            getLogger().info(Utils.colorize("Initializing datastore \"" + getDataStore().getName() + "\"..."));

            if (load) {
                getLogger().info(Utils.colorize("Loading giveaways..."));
                getDataStore().load();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

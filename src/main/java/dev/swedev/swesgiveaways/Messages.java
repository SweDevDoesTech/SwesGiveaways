package dev.swedev.swesgiveaways;

import org.bukkit.ChatColor;

public enum Messages {
    PREFIX("&6&lGiveaways &7&l»"),
    NO_ITEM(PREFIX + " &cYou must hold an item in your hand.");

    private final String message;

    Messages(String message) {
        this.message = ChatColor.translateAlternateColorCodes('&', message);
    }

    public String get() {
        return message;
    }
}

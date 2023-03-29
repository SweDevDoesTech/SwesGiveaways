package dev.swedev.swesgiveaways.misc;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class Utils {

    public static String parseTimeChar(String text) {
        //Plugin.getInstance().getConfig().get("giveaways.default-expiration-time");
        char lastChar = text.charAt(text.length() - 1);
        long inputTime = Long.parseLong(text.replaceAll("[^0-9]", ""));

        switch (lastChar) {
            case 'm' :
                return String.valueOf(inputTime * 60 * 20);

            case 'h' :
                return String.valueOf(inputTime * 60 * 60 * 20);

            default:
                return null;
        }
    }

    public static void sendCommandMessage(Player player, String message, String command) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));
        player.spigot().sendMessage(component);
    }

    public static String colorize(String text) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', text);
    }
}

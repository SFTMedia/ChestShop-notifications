package com.acrobot.chestshop.notifications;

import com.Acrobot.ChestShop.Events.ShopCreatedEvent;
import com.Acrobot.ChestShop.Signs.ChestShopSign;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static com.acrobot.chestshop.notifications.properties.Messages.SHOP_CREATION_FIRST_LINE;
import static com.acrobot.chestshop.notifications.properties.Messages.SHOP_CREATION_SECOND_LINE;

/**
 * @author Acrobot
 */
public class ShopCreationListener implements Listener {
    private Notifications notifications;

    @EventHandler(priority = EventPriority.MONITOR)
    public void onShopCreation(ShopCreatedEvent event) {
        Location location = event.getSign().getLocation();

        String firstLine = SHOP_CREATION_FIRST_LINE
                .replace("%player", event.getPlayer().getName())
                .replace("%item", event.getSignLine(ChestShopSign.ITEM_LINE));

        String secondLine = SHOP_CREATION_SECOND_LINE
                .replace("%location", "[" + location.getWorld().getName() + "] " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());
        Notifications.sendNotification(event.getPlayer(),firstLine,secondLine);
    }
}

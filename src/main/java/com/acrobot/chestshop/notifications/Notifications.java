package com.acrobot.chestshop.notifications;

import com.Acrobot.Breeze.Configuration.Configuration;
import com.acrobot.chestshop.notifications.properties.Messages;
import com.acrobot.chestshop.notifications.properties.Properties;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * @author Acrobot
 */
public class Notifications extends JavaPlugin {
    private static File dataFolder;
    private static HashMap<OfflinePlayer,ArrayList<String[]>> offlineNotifications;

    public void onEnable() {
        dataFolder = getDataFolder();

        Configuration.pairFileAndClass(loadFile("config.yml"), Properties.class);
        Configuration.pairFileAndClass(loadFile("local.yml"), Messages.class);

        if (Properties.SHOW_NOTIFICATION_ON_TRANSACTION) {
            getServer().getPluginManager().registerEvents(new TransactionListener(), this);
        }

        if (Properties.SHOW_NOTIFICATION_ON_SHOP_CREATION) {
            getServer().getPluginManager().registerEvents(new ShopCreationListener(), this);
        }
    }

    public static File loadFile(String string) {
        File file = new File(dataFolder, string);

        return loadFile(file);
    }

    private static File loadFile(File file) {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }
    public static void sendNotification(OfflinePlayer player,String firstLine,String secondLine) {
        if(player.isOnline()){
            
        } else {
            if(!offlineNotifications.hasKey(player)){
                offlineNotifications.put(player,new ArrayList<String[]>());
            }
            offlineNotifications.get(player).add(new String[]{firstLine,secondLine})
        }
    }
}

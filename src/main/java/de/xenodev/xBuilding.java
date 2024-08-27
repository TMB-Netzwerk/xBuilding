package de.xenodev;

import de.xenodev.commands.*;
import de.xenodev.events.JoinEvent;
import de.xenodev.events.QuitEvent;
import de.xenodev.utils.BoardBuilder;
import de.xenodev.utils.TimeBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;

public class xBuilding extends JavaPlugin {

    private static xBuilding instance;

    @Override
    public void onEnable() {
        instance = this;

        if(!new File("plugins/" + getName(), "config.yml").exists()){
            saveDefaultConfig();
        }

        init(getServer().getPluginManager());
        BoardBuilder.updateBoard();
        updateWorldTime();
    }

    private void init(PluginManager pluginManager) {
        pluginManager.registerEvents(new JoinEvent(), this);
        pluginManager.registerEvents(new QuitEvent(), this);
        pluginManager.registerEvents(new EnderseeCommand(), this);
        pluginManager.registerEvents(new InvseeCommand(), this);

        getCommand("location").setExecutor(new LocationCommand());
        getCommand("worldtime").setExecutor(new WorldTimeCommand());
        getCommand("rename").setExecutor(new RenameCommand());
        getCommand("signate").setExecutor(new SignateCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("endersee").setExecutor(new EnderseeCommand());

        getCommand("location").setTabCompleter(new LocationCommand());
        getCommand("gamemode").setTabCompleter(new GamemodeCommand());
        getCommand("endersee").setTabCompleter(new EnderseeCommand());
    }

    public static xBuilding getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return getInstance().getConfig().getString("Settings.Chatprefix").replace("&", "§");
    }

    public static String getError() {
        return getInstance().getConfig().getString("Settings.Errorprefix").replace("&", "§");
    }

    private void updateWorldTime() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    if(world.getPlayers().size() >= 1){
                        TimeBuilder timeBuilder = new TimeBuilder(world.getName());
                        timeBuilder.addSeconds(1);
                        if (timeBuilder.getSeconds() == 60) {
                            timeBuilder.addMinutes(1);
                            timeBuilder.setSeconds(0);
                        }

                        if (timeBuilder.getMinutes() == 60) {
                            timeBuilder.addHours(1);
                            timeBuilder.setMinutes(0);
                        }

                        if (timeBuilder.getHours() == 24) {
                            timeBuilder.addDays(1);
                            timeBuilder.setHours(0);
                        }
                    }
                }
            }

        }.runTaskTimerAsynchronously(xBuilding.getInstance(), 0, 20L);
    }
}
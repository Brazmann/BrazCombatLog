package io.github.Brazmann.brazCombatLogPlugin;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class BrazCombatLogPlugin extends JavaPlugin {

    public static FileConfiguration config;


    @Override
    public void onEnable() {
        saveResource("config.yml", false);
        config = getConfig();
        getServer().getPluginManager().registerEvents(new BrazListener(), this);
        CombatManager.Initialize();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

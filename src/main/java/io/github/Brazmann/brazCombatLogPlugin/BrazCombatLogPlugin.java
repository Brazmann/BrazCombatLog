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
        /*manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register(
                    Commands.literal("city-gold")
                            .executes(ctx -> {
                                ctx.getSource().getSender().sendPlainMessage("City of Gold!");
                                Entity executor = ctx.getSource().getExecutor();
                                var bruh = SphereAround(executor.getLocation(), 40);
                                int affectedCount = 0;
                                for(Block b : bruh){
                                    if(b.getType() != Material.AIR){
                                        b.setType(Material.GOLD_BLOCK);
                                        affectedCount++;
                                    }
                                }
                                ctx.getSource().getSender().sendPlainMessage("affected " + affectedCount + " blocks!");
                                return com.mojang.brigadier.Command.SINGLE_SUCCESS;
                            })
                            .build(),
                    "some bukkit help description string",
                    List.of("an-alias")
            );
        });*/
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

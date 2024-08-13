package io.github.Brazmann.brazCombatLogPlugin;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BrazListener implements Listener {


    @EventHandler
    public void onTick(ServerTickStartEvent event){
        CombatManager.Tick(event);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        CombatInstance instance = CombatManager.GetInstanceFromPlayer(event.getPlayer());
        if(instance != null) {
            CombatManager.DeleteInstance(instance, false);
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event){
        CombatInstance instance = CombatManager.GetInstanceFromPlayer(event.getPlayer());
        if(instance != null){
            CombatManager.DeleteInstance(instance, false);
            event.getPlayer().setHealth(0);
        }
    }

    @EventHandler
    public void onPlayerAttack(PrePlayerAttackEntityEvent event){
        Entity defender = event.getAttacked();
        Player attacker = event.getPlayer();
        //This is gonna be scuffed
        if(defender instanceof Player){
            Player playerDefender = (Player)defender;
            if(CombatManager.GetInstanceFromPlayer(attacker) != null || CombatManager.GetInstanceFromPlayer(playerDefender) != null) return;
                CombatManager.CreateNewInstance(event.getPlayer(), playerDefender);
        }
    }

}


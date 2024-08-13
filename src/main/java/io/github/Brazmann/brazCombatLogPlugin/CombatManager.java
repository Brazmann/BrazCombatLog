package io.github.Brazmann.brazCombatLogPlugin;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CombatManager {

    private static List<CombatInstance> combatInstances;

    private static int combatDuration = 60;
    public static int distanceToEndCombat = 4;


    public static void Initialize(){
        combatDuration = BrazCombatLogPlugin.config.getInt("ticks-to-expire-combat");
        distanceToEndCombat = BrazCombatLogPlugin.config.getInt("distance-to-expire-combat");
        combatInstances = new ArrayList<>();
    }

    public static void CreateNewInstance(UUID attacker, UUID defender){
        CombatInstance newInstance = new CombatInstance(attacker, defender);
        newInstance.ticksUntilEnd = combatDuration;
        combatInstances.add(newInstance);
        newInstance.Announce();
    }
    public static void CreateNewInstance(Player attacker, Player defender){
        CombatInstance newInstance = new CombatInstance(attacker.getUniqueId(), defender.getUniqueId());
        newInstance.ticksUntilEnd = combatDuration;
        combatInstances.add(newInstance);
        newInstance.Announce();
    }


    public static int GetInstanceCount(){
        return combatInstances.size();
    }

    public static CombatInstance GetInstanceFromPlayer(Player player){
        for(CombatInstance i : combatInstances){
            if(i.GetAttacker() == player.getUniqueId() || i.GetDefender() == player.getUniqueId()){
                return i;
            }
        }
        return null;
    }

    public static void DeleteInstance(CombatInstance instance, boolean isGenericEnd){
        instance.AnnounceEnd(isGenericEnd);
        combatInstances.remove(instance);
    }


    public static void Tick(ServerTickStartEvent tickEvent){
        UpdateCombatInstances();
    }

    private static void UpdateCombatInstances(){
        for(CombatInstance i : combatInstances){
            if(i.ticksUntilEnd <= 0){
                DeleteInstance(i, true);
                return;
            }
            if(i.isInRange()){
                i.ticksUntilEnd = combatDuration;
            } else {
                i.ticksUntilEnd -= 1;
            }
        }
    }

}

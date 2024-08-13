package io.github.Brazmann.brazCombatLogPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CombatInstance{
    private UUID attacker;
    private UUID defender;
    public int ticksUntilEnd = 0;

    public CombatInstance(UUID attacker, UUID defender) {
        this.attacker = attacker;
        this.defender = defender;
    }


    public void Announce(){
        Player att = GetAttackerEntity();
        Player def = GetDefenderEntity();
        //NIGHTMARE NIGHTMARE NIGHTMARE NIGHTMARE
        final TextComponent textForA = Component.text("You have entered combat with ").color(NamedTextColor.DARK_RED).append(Component.text(def.getName(), NamedTextColor.RED));
        att.sendMessage(textForA);
        final TextComponent textForD = Component.text("You have entered combat with ").color(NamedTextColor.DARK_RED).append(Component.text(att.getName(), NamedTextColor.RED));
        def.sendMessage(textForD);
    }

    public void AnnounceEnd(boolean isGenericEnd){
        Player att = GetAttackerEntity();
        Player def = GetDefenderEntity();

        if(isGenericEnd){
            TextComponent genericMessage = Component.text("Combat has ended. You may log out if desired now.", NamedTextColor.GOLD);
            att.sendMessage(genericMessage);
            def.sendMessage(genericMessage);
            return;
        }

        if(!att.isDead()){ //stuff this sloppa into it's own method or something, man.
            final TextComponent textForA = Component.text("Victory! ", NamedTextColor.GREEN)
                    .append(Component.text("Your combat with ", NamedTextColor.DARK_GREEN))
                    .append(Component.text(def.getName(), NamedTextColor.RED))
                    .append(Component.text(" has ended!", NamedTextColor.DARK_GREEN));
            att.sendMessage(textForA);
            final TextComponent textForD = Component.text("Failure! ", NamedTextColor.DARK_RED)
                    .append(Component.text("Your combat with ", NamedTextColor.DARK_RED))
                    .append(Component.text(att.getName(), NamedTextColor.RED))
                    .append(Component.text(" has ended!", NamedTextColor.DARK_RED));
            def.sendMessage(textForD);
        } else {
            final TextComponent textForD = Component.text("Victory! ", NamedTextColor.GREEN)
                    .append(Component.text("Your combat with ", NamedTextColor.DARK_GREEN))
                    .append(Component.text(att.getName(), NamedTextColor.RED))
                    .append(Component.text(" has ended!", NamedTextColor.DARK_GREEN));
            def.sendMessage(textForD);
            final TextComponent textForA = Component.text("Failure! ", NamedTextColor.DARK_RED)
                    .append(Component.text("Your combat with ", NamedTextColor.DARK_RED))
                    .append(Component.text(def.getName(), NamedTextColor.RED))
                    .append(Component.text(" has ended!", NamedTextColor.DARK_RED));
            att.sendMessage(textForA);
        }
    }

    public boolean isInRange(){
        return GetAttackerEntity().getLocation().distance(GetDefenderEntity().getLocation()) < CombatManager.distanceToEndCombat;

    }

    public UUID GetAttacker(){
        return attacker;
    }

    public UUID GetDefender(){
        return defender;
    }

    public Player GetAttackerEntity(){
        return Bukkit.getPlayer(attacker);
    }

    public Player GetDefenderEntity(){
        return Bukkit.getPlayer(defender);
    }
}

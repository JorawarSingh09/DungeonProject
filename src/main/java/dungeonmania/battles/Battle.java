package dungeonmania.battles;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.Health;

public class Battle {

    List<Round> rounds = new ArrayList<>();
    Health enemy;
    Player player;
    Inventory playerInventory;

    public Battle(Health enemy, Player player, Inventory playerInventory) {
        this.enemy = enemy;
        this.player = player;
        this.playerInventory = playerInventory;
    }

    /* returns if player won */
    public boolean startBattle() {
        while (!isGameOver()) {
            startRound();
        }

        updateWeaponDurability();
        diminishPotionEffects();
        return (isPlayerAlive());
        //Update player state
    }

    public void startRound() {
        rounds.add(new Round(player.getAttack(), enemy.getAttackDamage(), 
                            playerInventory.getAttackingItems(), playerInventory.getDefendingItems(), 
                            player.getAllies()));
    }

    private boolean isGameOver() {
        return (player.getHealth() <= 0 || enemy.getHealth() <= 0);
    }

    private boolean isPlayerAlive() {
        return (player.getHealth() > 0);
    }

    private void updateWeaponDurability() {
        playerInventory.updateWeaponsDurability();
    }

    private void diminishPotionEffects() {
        ;
    }
}

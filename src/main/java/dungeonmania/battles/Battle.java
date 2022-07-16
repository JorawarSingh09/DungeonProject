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
    double initialPlayerHealth;
    double initialEnemyHealth;

    public Battle(Health enemy, Player player, Inventory playerInventory) {
        this.enemy = enemy;
        this.player = player;
        this.playerInventory = playerInventory;
        this.initialEnemyHealth = enemy.getHealth();
        this.initialPlayerHealth = player.getHealth();
    }

    /* returns if player won */
    public boolean startBattle() {
        while (!isGameOver()) {
            startRound();
        }

        updateWeaponDurability();
        return (isPlayerAlive());
        // Update player state
    }

    public void startRound() {
        Round round = new Round(player.getAttack(), enemy.getAttackDamage(),
                playerInventory.getAttackingItems(), playerInventory.getDefendingItems(),
                player.getAllies());
        rounds.add(round);
        if (player.getPlayerState() == player.getInvincState()) {
            enemy.setHealth(0);
        } else {
            enemy.loseHealth(round.enemyHealthChange());
            player.loseHealth(round.playerHealthChange());
        }
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

    public Health getEnemy() {
        return this.enemy;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public double getInitialPlayerHealth() {
        return initialPlayerHealth;
    }

    public double getInitialEnemyHealth() {
        return initialEnemyHealth;
    }

}

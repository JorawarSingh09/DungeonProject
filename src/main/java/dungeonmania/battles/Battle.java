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

    public void startBattle() {
        while (!gameOver()) {
            startRound();
        }
    }

    public void startRound() {
        rounds.add(new Round());
    }

    private boolean gameOver() {
        return (player.getHealth() <= 0 || enemy.getHealth() <= 0);
    }

    private void updateWeaponDurability() {
        ;
    }

    private void diminishPotionEffects() {
        ;
    }
}

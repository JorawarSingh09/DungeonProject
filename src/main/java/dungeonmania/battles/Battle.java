package dungeonmania.battles;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Health;

public class Battle {
    List<Round> rounds = new ArrayList<>();
    Health enemy;
    Player player;

    public Battle(Health enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
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

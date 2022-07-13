package dungeonmania.controllers;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.battles.Battle;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Health;
import dungeonmania.response.models.BattleResponse;

public class BattleController {
    List<Battle> battles = new ArrayList<>();
    private int enemiesKilled;

    

    /*To start a new battle simply call newBattle from bc with the player and battling entity*/
    public BattleController() {
        this.enemiesKilled = 0;
    }

    public void newBattle(Player player, Health enemy) {
        Battle battle = new Battle(enemy, player, player.getInventory());
        battles.add(battle);
        if (battle.startBattle()) enemiesKilled += 1;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public BattleResponse getBattleResponseObj() {
        return null;
    }
}

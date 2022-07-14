package dungeonmania.controllers;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.battles.Battle;
import dungeonmania.battles.Round;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Storeable;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.response.models.RoundResponse;

public class BattleController {
    List<Battle> battles = new ArrayList<>();
    private int enemiesKilled;
    private boolean playerDied;    

    /*To start a new battle simply call newBattle from bc with the player and battling entity*/
    public BattleController() {
        this.enemiesKilled = 0;
    }

    public boolean newBattle(Player player, Health enemy) {
        Battle battle = new Battle(enemy, player, player.getInventory());
        battles.add(battle);
        if (battle.startBattle()) {
            enemiesKilled += 1;
        } else {
            this.playerDied = true;
        }
        player.engageBattle(playerDied);
        return (!playerDied);
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public boolean isPlayerAlive() {
        return (!playerDied);
    }

    public List<BattleResponse> getBattleResponseObj(Player player) {
        List<BattleResponse> battleList = new ArrayList<>();
        for (Battle battle : battles) {
            List<RoundResponse> roundResponses = new ArrayList<>();
            for (Round round : battle.getRounds()) {
                List<ItemResponse> weapons = new ArrayList<>();
                for (Integer id : round.weaponryUsed()) {
                    Storeable item = player.getItemFromId(id);
                    weapons.add(new ItemResponse(Integer.toString(item.getItemId()), item.getType()));
                }
                roundResponses.add(new RoundResponse(round.playerHealthChange(), round.enemyHealthChange(), weapons));
            }
            battleList.add(new BattleResponse(battle.getEnemy().getType(), roundResponses, battle.getInitialPlayerHealth(), battle.getInitialEnemyHealth()));
        }
        return battleList;
    }

}

package dungeonmania.controllers;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.battles.Battle;
import dungeonmania.battles.Round;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.interfaces.Health;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.response.models.RoundResponse;

public class BattleController {
    private List<Battle> battles = new ArrayList<>();
    private int enemiesKilled;
    private boolean playerDied;

    public BattleController() {
        this.enemiesKilled = 0;
    }

    public boolean newBattle(Player player, Health enemy) {
        Battle battle = new Battle(enemy, player, player.getInventory());
        battles.add(battle);
        if (battle.startBattle()) {
            enemiesKilled++;
        } else {
            this.playerDied = true;
        }
        player.engageBattle(playerDied);
        return (!playerDied);
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void addKill() {
        enemiesKilled++;
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
                    weapons.add(new ItemResponse(Integer.toString(id), player.itemType(id)));
                }
                roundResponses.add(new RoundResponse(round.playerHealthChange(), round.enemyHealthChange(), weapons));
            }
            battleList.add(new BattleResponse(battle.getEnemy().getType(), roundResponses,
                    battle.getInitialPlayerHealth(), battle.getInitialEnemyHealth()));
        }
        return battleList;
    }

}

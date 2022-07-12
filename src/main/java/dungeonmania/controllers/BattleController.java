package dungeonmania.controllers;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.battles.Battle;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.interfaces.Health;
import dungeonmania.response.models.BattleResponse;

public class BattleController {
    List<Battle> battles = new ArrayList<>();

    /*To start a new battle simply call newBattle from bc with the player and battling entity*/
    public void newBattle(Player player, Health enemy) {
        battles.add(new Battle(enemy, player, player.getInventory()));
    }

    public BattleResponse getBattleResponseObj() {
        return null;
    }
}

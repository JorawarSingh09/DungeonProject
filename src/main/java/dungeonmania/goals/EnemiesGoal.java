package dungeonmania.goals;

import dungeonmania.dungeon.Dungeon;

import com.google.gson.JsonObject;

import dungeonmania.controllers.BattleController;
import dungeonmania.enums.GoalString;

public class EnemiesGoal implements Goal {
    private int enemies;

    public EnemiesGoal(int enemies) {
        this.enemies = enemies;
    }

    public boolean isGoalCompleted(Dungeon dungeon) {
        BattleController bc = dungeon.getBattleController();
        int enemiesKilled = bc.getEnemiesKilled();
        return (enemiesKilled >= enemies) && (dungeon.countAllZomSpawners() == 0);
    }

    @Override
    public String toString(Dungeon dungeon) {
        if (isGoalCompleted(dungeon))
            return GoalString.COMPLETED.toString();
        return GoalString.ENEMY.toString();
    }

    @Override
    public JsonObject getJson(Dungeon dungeon) {

        JsonObject goal = new JsonObject();
        goal.addProperty("goal", this.toString(dungeon));
        return goal;

    }
}

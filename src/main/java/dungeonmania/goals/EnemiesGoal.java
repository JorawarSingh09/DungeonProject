package dungeonmania.goals;

import dungeonmania.dungeon.Dungeon;

import com.google.gson.JsonObject;

import dungeonmania.controllers.BattleController;

public class EnemiesGoal implements Goal {
    
    private int enemies;

    public EnemiesGoal(int enemies) {
        this.enemies = enemies;
    }

    public boolean isGoalCompleted(Dungeon dungeon) {
        BattleController bc = dungeon.getBattleController();
        int enemiesKilled = bc.getEnemiesKilled();
        int spawnersRemain = dungeon.getEntitiesOfType("zombie_toast_spawner").size();
        return (enemiesKilled >= enemies && spawnersRemain == 0);
    }

    @Override
    public String toString(Dungeon dungeon) {
        if (isGoalCompleted(dungeon))
            return "";
        return ":enemies";
    }

    @Override
    public JsonObject getJson(Dungeon dungeon) {
        JsonObject goal = new JsonObject();
        goal.addProperty("goal", this.toString(dungeon));
        return goal;
    }

}

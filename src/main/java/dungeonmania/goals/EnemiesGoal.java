package dungeonmania.goals;

import dungeonmania.Dungeon;
import dungeonmania.controllers.BattleController;

public class EnemiesGoal implements Goal {
    public int enemies;

    public EnemiesGoal(int enemies) {
        this.enemies = enemies;
    }

    public boolean isGoalCompleted(Dungeon dungeon) {
         BattleController bc = dungeon.getBattleController();
         int enemiesKilled = bc.getEnemiesKilled();

        return(enemiesKilled >= enemies);
    }

    public String toString(Dungeon dungeon){
        if (isGoalCompleted(dungeon))
            return "";
        return ":enemies";
    }

}

package dungeonmania.goals;

import dungeonmania.Dungeon;

public class EnemiesGoal implements Goal {
    public int enemies;

    public EnemiesGoal(int enemies) {
        this.enemies = enemies;
    }

    public boolean isGoalCompleted(Dungeon dungeon) {
        return false;
    }

}

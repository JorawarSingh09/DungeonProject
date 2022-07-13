package dungeonmania.goals;

import dungeonmania.Dungeon;

public interface Goal {
    public boolean isGoalCompleted(Dungeon dungeon);

    public String toString(Dungeon dungeon);
}

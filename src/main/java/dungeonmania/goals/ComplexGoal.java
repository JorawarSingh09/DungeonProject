package dungeonmania.goals;

import dungeonmania.Dungeon;

public class ComplexGoal implements Goal {
    Goal goal1 = null;
    Goal goal2 = null;
    public GoalCondition condition;

    public ComplexGoal(GoalCondition condition) {
        this.condition = condition;
    }

    public boolean isGoalCompleted(Dungeon dungeon) {
        if (condition == GoalCondition.AND) {
            return goal1.isGoalCompleted(dungeon) && goal2.isGoalCompleted(dungeon);
        } else {
            return goal1.isGoalCompleted(dungeon) || goal2.isGoalCompleted(dungeon);
        }
    }

    public void addSubgoal(Goal goal) {
        if (goal1 == null) {
            goal1 = goal;
        } else {
            goal2 = goal;
        }
    }

}

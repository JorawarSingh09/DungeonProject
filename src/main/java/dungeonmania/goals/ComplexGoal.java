package dungeonmania.goals;

import java.util.ArrayList;
import java.util.List;

public class ComplexGoal implements Goal {
    List<Goal> subgoals = new ArrayList<>();
    public GoalCondition condition;

    public ComplexGoal(GoalCondition condition) {
        this.condition = condition;
    }

    public List<Goal> getSubgoals() {
        return subgoals;
    }

    public void addSubgoal(Goal goal) {
        this.subgoals.add(goal);
    }
    
}

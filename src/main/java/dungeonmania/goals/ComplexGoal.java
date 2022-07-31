package dungeonmania.goals;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.enums.GoalString;

public class ComplexGoal implements Goal {
    
    private Goal goal1 = null;
    private Goal goal2 = null;
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

    @Override
    public String toString(Dungeon dungeon) {
        if (!goal1.isGoalCompleted(dungeon) && !goal2.isGoalCompleted(dungeon)) {
            return "(" + goal1.toString(dungeon) + " " + condition.name() + 
            " " + goal2.toString(dungeon) + ")";
        }else if (!goal1.isGoalCompleted(dungeon)) {;
            return goal1.toString(dungeon);
        } else if (!goal2.isGoalCompleted(dungeon)) {
            return goal2.toString(dungeon);
        }
        return GoalString.COMPLETED.toString();

    }

    @Override
    public JsonObject getJson(Dungeon dungeon) {
        JsonObject goal = new JsonObject();
        if (condition.name().equals(GoalCondition.OR.toString())) {
            goal.addProperty("goal", "OR");
        } else if (condition.name().equals(GoalCondition.AND.toString())) {
            goal.addProperty("goal", "AND");
        } else {
            goal.addProperty("goal", goal1.toString(dungeon));
            return goal;
        }
        JsonArray subgoals = new JsonArray();
        subgoals.add(goal1.getJson(dungeon));
        subgoals.add(goal2.getJson(dungeon));
        goal.add("subgoals", subgoals);
        return goal;
    }

}

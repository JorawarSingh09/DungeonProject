package dungeonmania.goals;

public enum GoalCondition {
    AND {
        @Override
        public String toString() {
            return "AND";
        }
    },
    OR {
        @Override
        public String toString() {
            return "OR";
        }
    }
}

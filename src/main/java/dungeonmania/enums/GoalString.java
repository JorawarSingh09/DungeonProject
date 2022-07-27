package dungeonmania.enums;

public enum GoalString {
    TREASURE {
        @Override
        public String toString() {
            return ":treasure";
        }
    },

    ENEMY {
        @Override
        public String toString() {
            return ":enemies";
        }
    },

    BOULDERGOAL {
        @Override
        public String toString() {
            return ":boulders";
        }
    },

    EXITGOAL {
        @Override
        public String toString() {
            return ":exit";
        }
    },

    COMPLETED {
        @Override
        public String toString() {
            return "";
        }
    }
}

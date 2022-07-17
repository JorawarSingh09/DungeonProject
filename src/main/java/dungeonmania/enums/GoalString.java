package dungeonmania.enums;

public enum GoalString {
    TREASURE {
        public String toString() {
            return ":treasure";
        }
    },

    ENEMY {
        public String toString() {
            return ":enemies";
        }
    },

    BOULDERGOAL {
        public String toString() {
            return ":boulders";
        }
    },

    EXITGOAL {
        public String toString() {
            return ":exit";
        }
    },

    COMPLETED {
        public String toString() {
            return "";
        }
    }
}

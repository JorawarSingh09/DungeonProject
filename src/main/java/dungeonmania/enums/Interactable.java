package dungeonmania.enums;

public enum Interactable {
    MERC {
        public String toString() {
            return "mercenary";
        }
    },

    ZOMTOASTSPAWN {
        public String toString() {
            return "zombie_toast_spawner";
        }
    },

    ASS {
        public String toString() {
            return "assassin";
        }
    }
}

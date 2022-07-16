package dungeonmania.enums;

public enum ErrorString {
    BRIBERAD {
        public String toString() {
            return "Mercenary is not within the player's bribe radius";
        }
    },
  
    BRIBETREAS {
        public String toString() {
            return "Player does not have enough treasure to bribe this mercenary";
        }
    },

    ZOMRAD {
        public String toString() {
            return "Player is not cardinally adjacent to the Zombie Toast Spawner.";
        }
    },
  
    NOWEAP {
        public String toString() {
            return "Player does not have a weapon so it cannot destroy the Zombie Toast Spawner.";
        }
    },

    NOTINTERACT {
        public String toString() {
            return "Entity is not interactable";
        }
    },

    SUCCESS {
        public String toString() {
            return "SUCCESS!";
        }
    }
}

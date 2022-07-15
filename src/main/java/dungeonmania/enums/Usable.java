package dungeonmania.enums;

public enum Usable {
    INVIS {
        public String toString() {
            return "invisibility_potion";
        }
    },
  
    INVINCE {
        public String toString() {
            return "invincibility_potion";
        }
    }, 

    BOMB {
        public String toString() {
            return "bomb";
        }
    }
}

package dungeonmania.entities.collectableentities;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Sword extends Entity implements Storeable, Attacking, Durability, Collectable {

    private int attack;
    private int durability;

    public Sword(int id, Position position, boolean interactable, boolean collidable, int attack, int durability) {
        super(id, position, interactable, collidable);
        this.attack = attack;
        this.durability = durability;
    }

    public void reduceDurability() {
        this.durability--;

    }

    public int getDurability() {
        return durability;
    }

    public boolean isAdditive() {
        return true;
    }

    public void pickup(Player player, Dungeon dungeon) {
        player.addItem(this);
        dungeon.removeEntity(this);
    }

    public int battleBonus() {
        return attack;
    }

    public int getItemId() {
        return getEntityId();
    }

    public String getType() {
        return EntityString.SWORD.toString();
    }

}

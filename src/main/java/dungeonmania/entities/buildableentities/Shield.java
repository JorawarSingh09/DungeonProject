package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Defending;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Shield extends Entity implements Buildable, Storeable, Defending, Durability {

    private int durability;
    private int defence;

    public Shield(int id, boolean interactable, boolean collidable, int durability, int defence) {
        super(id, new Position(0, 0), interactable, collidable);
        this.durability = durability;
        this.defence = defence;
    }

    public void reduceDurability() {
        this.durability -= 1;
    }

    public int getDurability() {
        return durability;
    }

    public int battleBonus() {
        return defence;
    }

    public int getItemId() {
        return getEntityId();
    }

    public String getType() {
        return EntityString.SHIELD.toString();
    }

    @Override
    public void build(Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void consumeItems(Player player) {
        // TODO Auto-generated method stub

    }

}

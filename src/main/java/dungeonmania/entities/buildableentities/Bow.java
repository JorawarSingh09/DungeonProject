package dungeonmania.entities.buildableentities;

import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.enums.EntityString;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Bow extends Entity implements Buildable, Storeable, Durability, Attacking {

    private int battleBonus = 2;
    private int durability;

    public Bow(int id, int bow_durability) {
        super(id, new Position(0, 0), false, false);
        this.durability = bow_durability;
    }

    public void reduceDurability() {
        this.durability -= 1;

    }

    public int getDurability() {
        return durability;
    }

    public boolean isAdditive() {
        return false;
    }

    public int getItemId() {
        return getEntityId();
    }

    public int attackBonus() {
        return battleBonus;
    }

    public String getType() {
        return EntityString.BOW.toString();
    }

    @Override
    public void build(Player player) {

    }

    @Override
    public void consumeItems(Player player) {

    }
}

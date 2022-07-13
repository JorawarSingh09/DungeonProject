package dungeonmania.entities.collectableentities;

import dungeonmania.entities.Entity;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Key extends Entity implements Storeable, Collectable{
    
    public Key(int id, Position position, boolean interactable, boolean collidable) {
        super(id, position, interactable, collidable);
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pickup() {
        // TODO Auto-generated method stub
        
    }
    
    public int getItemId() {
        return getEntityId();
    }

    public String getType() {
        return "key";
    }

}

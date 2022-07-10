package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Entity;

public class Dungeon {
    List<Entity> entities = new ArrayList<>();
    int currMaxId;

    public Dungeon() {
        currMaxId = 0;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    
    public void addEntity(Entity entity) {
        this.entities.add(entity);
        currMaxId += 1;
    }

    public int getCurrMaxId() {
        return currMaxId;
    }

    public void print() {
        System.out.println(entities);
    }

}

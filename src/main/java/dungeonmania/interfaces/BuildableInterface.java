package dungeonmania.interfaces;

import dungeonmania.entities.Entity;

public interface BuildableInterface {
    public boolean checkMaterials();
    public Entity build();
}

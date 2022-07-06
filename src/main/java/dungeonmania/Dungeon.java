package dungeonmania;

import java.util.List;

import dungeonmania.controllers.BattleController;
import dungeonmania.controllers.ComplexGoal;
import dungeonmania.controllers.MovementController;
import dungeonmania.entities.Entity;
import dungeonmania.spawners.SpiderSpawn;
import dungeonmania.util.Position;
import java.util.ArrayList;

public class Dungeon {
    List<Entity> entities;
    BattleController bc;
    MovementController mc;
    Player player;
    ComplexGoal goal;

    int dungeonId;
    String dungeonName;
    int currMaxEntity;

    /**
     * 
     * @param dungeonId
     * @param dungeonName
     * @param currMaxEntity
     * @param entities
     * @param bc
     * @param mc
     * @param player
     * @param goal
     */
    public Dungeon(int dungeonId, String dungeonName, int currMaxEntity, List<Entity> entities, 
                    BattleController bc, MovementController mc, Player player, ComplexGoal goal){
        
        this.dungeonId = dungeonId;
        this.dungeonName = dungeonName;
        this.currMaxEntity = currMaxEntity;
        this.entities = entities;
        this.bc = bc;
        this.mc = mc;
        this.player = player;
        this.goal = goal;
    }

    public int getDungeonId() {
        return this.dungeonId;
    }

    public void setDungeonId(int dungeonId) {
        this.dungeonId = dungeonId;
    }

    public String getDungeonName() {
        return this.dungeonName;
    }

    public void setDungeonName(String dungeonName) {
        this.dungeonName = dungeonName;
    }

    public int getCurrMaxEntity() {
        return this.currMaxEntity;
    }

    public void setCurrMaxEntity(int currMaxEntity) {
        this.currMaxEntity = currMaxEntity;
    }

    public void removeEntity(Entity entity){
        entities.remove(entity);
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public List<Entity> getEntities(){
        return entities;
    }
}

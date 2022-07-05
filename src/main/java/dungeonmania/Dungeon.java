package dungeonmania;

import java.util.List;

import dungeonmania.controllers.BattleController;
import dungeonmania.controllers.ComplexGoal;
import dungeonmania.controllers.MovementController;
import dungeonmania.util.Position;

import java.util.ArrayList;

public class Dungeon {
    List<Entity> entities  = new ArrayList<>();
    BattleController bc = new BattleController();
    MovementController mc = new MovementController();
    Player player = new Player();
    ComplexGoal goal = new ComplexGoal();

    int dungeonId;
    String dungeonName;
    int zombieSpawnRate;
    SpiderSpawn spiderSpawn;
    int currMaxEntity;

    

    /**
     * 
     * @param dungeonId
     * @param dungeonName
     * @param zombieSpawnRate
     * @param spiderSpawnRate
     * @param spiderSpawnLocation
     * @param currMaxEntity
     * @param entities
     * @param bc
     * @param mc
     * @param player
     * @param goal
     */
    public Dungeon(int dungeonId, String dungeonName, int zombieSpawnRate, int spiderSpawnRate, 
                    Position spiderSpawnLocation, int currMaxEntity, List<Entity> entities, 
                    BattleController bc, MovementController mc, Player player, ComplexGoal goal){
        
        this.dungeonId = dungeonId;
        this.dungeonName = dungeonName;
        this.zombieSpawnRate = zombieSpawnRate;
        this.spiderSpawn = new SpiderSpawn(spiderSpawnLocation, spiderSpawnRate);
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

    public int getZombieSpawnRate() {
        return this.zombieSpawnRate;
    }

    public void setZombieSpawnRate(int zombieSpawnRate) {
        this.zombieSpawnRate = zombieSpawnRate;
    }

    public SpiderSpawn getSpiderSpawn() {
        return this.spiderSpawn;
    }

    public int getCurrMaxEntity() {
        return this.currMaxEntity;
    }

    public void setCurrMaxEntity(int currMaxEntity) {
        this.currMaxEntity = currMaxEntity;
    }
}

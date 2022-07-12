package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.controllers.BattleController;
import dungeonmania.controllers.MovementController;
import dungeonmania.entities.Entity;
import dungeonmania.goals.Goal;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.Spider;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.spawners.SpiderSpawn;
import dungeonmania.util.Position;

public class Dungeon {

    int dungeonId;
    String dungeonString;
    int tickCount;
    List<Entity> entities = new ArrayList<>();
    Goal goal;
    BattleController bc;
    MovementController mc;
    Player player;
    int currMaxEntityId;
    SpiderSpawn spiderSpawner;

    public Dungeon(String dungeonName, int dungeonId) {
        this.dungeonString = dungeonName;
        currMaxEntityId = 0;
        tickCount = 0;
    }

    //Dungeon Respose
    public DungeonResponse createDungeonResponse(){
        return null;
    }

    public List<EntityResponse> createEntityResponse(){
        return null;
    }

    public List<ItemResponse> createItemResponse(){
        return null;
    }

    public List<Entity> getBuildable(){
        return null;
    }

    public int getDungeonId() {
        return this.dungeonId;
    }

    public void setDungeonId(int dungeonId) {
        this.dungeonId = dungeonId;
    }

    public String getDungeonString() {
        return this.dungeonString;
    }

    public void setDungeonString(String dungeonString) {
        this.dungeonString = dungeonString;
    }

    public int getTickCount() {
        return this.tickCount;
    }

    public void setTickCount(int tickCount) {
        this.tickCount = tickCount;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getEntitiesOfType(String type){
        return new ArrayList<>();
    }

    public List<Entity> getEntitiesOnBlock(Position pos){
        return new ArrayList<>();
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    
    public void addEntity(Entity entity) {
        this.entities.add(entity);
        currMaxEntityId += 1;
    }

    // TODO: check defined behaviour for item/entity removal (always unique?)
    public void removeEntity(Entity removing) { 
        for (Entity entity : entities){
            if (entity.equals(removing)){
                entities.remove(removing);
                currMaxEntityId -= 1;
            }
        }
    }

    public int getCurrMaxEntityId() {
        return currMaxEntityId;
    }

    public void print() {
        System.out.println(entities);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoals(Goal goal) {
        this.goal = goal;
    }

    public void setSpiderSpawner(SpiderSpawn spawner) {
        this.spiderSpawner = spawner;
    }

}

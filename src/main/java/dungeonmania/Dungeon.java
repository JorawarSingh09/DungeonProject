package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.controllers.BattleController;
import dungeonmania.controllers.MovementController;
import dungeonmania.entities.Entity;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class Dungeon {

    int dungeonId;
    String dungeonString;
    int tickCount;
    List<Entity> entities = new ArrayList<>();
    BattleController bc;
    MovementController mc;
    Player player;
    String goals;
    int currMaxEntityId;

    public Dungeon() {
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

    public void removeEntity(Entity removing){
        for(Entity entity : entities){
            if(entity.equals(removing)){
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

    public String getGoals() {
        return this.goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }



}

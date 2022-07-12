package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.controllers.BattleController;
import dungeonmania.controllers.MovementController;
import dungeonmania.entities.Entity;
import dungeonmania.goals.Goal;
import dungeonmania.interfaces.Storeable;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class Dungeon {

    String dungeonId;
    String dungeonName;
    int tickCount;
    List<Entity> entities = new ArrayList<>();
    Goal goal;
    BattleController bc;
    MovementController mc;
    Player player;
    int currMaxEntityId;

    public Dungeon() {
        currMaxEntityId = 0;
        tickCount = 0;
    }

    //Dungeon Respose
    public DungeonResponse createDungeonResponse(){
        return new DungeonResponse(dungeonId, dungeonName, createEntityResponse(),
            createItemResponse(), createBattleResponse(), getBuildable(), goal.toString());
    }

    public List<EntityResponse> createEntityResponse(){
        List<EntityResponse> entityResponses = new ArrayList<>();
        for(Entity entity : entities){
            entityResponses.add(new EntityResponse(entity.getEntityId(), entity.getType(),
                                 entity.getPosition(), entity.isInteractable()));
        }
        return entityResponses;
    }

    public List<ItemResponse> createItemResponse(){
        List<ItemResponse> inventory = new ArrayList<>();
        for(Storeable item : player.getInventoryItems()){
            Entity bob = (Entity) item; //this isnt gonna work
            inventory.add(new ItemResponse(bob.getEntityId(), bob.getType()));
        }
        return inventory;
    }

    public List<BattleResponse> createBattleResponse(){
        // no clue what to do here
        return new ArrayList<>();
    }
    public List<String> getBuildable(){
        List<String> buildable = new ArrayList<>();
        for(Storeable item : player.getBuildableItems()){
            buildable.add(item.toString());
        }
        return buildable;
    }

    public String getDungeonId() {
        return this.dungeonId;
    }

    public void setDungeonId(String dungeonId) {
        this.dungeonId = dungeonId;
    }

    public String getDungeonString() {
        return this.dungeonName;
    }

    public void setDungeonString(String dungeonString) {
        this.dungeonName = dungeonString;
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
        List<Entity> foundMatches = new ArrayList<>();
        for(Entity entity : entities){
            if(entity.getType().equals(type)){
                foundMatches.add(entity);
            }
        }
        return foundMatches;
    }

    public List<Entity> getEntitiesOnBlock(Position pos){
        List<Entity> foundMatches = new ArrayList<>();
        for(Entity entity : entities){
            if(entity.getPosition().equals(pos)){
                foundMatches.add(entity);
            }
        }
        return foundMatches;
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

    public Goal getGoal() {
        return goal;
    }

    public void setGoals(Goal goal) {
        this.goal = goal;
    }



}

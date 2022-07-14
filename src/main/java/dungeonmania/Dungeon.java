package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.controllers.BattleController;
import dungeonmania.controllers.MovementController;
import dungeonmania.entities.Entity;
import dungeonmania.goals.Goal;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.interfaces.Static;
import dungeonmania.interfaces.Storeable;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.spawners.SpiderSpawn;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Dungeon {

    int dungeonId;
    String dungeonName;
    int tickCount;
    List<Entity> entities = new ArrayList<>();
    BattleController bc = new BattleController();
    MovementController mc;
    Goal goal;
    Player player;
    int currMaxEntityId;
    SpiderSpawn spiderSpawner;
    List<String> usables = List.of("invincibility_potion", "invisibility_potion", "bomb");

    public Dungeon(String dungeonName, int dungeonId) {
        this.dungeonId = dungeonId;
        this.dungeonName = dungeonName;
        currMaxEntityId = 0;
        tickCount = 0;
    }

    public boolean isGoalCompleted() {
        return goal.isGoalCompleted(this);
    }

    // Dungeon Respose
    public DungeonResponse createDungeonResponse() {
        return new DungeonResponse(Integer.toString(dungeonId), dungeonName, createEntityResponse(),
                createItemResponse(), createBattleResponse(), getBuildable(), goal.toString(this));
    }

    public List<EntityResponse> createEntityResponse() {
        List<EntityResponse> entityResponses = new ArrayList<>();
        for (Entity entity : entities) {
            entityResponses.add(new EntityResponse(Integer.toString(entity.getEntityId()), entity.getType(),
                    entity.getPosition(), entity.isInteractable()));
        }
        return entityResponses;
    }

    public List<ItemResponse> createItemResponse() {
        List<ItemResponse> inventory = new ArrayList<>();
        for (Storeable item : player.getInventoryItems()) {
            inventory.add(new ItemResponse(Integer.toString(item.getItemId()), item.getType()));
        }
        return inventory;
    }

    public List<BattleResponse> createBattleResponse() {
        return bc.getBattleResponseObj(player);
    }

    public BattleController getBattleController() {
        return this.bc;
    }

    public List<String> getBuildable() {
        List<String> buildable = new ArrayList<>();
        for (String item : player.getBuildableItems()) {
            buildable.add(item);
        }
        return buildable;
    }

    public String getDungeonId() {
        return Integer.toString(this.dungeonId);
    }

    public void setDungeonId(int dungeonId) {
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

    public void tick() {
        if (spiderSpawner.getSpawnRate() != 0 && tickCount % spiderSpawner.getSpawnRate() == 0) {
            addEntity(spiderSpawner.spawnSpider(getCurrMaxEntityId()));
        }
        this.tickCount++;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getEntitiesOfType(String type) {
        List<Entity> foundMatches = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getType().equals(type)) {
                foundMatches.add(entity);
            }
        }
        return foundMatches;
    }

    public List<Static> getStaticsOnBlock(Position pos) {
        List<Static> foundMatches = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getPosition().equals(pos) && entity instanceof Static) {
                foundMatches.add((Static) entity);
            }
        }
        return foundMatches;
    }

    public List<Collectable> getCollectablesOnBlock(Position pos) {
        List<Collectable> foundMatches = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getPosition().equals(pos) && entity instanceof Collectable) {
                foundMatches.add((Collectable) entity);
            }
        }
        return foundMatches;
    }

    public List<Moveable> getEnemies() {
        List<Moveable> foundMatches = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Moveable && !(entity instanceof Player)) {
                foundMatches.add((Moveable) entity);
            }
        }
        return foundMatches;
    }

    public List<Health> getEnemiesOnBlock(Position pos) {
        List<Health> foundMatches = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getPosition().equals(pos) && entity instanceof Health) {
                foundMatches.add((Health) entity);
            }
        }
        return foundMatches;
    }

    public boolean areAllSwitchesTriggered() {
        return mc.allSwitchestriggered();
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
        currMaxEntityId += 1;
    }

    // TODO: check defined behaviour for item/entity removal in terms of ID (always
    // unique?)
    public void removeEntity(Entity removing) {
        entities.remove(removing);
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
        this.mc = new MovementController(player, this);
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

    public Entity getEntityById(int id) {
        for (Entity entity : entities) {
            if (entity.getEntityId() == id) {
                return entity;
            }
        }
        return null;
    }

    public boolean itemInPlayerInventory(int id) {
        return player.hasItem(id);
    }

    public boolean itemIsUsable(int id) {
        return usables.contains(player.itemType(id));
    }

    public boolean canBuild(String itemString) {
        return player.getBuildableItems().contains(itemString);
    }

    public void useItem(int id) {
        if (player.itemType(id).equals(usables.get(0))) {
            player.drinkInvinc(id);
        } else if (player.itemType(id).equals(usables.get(1))) {
            player.drinkInvis(id);
        } else {
            player.putDownBomb(id);
        }
    }

    public void build(String item) {
        player.build(item, currMaxEntityId);
        currMaxEntityId += 1;
    }

    public void updateMovement(Direction playerMovement) {
        mc.movePlayer(playerMovement);
        // mc.updateEntityPositions();
    }

    public void startBattle(Health enemy) {
        if (bc.newBattle(player, enemy)) {
            removeEntity(getEntityById(enemy.getEntityId()));
        } else {
            removeEntity(player);
        }
    }

}

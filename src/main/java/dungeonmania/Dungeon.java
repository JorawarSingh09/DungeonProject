package dungeonmania;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.controllers.BattleController;
import dungeonmania.controllers.MovementController;
import dungeonmania.entities.Entity;
import dungeonmania.goals.Goal;
import dungeonmania.interfaces.Collectable;
import dungeonmania.interfaces.Health;
import dungeonmania.interfaces.Moveable;
import dungeonmania.interfaces.Static;
import dungeonmania.interfaces.Storeable;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.staticentities.SwampTile;
import dungeonmania.enums.Buildable;
import dungeonmania.enums.ErrorString;
import dungeonmania.enums.GoalString;
import dungeonmania.enums.Interactable;
import dungeonmania.enums.Usable;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.spawners.SpiderSpawn;
import dungeonmania.spawners.ZombieToastSpawner;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Dungeon {

    private int dungeonId;
    private String dungeonName;
    private int tickCount;
    private List<Entity> entities = new ArrayList<>();
    private BattleController bc = new BattleController();
    private MovementController mc;
    private Goal goal;
    private Player player;
    private int currMaxEntityId;
    private SpiderSpawn spiderSpawner;
    private List<SwampTile> swampTiles = new ArrayList<>();

    public Dungeon(String dungeonName, int dungeonId) {
        this.dungeonId = dungeonId;
        this.dungeonName = dungeonName;
        currMaxEntityId = 0;
        tickCount = 1;
    }

    public boolean isGoalCompleted() {
        return goal.isGoalCompleted(this);
    }

    // Dungeon Respose
    public DungeonResponse createDungeonResponse() {
        String goalComplete = goal.toString(this);
        if (goal.isGoalCompleted(this)) {
            goalComplete = GoalString.COMPLETED.toString();
        }
        return new DungeonResponse(Integer.toString(dungeonId), dungeonName, createEntityResponse(),
                createItemResponse(), createBattleResponse(), getBuildable(), goalComplete);
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
        for (String item : player.getBuildableItems(getEntitiesOfType("zombie_toast").size() > 0)) {
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

    public void tick(boolean hasMoved) {
        if (!hasMoved) {
            player.setPreviousPosition(player.getPosition());
        }
        player.tickPotion();
        player.tickMindControl();
        mc.updateEntityPositions();
        if (spiderSpawner.getSpawnRate() != 0 && tickCount % spiderSpawner.getSpawnRate() == 0) {
            addEntity(spiderSpawner.spawnEntity(getCurrMaxEntityId(), this));
        }
        for (Entity zombieToastSpawner : getEntitiesOfType("zombie_toast_spawner")) {
            if (((ZombieToastSpawner) zombieToastSpawner).getSpawnRate() != 0 &&
                    tickCount % ((ZombieToastSpawner) zombieToastSpawner).getSpawnRate() == 0) {
                addEntity(((ZombieToastSpawner) zombieToastSpawner).spawnEntity(getCurrMaxEntityId(), this));
            }
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
            if (entity instanceof Moveable) {
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
        if (entity != null) {
            this.entities.add(entity);
            currMaxEntityId += 1;
        }
    }

    public void removeEntity(Entity removing) {
        entities.remove(removing);
    }

    public void addKillCount() {
        bc.addKill();
    }

    public int getCurrMaxEntityId() {
        return currMaxEntityId;
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

    public String getEntityType(int id) {
        if (getEntityById(id) != null) {
            return getEntityById(id).getType();
        }
        return "";
    }

    public boolean itemInPlayerInventory(int id) {
        return player.hasItem(id);
    }

    public boolean itemIsUsable(int id) {
        return Arrays.stream(Usable.values()).anyMatch((t) -> t.toString().equals(player.itemType(id)));
    }

    public boolean itemIsInteractable(int id) {
        return Arrays.stream(Interactable.values()).anyMatch((t) -> t.toString().equals(getEntityType(id)));
    }

    public static boolean itemIsBuildable(String item) {
        return Arrays.stream(Buildable.values()).anyMatch((t) -> t.toString().equals(item));
    }

    public boolean canBuild(String itemString) {
        return player.getBuildableItems(getEntitiesOfType("zombie_toast").size() > 0).contains(itemString);
    }

    public void useItem(int id) {
        ;
        if (player.itemType(id).equals(Usable.BOMB.toString())) {
            player.putDownBomb(this, id);
        } else {
            player.drinkPotion(id);
        }
    }

    public String interact(int id) {
        if (getEntityType(id).equals(Interactable.MERC.toString())) {
            Mercenary mercenary = (Mercenary) getEntityById(id);
            return player.attemptBribe(mercenary);
        } else {
            ZombieToastSpawner zomSpawn = (ZombieToastSpawner) getEntityById(id);
            return tryBreakZomSpawn(zomSpawn);
        }
    }

    public void build(String item) {
        player.build(item, currMaxEntityId);
        currMaxEntityId += 1;
    }

    public void updateMovement(Direction playerMovement) {
        mc.movePlayer(playerMovement);
    }

    public List<Integer> getEntityIds() {
        return entities.stream().map(Entity::getEntityId).collect(Collectors.toList());
    }

    public void removeEntityById(int id) {
        removeEntity(getEntityById(id));
    }

    public void startBattle(Health enemy) {
        if (player.getPlayerState().equals(player.getInvisState())) {
            return;
        }
        if (bc.newBattle(player, enemy)) {
            removeEntity(getEntityById(enemy.getEntityId()));
        } else {
            removeEntity(player);
        }
    }

    public String tryBreakZomSpawn(ZombieToastSpawner zomSpawn) {
        if (!(player.getPosition().getCardinallyAdjacentPositions().contains(zomSpawn.getPosition()))) {
            return ErrorString.ZOMRAD.toString();
        } else if (!player.hasWeapon()) {
            return ErrorString.NOWEAP.toString();
        }
        removeEntity(zomSpawn);
        bc.addKill();
        return ErrorString.SUCCESS.toString();
    }

    public void addSwampTile(SwampTile swampTile) {
        swampTiles.add(swampTile);
    }

    public List<SwampTile> getSwampTiles() {
        return swampTiles;
    }
}

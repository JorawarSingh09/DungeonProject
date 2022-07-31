package dungeonmania.entities.movingentities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.buildableentities.Sceptre;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.collectableentities.interfaces.Regenerative;
import dungeonmania.entities.collectableentities.interfaces.Storeable;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.entities.movingentities.playerstates.InvincibleState;
import dungeonmania.entities.movingentities.playerstates.InvisibleState;
import dungeonmania.entities.movingentities.playerstates.PlayerState;
import dungeonmania.entities.movingentities.playerstates.interfaces.Moveable;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.entities.movingentities.properties.movements.MovementStrategy;
import dungeonmania.entities.movingentities.properties.movements.PlayerMovementStrategy;
import dungeonmania.enums.ErrorString;
import dungeonmania.enums.Usable;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.entities.collectableentities.Bomb;

public class Player extends Entity implements Moveable {

    private double health;
    private double attack;
    private Inventory inventory;
    private MovementStrategy moveStrat;
    private Direction movement;
    private List<Mercenary> mercenaries = new ArrayList<>();
    private Queue<Regenerative> queueItems = new LinkedList<>();

    private PlayerState state;
    private Position prevPosition;

    public Player(int id, Position position,
            double player_attack, double player_health, int bowDurability, int shieldDurability, int shieldDefence,
            int armourAttack, int armourDefence, int mindControlDuration) {
        super(id, position, false, false);
        this.prevPosition = position;
        this.health = player_health;
        this.attack = player_attack;
        this.inventory = new Inventory(bowDurability, shieldDurability, shieldDefence, armourAttack, armourDefence,
                mindControlDuration);
        this.state = new AliveState(this);
        this.moveStrat = new PlayerMovementStrategy(this);
    }

    public PlayerState getPlayerState() {
        return state;
    }

    public Direction getMovement() {
        return movement;
    }

    public void setMovement(Direction movement) {
        this.movement = movement;
    }

    public List<Storeable> getInventoryItems() {
        return inventory.getInventoryItems();
    }

    public List<String> getBuildableItems(boolean hasZombie) {
        return inventory.getBuildableItems(hasZombie);
    }

    public void setPlayerState(PlayerState state) {
        this.state = state;
    }

    public void setPlayerStateFromJSON(String jState) {
        if (jState.contains("Alive")) {
            setPlayerState(new AliveState(this));
        } else if (jState.contains("Invincible")) {
            setPlayerState(new InvincibleState(this));
        } else if (jState.contains("Invisible")) {
            setPlayerState(new InvisibleState(this));
        }
    }

    public double getHealth() {

        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double loseHealth(double deltaHealth) {
        health = health + deltaHealth;
        if (health <= 0) {
            health = 0;
            state = null;
        }
        return health;
    }

    public double getAttack() {
        return attack;
    }

    public void addAlly(Mercenary mercenary, boolean mindControl) {
        mercenaries.add(mercenary);
        mercenary.setAlly();
        mercenary.setInteractable(false);
        if (mindControl) {
            mercenary.setMindControl(true);
            mercenary.setDurability(inventory.getMindControlDuration());
        } else {
            inventory.removeItem(mercenary.getBribeAmount(), Treasure.class);
        }
    }

    public void removeAlly(Mercenary mercenary) {
        mercenaries.remove(mercenary);
        mercenary.removeAlly();
        mercenary.setMindControl(false);
    }

    public List<Mercenary> getAllies() {
        return mercenaries;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Position getNextPosition(Direction movement) {
        return this.getPosition().translateBy(movement);
    }

    public void updatePosition(Dungeon dungeon, Direction movement) {
        moveStrat.updateMovement(dungeon, this);
    }

    public void engageBattle(boolean playerDied) {
        if (state != null)
            state.engageBattle(playerDied);
    }

    public void tickPotion() {
        if (queueItems.size() > 0) {
            Regenerative item = queueItems.peek();
            item.decrementDuration();
            if (item.getRemainingDuration() <= 0) {
                queueItems.remove();

                if (queueItems.size() > 0) {
                    nextItem();
                } else {
                    if (state != null)
                        state = new AliveState(this);
                }
            }
        } else {
            if (state != null)
                state.tick(0);
        }
    }

    public void tickMindControl() {
        List<Mercenary> merc = new ArrayList<Mercenary>();
        for (Mercenary mercenary : mercenaries) {
            if (mercenary.isMindControlled()) {
                if (mercenary.getDurability() < 0) {
                    merc.add(mercenary);
                }
                mercenary.reduceDurability();
            }
        }
        for (Mercenary mercenary : merc) {
            removeAlly(mercenary);
        }
    }

    public void drinkInvis(int itemId) {
        if (inventory.getItemFromId(itemId) instanceof Regenerative) {
            Regenerative invisPotion = (Regenerative) inventory.getItemFromId(itemId);
            queueItems.add(invisPotion);
            if (queueItems.size() == 1) {
                if (state != null)
                    state.drinkInvis();
            }
        }
        inventory.removeItemById(itemId);
    }

    public void drinkInvinc(int itemId) {
        if (inventory.getItemFromId(itemId) instanceof Regenerative) {
            System.out.println("am i regen");
            Regenerative invincPotion = (Regenerative) inventory.getItemFromId(itemId);
            queueItems.add(invincPotion);
            if (queueItems.size() == 1) {
                if (state != null)
                    state.drinkInvinc();
            }
        }
        inventory.removeItemById(itemId);
    }

    private void nextItem() {
        if (queueItems.peek() instanceof InvisibilityPotion) {
            if (state != null)
                state.drinkInvis();
        } else if (queueItems.peek() instanceof InvincibilityPotion) {
            if (state != null)
                state.drinkInvinc();
        }
    }

    public boolean hasItem(int id) {
        return inventory.hasItem(id);
    }

    public Storeable getItemFromId(int id) {
        return inventory.getItemFromId(id);
    }

    public void addItem(Entity item) {
        inventory.addItem((Storeable) item);
    }

    public void removeItem(Entity entity) {
        inventory.removeItemById(entity.getEntityId());
    }

    public String itemType(int id) {
        return inventory.getHistoricalItemType(id);
    }

    public void putDownBomb(Dungeon dungeon, int id) {
        ((Bomb) inventory.getItemFromId(id)).drop(dungeon, this);
    }

    public void build(String item, int nextItemId) {
        inventory.build(item, nextItemId);
    }

    public boolean hasKey(int keyPair) {
        return inventory.hasRightKey(keyPair);
    }

    public boolean hasSunStone() {
        return inventory.hasSunStone();
    }

    public Position getPreviousPosition() {
        return prevPosition;
    }

    public void setPreviousPosition(Position prevPosition) {
        this.prevPosition = prevPosition;
    }

    public String attemptBribe(Assassin assassin) {
        String bribeState = this.canBribe(assassin);
        if (bribeState.equals(ErrorString.SUCCESS.toString())) {
            if (inventory.countItem(Sceptre.class) >= 1) {
                addAlly(assassin, true);
            } else if (new Random().nextDouble() > assassin.getFailChance()) {
                addAlly(assassin, false);
            } else {
                inventory.removeItem(assassin.getBribeAmount(), Treasure.class);
            }
        }
        return bribeState;
    }

    public String attemptBribe(Mercenary mercenary) {
        String bribeState = this.canBribe(mercenary);
        if (bribeState.equals(ErrorString.SUCCESS.toString()))
            addAlly(mercenary, inventory.countItem(Sceptre.class) >= 1);
        return bribeState;
    }

    private String canBribe(Mercenary mercenary) {
        if (inventory.countItem(Sceptre.class) > 0) {
            return ErrorString.SUCCESS.toString();
        }
        if (inventory.countItem(Treasure.class) < mercenary.getBribeAmount())
            return ErrorString.BRIBETREAS.toString();
        if (this.getDistanceBetweenTwoEntities(mercenary) > mercenary.getbribeRadius())
            return ErrorString.BRIBERAD.toString();
        if (!mercenary.isInteractable())
            return ErrorString.NOTINTERACT.toString();
        return ErrorString.SUCCESS.toString();
    }

    @Override
    public String getType() {
        return "player";
    }

    public boolean hasWeapon() {
        return inventory.getAttackingItems().size() > 0;
    }

    public boolean hasKey() {
        return inventory.getKeys().size() > 0;
    }

    public void updatePosition(Dungeon dungeon, Player player) {
        return;
    }

    public boolean isTangible() {
        return true;
    }

    public MovementStrategy getMovementStrategy() {
        return moveStrat;
    }

    public void changeMovementStrategy(MovementStrategy movementStrategy) {
        this.moveStrat = movementStrategy;
    }

    public boolean isAlly() {
        return true;
    }

    public void drinkPotion(int id) {
        if (itemType(id).equals(Usable.INVINCE.toString())) {
            drinkInvinc(id);
        } else if (itemType(id).equals(Usable.INVIS.toString())) {
            drinkInvis(id);
        }
    }

    @Override
    public JsonObject getJson() {
        JsonObject entityJSON = new JsonObject();
        entityJSON.addProperty("id", super.getEntityId());
        entityJSON.addProperty("type", this.getType());
        entityJSON.addProperty("x", this.getPosition().getX());
        entityJSON.addProperty("y", this.getPosition().getY());
        entityJSON.addProperty("playerState", this.state.toString());
        entityJSON.addProperty("health", this.health);

        // need to make a method to convert List to json array
        // TODO
        JsonArray allys = new JsonArray();
        mercenaries.forEach(e -> allys.add(e.getJson()));
        entityJSON.add("allys", allys);

        JsonArray inventorys = new JsonArray();
        inventory.getInventoryItems().forEach(e -> inventorys.add(e.getJson()));
        entityJSON.add("inventory", inventorys);

        JsonArray potQ = new JsonArray();
        queueItems.forEach(e -> potQ.add(e.getJson()));
        entityJSON.add("potionQ", potQ);

        return entityJSON;
    }

}

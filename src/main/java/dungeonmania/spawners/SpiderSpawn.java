package dungeonmania.spawners;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.util.Position;

public class SpiderSpawn {
    List<Position> spawnBlocks = new ArrayList<>();
    int spawnRate;
    
    public SpiderSpawn(int spawnRate){
        this.spawnBlocks = 
            List.of(new Position(0, 1),
                    new Position(1, 1),
                    new Position(0, 0),
                    new Position(1, 0));

        this.spawnRate = spawnRate;
    }

    public SpiderSpawn(Position spiderSpawnLocation, int spawnRate){
        this.spawnBlocks = 
            List.of(spiderSpawnLocation,
                    new Position(spiderSpawnLocation.getX(), spiderSpawnLocation.getY() + 1),
                    new Position(spiderSpawnLocation.getX() + 1, spiderSpawnLocation.getY() + 1),
                    new Position(spiderSpawnLocation.getX() + 1, spiderSpawnLocation.getY()));

        this.spawnRate = spawnRate;
    }

    public List<Position> getSpawnLocation(){
        return this.spawnBlocks;
    }

    public void setSpawnLocation(Position bottomLeft){
        this.spawnBlocks = 
            List.of(bottomLeft,
                    new Position(bottomLeft.getX(), bottomLeft.getY() + 1),
                    new Position(bottomLeft.getX() + 1, bottomLeft.getY() + 1),
                    new Position(bottomLeft.getX() + 1, bottomLeft.getY()));
    }

    public int getSpawnRate(){
        return this.spawnRate;
    }

    public void setSpawnRate(int spawnRate){
        this.spawnRate = spawnRate;
    }
}

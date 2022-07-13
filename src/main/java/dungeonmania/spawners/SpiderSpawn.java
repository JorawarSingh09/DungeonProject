package dungeonmania.spawners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.entities.movingentities.Spider;
import dungeonmania.util.Position;

public class SpiderSpawn {
    List<Position> spawnArea = new ArrayList<>();
    int spawnRate;
    int radius;
    
    public SpiderSpawn(int spawnRate, Position playerSpawnBlock){
        this.radius = 4;
        this.spawnArea = playerSpawnBlock.getAdjacentPositions();
        this.spawnArea.removeAll(playerSpawnBlock.getCardinallyAdjacentPositions());
        this.spawnArea = this.spawnArea.stream().map(p -> p.scale(radius)).
                                    collect(Collectors.toList());
        this.spawnRate = spawnRate;
        
    }

    public Spider spawnSpider(int currentMaxId){
        Random r = new Random();
        int maxX = Collections.max((spawnArea.stream().
                                    map(p -> p.getX()).
                                    collect(Collectors.toList())));
        int maxY = Collections.max((spawnArea.stream().
                                    map(p -> p.getY()).
                                    collect(Collectors.toList())));

        int minX = Collections.min((spawnArea.stream().
                                    map(p -> p.getX()).
                                    collect(Collectors.toList())));
        int minY = Collections.min((spawnArea.stream().
                                    map(p -> p.getY()).
                                    collect(Collectors.toList())));

        return new Spider(currentMaxId, new Position(
            r.nextInt(maxX-minX) + minX, 
            r.nextInt(maxY-minY) + minY), 
            false, 
            false, 
            10, 
            10);
    }

    public int getSpawnRate(){
        return this.spawnRate;
    }

    public void setSpawnRate(int spawnRate){
        this.spawnRate = spawnRate;
    }
}

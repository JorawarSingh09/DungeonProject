package dungeonmania.interfaces;

import dungeonmania.dungeon.Dungeon;

public interface Spawn<T> {

    public T spawnEntity(int id, Dungeon dungeon);

    public int getSpawnRate();

    public void setSpawnRate(int spawnRate);
}

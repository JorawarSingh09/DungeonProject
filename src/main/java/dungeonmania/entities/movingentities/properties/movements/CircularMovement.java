package dungeonmania.entities.movingentities.properties.movements;

import java.util.ArrayList;
import java.util.List;

public class CircularMovement<E> extends ArrayList<E> {
    @Override
    public E get(int index) {
        return super.get(Math.abs(index % size()));
    }

    public void populatePath(List<E> rawPath){
        for(E pos : rawPath){
            this.add(pos);
        }
    }
}
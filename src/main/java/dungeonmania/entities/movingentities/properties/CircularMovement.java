package dungeonmania.entities.movingentities.properties;

import java.util.ArrayList;
import java.util.List;

public class CircularMovement<E> extends ArrayList<E> {
    @Override
    public E get(int index) {
        return super.get(index % size());
    }

    public void populatePath(List<E> rawPath){
        for(E pos : rawPath){
            this.add(pos);
        }
    }
}
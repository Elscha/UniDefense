package uni_defense.logic.enemies;

import uni_defense.logic.world.World;

public class Worker extends Enemy {

    public Worker(World world) {
        super(world, 0.5, 10);
    }
    
    public Worker(World world, double speed, int size) {
    	super(world, speed, size, 10);
    }

}

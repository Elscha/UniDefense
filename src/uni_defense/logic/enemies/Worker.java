package uni_defense.logic.enemies;

import uni_defense.logic.world.World;

public class Worker extends Enemy {

    public Worker(World world) {
        super(world);
    }
    
    public Worker(World world, double speed, int size) {
    	super(world, size);
    }
    
    @Override
    public double getSpeed() {
        return 0.5;
    }
    
    @Override
    public int getMaxHp() {
        return 10;
    }
    
    @Override
    public int getGold() {
        return 1;
    }

}

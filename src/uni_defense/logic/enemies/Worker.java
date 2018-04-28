package uni_defense.logic.enemies;

import uni_defense.logic.world.World;

public class Worker extends Enemy {

    private boolean boss;
    
    public Worker(World world) {
        super(world);
    }
    
    public Worker(World world, boolean boss) {
    	super(world);
    	this.boss = boss;
    	
    	if (boss) {
    	    initHp(); // init again, because boss is only set now
    	}
    }
    
    @Override
    public double getSpeed() {
        return boss ? 0.35 : 0.5;
    }
    
    @Override
    public int getMaxHp() {
        return boss ? 150 : 15;
    }
    
    @Override
    public int getGold() {
        return boss ? 100 : 1;
    }
    
    @Override
    public int getSize() {
        return boss ? 64 : 0;
    }

}

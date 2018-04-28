package uni_defense.logic.enemies;

import uni_defense.logic.player.Player;
import uni_defense.logic.world.World;

public class Fly extends Enemy {

	private boolean boss;
	
    public Fly(World world) {
        this(world, false);
    }
    
    public Fly(World world, boolean boss) {
    	super(world);
    	this.boss = boss;
    }

    @Override
    public double getSpeed() {
        return boss ? 0.5 : 0.35 ;
    }

    @Override
    public int getMaxHp() {
        return boss ? 45 : 15;
    }

    @Override
    public int getGold() {
        return boss ? 15 : 10;
    }
    
    @Override
    public void step(double dtime) {
        double walkInThisStep = (dtime / 1000) * getSpeed();
        
        if (isFrozen()) {
            walkInThisStep *= 0.5;
        }
        
        double dx = finalTarget.getX() - getX();
        double dy = finalTarget.getY() - getY();
        
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (walkInThisStep >= distance) {
            Player.INSTANCE.damage(getDamage());
            world.removeObject(this);
            Player.INSTANCE.decreaseEnemiesAliveByOne();
            
        } else {
            double ratio = walkInThisStep / distance;
            
            move(dx * ratio, dy * ratio);
        }
    }

}

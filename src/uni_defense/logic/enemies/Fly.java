package uni_defense.logic.enemies;

import uni_defense.logic.player.Player;
import uni_defense.logic.world.World;

public class Fly extends Enemy {

    public Fly(World world) {
        super(world);
    }

    @Override
    public double getSpeed() {
        return 0.35;
    }

    @Override
    public int getMaxHp() {
        return 15;
    }

    @Override
    public int getGold() {
        return 10;
    }
    
    @Override
    public void step(double dtime) {
        double walkInThisStep = (dtime / 1000) * getSpeed();
        
        double dx = finalTarget.getX() - getX();
        double dy = finalTarget.getY() - getY();
        
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (walkInThisStep >= distance) {
            Player.INSTANCE.damage(getDamage());
            world.removeObject(this);
            
        } else {
            double ratio = walkInThisStep / distance;
            
            move(dx * ratio, dy * ratio);
        }
    }

}

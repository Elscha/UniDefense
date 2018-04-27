package uni_defense.logic.buildings.bullets;

import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.World;

public class Fireball extends MovableObject {

    /**
     * tiles per second.
     */
    private double speed = 3;
    
    private int damage = 7;
    
    private Enemy target;
    
    private World world;
    
    public Fireball(World world, double x, double y, Enemy target) {
        super(x, y);
        this.world = world;
        this.target = target;
    }
    
    @Override
    public void step(double dtime) {
        double movementThisStep = dtime / 1000.0 * speed;
        
        double dx = target.getX() - getX();
        double dy = target.getY() - getY();
        
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (movementThisStep >= distance) {
            world.removeObject(this);
            target.damage(damage);
            
        } else {
            double ratio = movementThisStep / distance;
            
            move(dx * ratio, dy * ratio);
        }
        
    }
    
    @Override
    public int getSize() {
        return 8;
    }

}
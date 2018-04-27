package uni_defense.logic.buildings.bullets;

import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.World;

public class Bullet extends MovableObject {

    /**
     * tiles per second.
     */
    private double speed = 4;
    
    private Enemy target;
    
    private World world;
    
    public Bullet(World world, double x, double y, Enemy target) {
        super(x, y);
        this.world = world;
    }
    
    @Override
    public void step(double dtime) {
        double movementThisStep = dtime / 1000.0 * speed;
        
        double dx = target.getX() - getX();
        double dy = target.getY() - getY();
        
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (movementThisStep >= distance) {
            world.removeObject(this);
            // TODO: damage enemy
            
        } else {
            double ratio = movementThisStep / distance;
            
            move(dx * ratio, dy * ratio);
        }
        
    }

}
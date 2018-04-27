package uni_defense.logic.buildings;

import uni_defense.logic.buildings.bullets.Bullet;
import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.World;

public class Archer extends Building {

    private final double MAX_COOLDOWN = 2000;
    
    private int range;
    
    private double cooldown;
    
    private World world;
    
    public Archer(World world) {
        this.world = world;
    }
    
    private boolean isInRange(Enemy enemy, int x, int y) {
        double dx = enemy.getX() - x;
        double dy = enemy.getY() - y;
        
        return Math.sqrt(dx * dx + dy * dy) <= range;
    }
    
    @Override
    public void step(double dtime, int x, int y) {
        if (cooldown > 0) {
            cooldown -= dtime;
        }
        
        if (cooldown <= 0) {
            // search enemy in range
            for (MovableObject enemy : world.getObjects()) {
                if (enemy instanceof Enemy && isInRange((Enemy) enemy, x, y)) {
                    // shoot the enemy
                    world.addObject(new Bullet(world, x, y, (Enemy) enemy));
                    
                    // cooldown
                    cooldown = MAX_COOLDOWN;
                    break;
                }
            }
        }
    }
    
}

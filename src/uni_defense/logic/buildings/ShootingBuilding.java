package uni_defense.logic.buildings;

import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.World;

public abstract class ShootingBuilding extends Building {

    private World world;
    
    private double cooldown;
    
    public ShootingBuilding(World world) {
        this.world = world;
    }
    
    public World getWorld() {
        return world;
    }
    
    public abstract int getRange();
    
    public abstract double getCooldown();
    
    private boolean isInRange(Enemy enemy, int x, int y) {
        double dx = enemy.getX() - x;
        double dy = enemy.getY() - y;
        
        double distance = Math.sqrt(dx * dx + dy * dy); 
        
        return distance <= getRange();
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
                    shootEnemy(x, y, (Enemy) enemy);
                    
                    // cooldown
                    cooldown = getCooldown();
                    break;
                }
            }
        }
    }
    
    public abstract void shootEnemy(int x, int y, Enemy enemy);
}

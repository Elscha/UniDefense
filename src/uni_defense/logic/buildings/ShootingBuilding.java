package uni_defense.logic.buildings;

import java.util.HashSet;
import java.util.Set;

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
    
    private double getDistance(Enemy enemy, int x, int y) {
        double dx = enemy.getX() - x;
        double dy = enemy.getY() - y;
        
        return Math.sqrt(dx * dx + dy * dy); 
    }
    
    private boolean isInRange(Enemy enemy, int x, int y) {
        return getDistance(enemy, x, y) <= getRange();
    }
    
    @Override
    public void step(double dtime, int x, int y) {
        if (cooldown > 0) {
            cooldown -= dtime;
        }
        
        if (cooldown <= 0) {
            Set<Enemy> enemiesInRange = new HashSet<>();
            
            // search enemy in range
            for (MovableObject enemy : world.getObjects()) {
                if (enemy instanceof Enemy && isInRange((Enemy) enemy, x, y)) {
                    enemiesInRange.add((Enemy) enemy);
                }
            }
            
            if (!enemiesInRange.isEmpty()) {
                // find closest enemy
                Enemy closest = null;
                double distance = 0;
                for (Enemy enemy : enemiesInRange) {
                    double dist = getDistance(enemy, x, y);
                    if (closest == null || distance > dist) {
                        closest = enemy;
                        distance = dist;
                    }
                }
                
                // shoot the enemy
                shootEnemy(x, y, closest);
                
                // cooldown
                cooldown = getCooldown();
            }
        }
    }
    
    public abstract void shootEnemy(int x, int y, Enemy enemy);
}

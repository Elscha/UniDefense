package uni_defense.logic.buildings;

import java.util.HashSet;
import java.util.Set;

import uni_defense.logic.buildings.bullets.IceTowerEffect;
import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.World;

public class IceTower extends Building {

    public static final int RANGE = 4;
    
    private World world;
    
    private double warmup;
    
    private static final double MAX_WARMUP = 5000;
    
    public IceTower(World world) {
        this.world = world;
    }
    
    private double getDistance(Enemy enemy, int x, int y) {
        double dx = enemy.getX() - x;
        double dy = enemy.getY() - y;
        
        return Math.sqrt(dx * dx + dy * dy); 
    }
    
    private boolean isInRange(Enemy enemy, int x, int y) {
        return getDistance(enemy, x, y) <= RANGE;
    }
    
    @Override
    public void step(double dtime, int x, int y) {
        if (warmup == 0) {
            boolean enemyInRange = false;
            
            // search enemy in range
            for (MovableObject enemy : world.getObjects()) {
                if (enemy instanceof Enemy && isInRange((Enemy) enemy, x, y)) {
                    enemyInRange = true;
                }
            }
            
            if (enemyInRange) {
                warmup += dtime;
            }
            
        } else {
            warmup += dtime;
            
            if (warmup >= MAX_WARMUP) {
                warmup = 0;
                
                Set<Enemy> enemiesInRange = new HashSet<>();
                
                // search enemy in range
                for (MovableObject enemy : world.getObjects()) {
                    if (enemy instanceof Enemy && isInRange((Enemy) enemy, x, y)) {
                        enemiesInRange.add((Enemy) enemy);
                    }
                }
                
                if (!enemiesInRange.isEmpty()) {

                    world.addObject(new IceTowerEffect(x, y, world));
                    
                    for (Enemy e : enemiesInRange) {
                        e.freeze();
                    }
                    
                }

            }
        }
        
    }

    @Override
    public int getInfluenceRange() {
        return RANGE;
    }

    @Override
    public int getThreatLevel() {
        return 1;
    }

}

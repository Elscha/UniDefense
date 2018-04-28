package uni_defense.logic.enemies.wave;

import uni_defense.logic.enemies.Anke;
import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.enemies.Worker;
import uni_defense.logic.world.World;

public class Wave2 extends AbstractWave {

    private final double TIMING = 2000;
    
    private int numWorkers;
    
    private int created;
    
    private World world;
    
    private double timer;
    
    public Wave2(World world) {
        this.world = world;
        
        numWorkers = (int) (Math.random() * 5 + 20);
    }
    
    @Override
    public void step(double dtime) {
        if (done()) {
            return;
        }
        
        timer += dtime;
        
        if (timer > TIMING) {
            timer = 0;
            
            Enemy nextEnemy;
            if ((int) (Math.random() * 100) > 80) {
            	nextEnemy = new Anke(world);
            } else {
            	nextEnemy = new Worker(world);
            }
            
            world.addObject(nextEnemy);
            created++;
        }
    }
    
    @Override
    public boolean done() {
        return created >= numWorkers;
    }
    
    @Override
    public String getName() {
        return "Horrible Shrew";
    }
    
    public static byte getId() {
        return 3;
    }
    
}

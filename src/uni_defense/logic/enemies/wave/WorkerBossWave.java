package uni_defense.logic.enemies.wave;

import uni_defense.logic.enemies.Worker;
import uni_defense.logic.world.World;

public class WorkerBossWave extends AbstractWave {

    private final double TIMING = 1200;
    
    private int numWorkers;
    
    private int created;
    
    private World world;
    
    private double timer;
    
    public WorkerBossWave(World world) {
        this.world = world;
        
        numWorkers = (int) (Math.random() * 2 + 2);
    }
    
    @Override
    public void step(double dtime) {
        if (done()) {
            return;
        }
        
        timer += dtime;
        
        if (timer > TIMING) {
            timer = 0;
            
            if (created + 1 == numWorkers) {
                world.addObject(new Worker(world, true));
            } else {
                world.addObject(new Worker(world));
            }
            created++;
        }
    }
    
    @Override
    public boolean done() {
        return created >= numWorkers;
    }
    
}

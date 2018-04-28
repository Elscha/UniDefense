package uni_defense.logic.enemies.wave;

import uni_defense.logic.enemies.Worker;
import uni_defense.logic.world.World;

public class WorkerWave extends AbstractWave {

    private final double TIMING = 2000;
    
    private int numWorkers;
    
    private int created;
    
    private World world;
    
    private double timer;
    
    public WorkerWave(World world) {
        this.world = world;
        
        numWorkers = (int) (Math.random() * 5 + 5);
    }
    
    @Override
    public void step(double dtime) {
        if (done()) {
            return;
        }
        
        timer += dtime;
        
        if (timer > TIMING) {
            timer = 0;
            
            world.addObject(new Worker(world));
            created++;
        }
    }
    
    @Override
    public boolean done() {
        return created >= numWorkers;
    }
    
    @Override
    public String getName() {
        return "Workers";
    }
    
    public static byte getId() {
        return 1;
    }
    
}

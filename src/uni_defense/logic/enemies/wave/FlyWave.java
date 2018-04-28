package uni_defense.logic.enemies.wave;

import uni_defense.logic.enemies.Fly;
import uni_defense.logic.world.World;

public class FlyWave extends AbstractWave {

    private final double TIMING = 5000;
    
    private int numFlies;
    
    private int created;
    
    private World world;
    
    private double timer;
    
    public FlyWave(World world) {
        this.world = world;
        
        numFlies = (int) (Math.random() * 2 + 2);
    }
    
    @Override
    public void step(double dtime) {
        if (done()) {
            return;
        }
        
        timer += dtime;
        
        if (timer > TIMING) {
            timer = 0;
            
            world.addObject(new Fly(world));
            created++;
        }
    }
    
    @Override
    public boolean done() {
        return created >= numFlies;
    }
    
    @Override
    public String getName() {
        return "Flies";
    }
    
    public static byte getId() {
        return 4;
    }
    
}

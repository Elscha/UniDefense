package uni_defense.logic.enemies.wave;

import uni_defense.logic.world.World;

public abstract class AbstractWave {
    
    public abstract void step(double dtime);

    public abstract boolean done();
    
    public abstract String getName();
    
    public static AbstractWave createWaveById(World world, byte id) {
        switch (id) {
        case 1: return new WorkerWave(world);
        case 2: return new WorkerBossWave(world);
        case 3: return new AnkeWave(world);
        case 4: return new FlyWave(world);
        default: return null;
        }
    }
    
}

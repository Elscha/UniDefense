package uni_defense.logic.enemies.wave;

public abstract class AbstractWave {
    
    public abstract void step(double dtime);

    public abstract boolean done();
    
}

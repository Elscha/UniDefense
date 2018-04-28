package uni_defense.logic.buildings.bullets;

import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.World;

public class IceTowerEffect extends MovableObject {

    private double timer;
    
    private World world;
    
    public IceTowerEffect(double x, double y, World world) {
        super(x, y);
        this.world = world;
    }
    
    @Override
    public void step(double dtime) {
        timer += dtime;
        
        if (timer > 500) {
            world.removeObject(this);
        }
    }

}

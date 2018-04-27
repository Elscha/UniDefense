package uni_defense.logic.enemies;

import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.Point;
import uni_defense.logic.world.World;

public abstract class Enemy extends MovableObject {

    /**
     * The final target to move to.
     */
    private Point finalTarget;
    
    /**
     * The next tile to move on.
     */
    private Point currentTarget;
    
    public Enemy(World world) {
        super(world.getSpawnPos().getX(), world.getSpawnPos().getY());
        
        this.finalTarget = world.getCastlePos();
    }
    
    @Override
    public void step(long dtime) {
        
    }

}

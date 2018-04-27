package uni_defense.logic.enemies;

import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.PathFinder;
import uni_defense.logic.world.Point;
import uni_defense.logic.world.World;

public abstract class Enemy extends MovableObject {

    private PathFinder pathFinder;
    
    /**
     * tiles per second.
     */
    private float speed;
    
    /**
     * The final target to move to.
     */
    private Point finalTarget;
    
    /**
     * The next tile to move on.
     */
    private Point currentTarget;
    
    /**
     * @param speed Tiles per second.
     */
    public Enemy(World world, float speed) {
        super(world.getSpawnPos().getX(), world.getSpawnPos().getY());
        
        this.finalTarget = world.getCastlePos();
        this.pathFinder = new PathFinder(world);
        
        findNextCurrentTarget();
    }
    
    private void findNextCurrentTarget() {
        currentTarget = pathFinder.findPath(Math.round(getX()), Math.round(getY()), finalTarget.getX(), finalTarget.getY()).get(1);
    }
    
    @Override
    public void step(long dtime) {
        float walkInThisStep = (float) (dtime / 1000000000.) * speed;
        
        float dx = (getX() - currentTarget.getX());
        float dy = (getY() - currentTarget.getY());
        
        float distToCurrentTarget = (float) Math.sqrt(dx * dx + dy * dy);
        
        if (walkInThisStep >= distToCurrentTarget) {
            setX(currentTarget.getX());
            setY(currentTarget.getY());
            
            findNextCurrentTarget();
            
        } else {
            
            float ratio = walkInThisStep / distToCurrentTarget;
            float moveX = dx * ratio;
            float moveY = dy * ratio;
            
            move(moveX, moveY);
        }
        
    }
    
    public Point getCurrentTile() {
        return currentTarget;
    }

}

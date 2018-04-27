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
    private double speed;
    
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
    public Enemy(World world, double speed) {
        super(world.getSpawnPos().getX(), world.getSpawnPos().getY());
        
        this.speed = speed;
        this.finalTarget = world.getCastlePos();
        this.pathFinder = new PathFinder(world);
        
        currentTarget = findNextCurrentTarget();
        
    }
    
    // protected for test cases
    protected Point findNextCurrentTarget() {
//        return pathFinder.findPath((int) Math.round(getX()), (int) Math.round(getY()), finalTarget.getX(), finalTarget.getY()).get(1);
        return new Point(32, 62);
    }
    
    @Override
    public void step(double dtime) {
        double walkInThisStep = (dtime / 1000) * speed;
        
        double dx = currentTarget.getX() - getX();
        double dy = currentTarget.getY() - getY();
        
        if (Math.abs(dx) > Math.abs(dy)) {
            
            if (walkInThisStep > Math.abs(dx)) {
                setX(currentTarget.getX());
                currentTarget = findNextCurrentTarget();
                
            } else {
                setX(getX() + Math.signum(dx) * walkInThisStep);
            }
            
        } else {
            
            if (walkInThisStep > Math.abs(dy)) {
                setY(currentTarget.getY());
                currentTarget = findNextCurrentTarget();
                
            } else {
                setY(getY() + Math.signum(dy) * walkInThisStep);
            }
            
        }
    }
    
    @Override
    public Point getCurrentTile() {
        // an enemey always "blocks" the tile that it is moving to
        return currentTarget;
    }

}

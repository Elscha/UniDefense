package uni_defense.logic.enemies;

import java.util.List;

import uni_defense.logic.player.Player;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.PathFinder;
import uni_defense.logic.world.Point;
import uni_defense.logic.world.World;

public abstract class Enemy extends MovableObject {

    private World world;
    
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
    
    private int size;
    
    private int hp;
    
    /**
     * @param speed Tiles per second.
     */
    public Enemy(World world, double speed, int hp) {
        this(world, speed, 0, hp);
    }
    
    public Enemy(World world, double speed, int size, int hp) {
        super(world.getSpawnPos().getX(), world.getSpawnPos().getY());
        
        this.world = world;
        
        this.speed = speed;
        this.finalTarget = world.getCastlePos();
        this.pathFinder = new PathFinder(world);
        
        currentTarget = findNextCurrentTarget();
        
        this.size = size;
        this.hp = hp;
    }
    
    @Override
    public int getSize() {
		return size;
	}

	// protected for test cases
    protected Point findNextCurrentTarget() {
        List<Point> path = pathFinder.findPath((int) Math.round(getX()), (int) Math.round(getY()), finalTarget.getX(), finalTarget.getY());
        
        System.out.println("Got Path: " + path);
        
        if (path == null || path.size() < 2) {
            return new Point((int) Math.round(getX()), (int) Math.round(getY()));
        } else {
            return path.get(1);
        }
        
    }
    
    @Override
    public void step(double dtime) {
        double walkInThisStep = (dtime / 1000) * speed;
        
        if (Math.round(getX()) == finalTarget.getX() && Math.round(getY()) == finalTarget.getY()) {
            Player.INSTANCE.damage(getDamage());
            world.removeObject(this);
            return;
        }
        
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
    
    public void damage(int damage) {
        this.hp -= damage;
        if (hp <= 0) {
            world.removeObject(this);
        }
    }
    
    @Override
    public Point getCurrentTile() {
        // an enemey always "blocks" the tile that it is moving to
        return currentTarget;
    }

    @Override
    public String getID() {
    	return this.getClass().getSimpleName().toLowerCase() + size;
    }
    
    /**
     * How much damage this enemy does, when it reaches the castle.
     */
    public int getDamage() {
        return 1;
    }
    
}

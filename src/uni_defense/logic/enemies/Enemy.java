package uni_defense.logic.enemies;

import java.util.List;

import uni_defense.audio.Sound;
import uni_defense.logic.player.Player;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.PathFinder;
import uni_defense.logic.world.Point;
import uni_defense.logic.world.World;

public abstract class Enemy extends MovableObject {

    protected World world;
    
    private PathFinder pathFinder;
    
    /**
     * The final target to move to.
     */
    protected Point finalTarget;
    
    /**
     * The next tile to move on.
     */
    private Point currentTarget;
    
    private int hp;
    
    public Enemy(World world) {
        super(world.getSpawnPos().getX(), world.getSpawnPos().getY());
        Player.INSTANCE.setEnemiesAlive(Player.INSTANCE.getEnemiesAlive() + 1);
        
        this.world = world;
        
        this.finalTarget = world.getCastlePos();
        this.pathFinder = new PathFinder(world);
        
        currentTarget = findNextCurrentTarget();
        
        initHp();
    }
    
    protected void initHp() {
        this.hp = getMaxHp();
    }
    
    /**
     * tiles per second. 
     */
    public abstract double getSpeed();
    
	// protected for test cases
    protected Point findNextCurrentTarget() {
        List<Point> path = pathFinder.findPath((int) Math.round(getX()), (int) Math.round(getY()), finalTarget.getX(), finalTarget.getY());
        
        if (path == null || path.size() < 2) {
            return new Point((int) Math.round(getX()), (int) Math.round(getY()));
        } else {
            return path.get(1);
        }
        
    }
    
    @Override
    public void step(double dtime) {
        double walkInThisStep = (dtime / 1000) * getSpeed();
        
        if (Math.round(getX()) == finalTarget.getX() && Math.round(getY()) == finalTarget.getY()) {
            Player.INSTANCE.damage(getDamage());
            world.removeObject(this);
            Player.INSTANCE.decreaseEnemiesAliveByOne();
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
        if (hp > 0) {
            this.hp -= damage;
            if (hp <= 0) {
                world.removeObject(this);
                Player.INSTANCE.updateGold(getGold());
                Player.INSTANCE.increaseEnemiesKilledByOne();
                Player.INSTANCE.decreaseEnemiesAliveByOne();
                playDeathSound();
            }
        }
    }
    
    protected void playDeathSound() {
        new Sound("sfx/death.wav").soundStart();
    }
    
    @Override
    public Point getCurrentTile() {
        // an enemey always "blocks" the tile that it is moving to
        return currentTarget;
    }

    @Override
    public String getID() {
    	return this.getClass().getSimpleName().toLowerCase() + getSize();
    }
    
    /**
     * How much damage this enemy does, when it reaches the castle.
     */
    public int getDamage() {
        return 1;
    }
    
    public int getHp() {
        return hp;
    }
    
    public abstract int getMaxHp();
    
    public abstract int getGold();
    
}

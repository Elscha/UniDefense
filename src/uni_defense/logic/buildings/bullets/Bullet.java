package uni_defense.logic.buildings.bullets;

import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.GroundTile;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.Point;
import uni_defense.logic.world.World;

public class Bullet extends MovableObject {

    /**
     * tiles per second.
     */
    private double speed = 4;
    
    private int damage = 5;
    
    private Enemy target;
    
    private World world;

    private Point bulletSpawn;
    
    public Bullet(World world, double x, double y, Enemy target) {
        super(x, y);
        this.world = world;
        this.target = target;
        this.bulletSpawn = new Point((int) Math.round(x), (int) Math.round(y));
    }
    
    @Override
    public void step(double dtime) {
        double movementThisStep = dtime / 1000.0 * speed;
        
        double dx = target.getX() - getX();
        double dy = target.getY() - getY();
        
        double distance = Math.sqrt(dx * dx + dy * dy);

        Point tilePos = getCurrentTile();
        if(!tilePos.equals(bulletSpawn)){
            if(world.getGroundTile(tilePos.getX(), tilePos.getY()).equals(GroundTile.STONE)
                    || world.getBuilding(tilePos.getX(), tilePos.getY()) != null){
                world.removeObject(this);
            }
        }


        if (movementThisStep >= distance) {
            world.removeObject(this);
            target.damage(damage);
            
        } else {
            double ratio = movementThisStep / distance;
            
            move(dx * ratio, dy * ratio);
        }
        
    }
    
    @Override
    public int getSize() {
        return 8;
    }

}

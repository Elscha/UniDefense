package uni_defense.logic.world;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uni_defense.Main;
import uni_defense.logic.buildings.Building;
import uni_defense.logic.enemies.Worker;

public class World {
    
    private Point spawn;
    
    private Point castle;
    
    // [x][y]
    private GroundTile[][] ground;
    
    // [x][y]
    private Building[][] buildings;
    private Set<MovableObject> objects;
    
    private Set<MovableObject> markedForRemoval = new HashSet<>();
    
    public World() {
        this.spawn = new Point(Math.round(Main.STANDARD_WIDTH/2), Math.round(Main.STANDARD_HEIGHT/2));
        this.castle = new Point(Math.round(Main.STANDARD_WIDTH/2), 0);

        initGround();

        buildings = new Building[Main.STANDARD_WIDTH][Main.STANDARD_HEIGHT];
        
        objects = new HashSet<>(1337);
        objects.add(new Worker(this));
        objects.add(new Worker(this, 0.1f, 64));
    }

    public World(GroundTile[][] ground, Point spawn, Point castle){
        this.spawn = spawn;
        this.castle = castle;

        setGround(ground);

        objects = new HashSet<>(1337);
    }
    
    private void initGround() {
        ground = new GroundTile[Main.STANDARD_WIDTH][Main.STANDARD_HEIGHT];
        
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                ground[i][j] = GroundTile.GRASS;
            }
        }
    }
    
    /**
     * [0][0] is top left.
     */
    public GroundTile getGroundTile(int x, int y) {
        return ground[x][y];
    }
    
    public boolean isWalkable(int x, int y) {
    	return ground[x][y].isWalkable() && buildings[x][y] == null;
    }
    
    public boolean isBuildable(int x, int y) {
        boolean canBuild = ground[x][y].isBuildable() && buildings[x][y] == null;
        
        canBuild &= castle.getX() != x || castle.getY() != y;
        
        if (canBuild) {
            for (MovableObject obj : objects) {
                Point objPos = obj.getCurrentTile();
                canBuild &= objPos.getX() != x || objPos.getY() != y;
                
                if (!canBuild) {
                    break;
                }
            }
        }
        
        return canBuild;
    }

    /**
     * [0][0] is top left.
     * 
     * @return <code>null</code> if no building is there.
     */
    public Building getBuilding(int x, int y) {
        return buildings[x][y];
    }
    
    public int getWidth() {
        return ground.length;
    }
    
    public int getHeight() {
        return ground[0].length;
    }
    
    public Set<MovableObject> getObjects() {
        return Collections.unmodifiableSet(objects);
    }
    
    public void addObject(MovableObject object) {
        objects.add(object);
    }
    
    /**
     * Will remove once step() is called next.
     */
    public void removeObject(MovableObject object) {
        markedForRemoval.add(object);
    }

    public Point getCastlePos() {
        return castle;
    }
    
    public Point getSpawnPos() {
        return spawn;
    }
    
    /**
     * Simulates a single step for the enemies.
     * 
     * @param dtime The time since the last step in milliseconds.
     */
    public void step(double dtime) {
        objects.removeAll(markedForRemoval);
        
        for (MovableObject obj : objects) {
            obj.step(dtime);
        }
    }

    public GroundTile[][] getGround() {
        return ground;
    }

    public void setGround(GroundTile[][] newGround) {
        ground = newGround;
        buildings = new Building[getWidth()][getHeight()];
    }
}

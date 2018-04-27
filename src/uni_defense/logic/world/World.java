package uni_defense.logic.world;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uni_defense.logic.buildings.Building;

public class World {

    private int height;
    
    private int width;
    
    private Point spawn;
    
    private Point castle;
    
    // [x][y]
    private GroundTile[][] ground;
    
    // [x][y]
    private Building[][] buildings;
    
    private Set<MovableObject> objects;
    
    public World() {
        height = 64;
        width = 64;
        
        initGround();
        
        buildings = new Building[width][height];
        
        objects = new HashSet<>(1337);
        
        this.spawn = new Point(32, 63);
        this.castle = new Point(32, 0);
    }
    
    private void initGround() {
        ground = new GroundTile[width][height];
        
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
    
    public boolean isWalkable (int x, int y) {
    	return ground[x][y].isWalkable() && buildings[x][y]==null;
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
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public Set<MovableObject> getObjects() {
        return Collections.unmodifiableSet(objects);
    }
    
    public void addObject(MovableObject object) {
        objects.add(object);
    }
    
    public void removeObject(MovableObject object) {
        objects.remove(object);
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
     * @param dtime The time since the last step in nanseconds.
     */
    public void step(long dtime) {
        
    }
    
}

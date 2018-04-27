package uni_defense.logic.world;

public enum GroundTile {

    GRASS(true, true),
    
    DIRT(true, false),
    
    STONE(false, true),
    
    WATER(false, false);
    
    
    private boolean walkable;
    
    private boolean buildable;

    private GroundTile(boolean walkable, boolean buildable) {
        this.walkable = walkable;
        this.buildable = buildable;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isBuildable() {
        return buildable;
    }
    
}

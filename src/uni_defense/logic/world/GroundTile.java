package uni_defense.logic.world;

import uni_defense.logic.exceptions.WorldNotFittingException;

import java.util.Arrays;

public enum GroundTile {

    GRASS(true, true), //G
    
    DIRT(true, false), //D
    
    STONE(false, true), //S
    
    WATER(false, false); //W
    
    
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

    public static GroundTile getByFirstLetter(String letter){
        return Arrays.stream(values())
                .filter(groundTile -> groundTile.name().startsWith(letter))
                .findFirst().orElseThrow(() -> new WorldNotFittingException());
    }
    
}

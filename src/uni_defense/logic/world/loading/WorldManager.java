package uni_defense.logic.world.loading;

import uni_defense.logic.exceptions.WorldNotFittingException;
import uni_defense.logic.world.GroundTile;
import uni_defense.logic.world.Point;
import uni_defense.logic.world.World;

import java.io.File;
import java.io.IOException;

public class WorldManager {

    private static World world;

    public static World loadMap(File file) throws IOException, WorldNotFittingException {


        String[][] stringMap = IOUtils.loadMap(file);
        GroundTile[][] ground = new GroundTile[stringMap.length][stringMap[0].length];
        for (int y = 0; y < stringMap[0].length; y++) {
            for (int x = 0; x < stringMap.length; x++) {
                String tile = stringMap[x][y];
                ground[x][y] = GroundTile.getByFirstLetter(tile);
            }
        }

        World newWorld = new World(ground, new Point(0, 0), new Point(0, 0));
        setWorld(newWorld);
        return newWorld;
    }

    public static World getWorld() {
        return world;
    }

    public static void setWorld(World world) {
        WorldManager.world = world;
    }

}

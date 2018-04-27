package uni_defense.logic.world.loading;

import org.junit.Test;
<<<<<<< HEAD
import uni_defense.logic.exceptions.WorldNotFittingException;
=======
>>>>>>> bde6f90bc18285c4119ee7dbe86c16c0b7eb6a43
import uni_defense.logic.world.GroundTile;
import uni_defense.logic.world.World;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class WorldManagerTest {

    @Test
    public void shouldLoadMapAndConvertIt() throws Exception {
        File testFile = new File(getClass().getClassLoader().getResource("sampleMap_01.csv").getFile());
        File testFile2 = new File(getClass().getClassLoader().getResource("sampleMap_02.csv").getFile());

        WorldManager.loadMap(testFile);
        World w = WorldManager.getWorld();

        GroundTile[][] ground = w.getGround();

        assertNotNull(ground);

        assertEquals(6, w.getHeight());
        assertEquals(6, w.getWidth());

        WorldManager.loadMap(testFile2);

        World w2 = WorldManager.getWorld();

        GroundTile[][] ground2 = w2.getGround();

        assertNotNull(2);

        assertEquals(8, w2.getHeight());
        assertEquals(6, w2.getWidth());

    }

    @Test(expected = WorldNotFittingException.class)
    public void shouldNotLoadMap() throws Exception {
        File testFile = new File(getClass().getClassLoader().getResource("sampleMap_01_not_passing.csv").getFile());

        WorldManager.loadMap(testFile);
    }

}
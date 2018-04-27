package uni_defense.logic.world.loading;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class IOUtilsTest {

    @Test
    public void shouldLoadMap_whenMapFormatIsCorrect_producesCorrectMapAsArray() throws Exception{
        File testFile = new File(getClass().getClassLoader().getResource("sampleMap_01.csv").getFile());
        File testFile2 = new File(getClass().getClassLoader().getResource("sampleMap_02.csv").getFile());

        String[][] map = IOUtils.loadMap(testFile);
        String[][] map2 = IOUtils.loadMap(testFile2);

        assertNotNull(map);
        assertNotNull(map2);

        assertEquals(6, map.length);
        assertEquals(6, map[0].length);

        assertEquals(6, map2.length); //x
        assertEquals(8, map2[0].length); //y
    }
}
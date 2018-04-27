package uni_defense.logic.world.loading;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOUtils {

    public static String[][] loadLevelConfiguration(File file) throws IOException{
        List<List<String>> map = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;

        while ((line = br.readLine()) != null) {
            List<String> row = Arrays.asList(line.split(";"));
            map.add(row);
        }
        Integer biggestX = 0;
        for(List<String> row : map){
            if(row.size() > biggestX){
                biggestX = row.size();
            }
        }

        String[][] mapArr = new String[biggestX][map.size()];

        for (int y = 0; y < map.size(); y++) {
            List<String> row = map.get(y);
            for (int x = 0; x < row.size(); x++) {
                mapArr[x][y] = row.get(x);
                //System.out.println("x = " + x+", y = "+y+" : "+row.get(x));
            }
        }
        return mapArr;
    }


}

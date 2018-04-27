package uni_defense.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(File file) {
		BufferedImage bi = null;
        //System.err.println("....setimg...." + fileName);

        try {
            bi = ImageIO.read(file); 

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image could not be read");
            System.exit(1);
        }
        System.out.println(bi);

        return bi;
    }

}

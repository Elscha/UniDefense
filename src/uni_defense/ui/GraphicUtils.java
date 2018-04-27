package uni_defense.ui;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicUtils {
	
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

	
	/**
	 * 
	 * @see <a href="https://stackoverflow.com/a/4216635">https://stackoverflow.com/a/4216635</a>
	 */
	public static BufferedImage scale(BufferedImage before, int size) {
	     int w = before.getWidth();
	     int h = before.getHeight();
	     BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	     AffineTransform at = new AffineTransform();
	     
	     double widthScale = (double) size / after.getWidth();
	     double heightcale = (double) size / after.getHeight();
	     
	     at.scale(widthScale, heightcale);
	     AffineTransformOp scaleOp = 
	        new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
	     after = scaleOp.filter(before, after);
	     
	     return after;
	}
}

package uni_defense.ui;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicUtils {
	
	public final static String BASE_FOLDER =  GraphicUtils.class.getResource(".").getFile() + "/";
	
	public static BufferedImage loadImage(File file) {
		BufferedImage bi = null;
        try {
            bi = ImageIO.read(file); 

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

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
	     
//	     System.out.println(after.getWidth());
	     
	     double widthScale = 0.5f * ((double) size / after.getWidth());
	     double heightcale = 0.5f * ((double) size / after.getHeight());
	     
	     at.scale(widthScale, heightcale);
	     AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
	     after = scaleOp.filter(before, after);
	     
	     return after;
	}
}

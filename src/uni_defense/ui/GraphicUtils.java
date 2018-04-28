package uni_defense.ui;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GraphicUtils {
	
	public final static String BASE_FOLDER;
	
	static {
		String folder = "";
		try {
			URL url = GraphicUtils.class.getResource(".");
			if (null != url) {
				folder = url.getFile() + "/";
			} else {
				folder = new File(".").getAbsolutePath() + "/";
			}
//			System.out.println(f.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BASE_FOLDER = folder;
	}
	
	public static BufferedImage loadImage(File file) {
		BufferedImage bi = null;
        try {
            bi = ImageIO.read(file); 

        } catch (IOException e) {
        	System.out.println("Could not load: " + file.getAbsolutePath());
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
	     
	     double widthScale = 0.5f * ((double) size / WorldRenderer.TILE_SIZE);
	     double heightcale = 0.5f * ((double) size / WorldRenderer.TILE_SIZE);
	     
	     at.scale(widthScale, heightcale);
	     AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
	     after = scaleOp.filter(before, after);
	     
	     return after;
	}
}

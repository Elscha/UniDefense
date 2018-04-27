package uni_defense.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;

public class Enemy extends AbstractGraphicComponent implements Runnable {
	
	private File folder;
	private BufferedImage[] images;
	private int pictureIndex = 0;
	
	
	public Enemy(String path) {
		folder = new File(AbstractGraphicComponent.BASE_FOLDER + path);
		
		if (null != folder && folder.isDirectory()) {
			File[] pictures = folder.listFiles();
			
			images = new BufferedImage[pictures.length];
			for (int i = 0; i < pictures.length; i++) {
				images[i] = ImageLoader.loadImage(pictures[i]);
			}
		} else {
			System.err.println("Couldn't load picture");
		}
		
		//new Thread(this).start();
		
	}
	
	
	@Override
	public void update(Graphics g) {
		super.update(g);
		g.drawImage(images[pictureIndex++], 0, 0, null);
		
		if (pictureIndex >= images.length) {
			pictureIndex = 0;
		}
		
	}


	/**
	 * 
	 * @see <a href="https://stackoverflow.com/a/4216635">https://stackoverflow.com/a/4216635</a>
	 */
	private static ImageIcon scale(BufferedImage before) {
	     int w = before.getWidth();
	     int h = before.getHeight();
	     BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	     AffineTransform at = new AffineTransform();
	     at.scale(0.5, 0.5);
	     AffineTransformOp scaleOp = 
	        new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
	     after = scaleOp.filter(before, after);
	     
	     return new ImageIcon(after);
	}
	
	@Override
	public void run() {
		while(true) {
			if (null != getGraphics()) {
				update(getGraphics());
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
	}

}

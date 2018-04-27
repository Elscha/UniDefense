package uni_defense.ui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;

public class BackgroundTile2 extends AbstractGraphicComponent {
	
	private File file;
//	private BufferedImage image;
	
	private static final int HEIGHT = 16;
	private static final int WIDTH = 16;
	
	public BackgroundTile2(ImageIcon image) {
		super(image);
		 setPreferredSize(new Dimension(WIDTH, HEIGHT));
	     setMaximumSize(new Dimension(WIDTH, HEIGHT));
	     setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
	     
	     
	}

//	@Override
//	public void update(Graphics g) {
//		super.update(g);
//		if (null != image) {
//			g.drawImage(image, 0, 0, null);
//		} else {
//			System.err.println("image is null in " + this.getClass().getSimpleName());
//		}
//		
//	}
	
//	@Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (null != image) {
//        	g.drawImage(image, 0, 0, this);
//        } else {
//			System.err.println("image is null in " + this.getClass().getSimpleName());
//		}
//    }
}

package uni_defense.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackgroundTile extends AbstractGraphicComponent {
	
	private File file;
	private BufferedImage image;
	
	private static final int HEIGHT = 16;
	private static final int WIDTH = 16;
	
	public BackgroundTile(String path) {
		 setPreferredSize(new Dimension(WIDTH, HEIGHT));
	     setMaximumSize(new Dimension(WIDTH, HEIGHT));
	     setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		file = new File(AbstractGraphicComponent.BASE_FOLDER + path);
		
		if (null != file && file.isFile()) {
			image = GraphicUtils.loadImage(file);
		} else {
			System.err.println("Couldn't load picture");
		}
	}

	@Override
	public void update(Graphics g) {
		super.update(g);
		if (null != image) {
			g.drawImage(image, 0, 0, null);
		} else {
			System.err.println("image is null in " + this.getClass().getSimpleName());
		}
		
	}
	
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

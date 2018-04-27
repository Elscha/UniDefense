package uni_defense.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackgroundTile extends AbstractGraphicComponent {
	
	private File file;
	private BufferedImage image;
	
	public BackgroundTile(String path) {
		file = new File(AbstractGraphicComponent.BASE_FOLDER + path);
		
		if (null != file && file.isFile()) {
			image = ImageLoader.loadImage(file);
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

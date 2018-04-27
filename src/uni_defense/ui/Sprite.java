package uni_defense.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

public class Sprite extends AbstractGraphicComponent {
	
	private File folder;
	private BufferedImage[] images;
	private int pictureIndex = 0;
	
	
	public Sprite(String path) {
		this(path, 0);
		
	}
	
	public Sprite(String path, int size) {
		folder = new File(AbstractGraphicComponent.BASE_FOLDER + path);
		
		if (null != folder && folder.isDirectory()) {
			File[] pictures = folder.listFiles();
			
			images = new BufferedImage[pictures.length];
			for (int i = 0; i < pictures.length; i++) {
				images[i] = GraphicUtils.loadImage(pictures[i]);
				
				if (size > 0 && (images[i].getWidth() != size || images[i].getHeight() != size)) {
					images[i] = GraphicUtils.scale(images[i], size);
				}
			}
		} else {
			System.err.println("Couldn't load picture");
		}
		
		setPreferredSize(new Dimension(size, size));
        setMaximumSize(new Dimension(size, size));
        setMinimumSize(new Dimension(size, size));
		
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
}

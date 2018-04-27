package uni_defense.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

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
		g.drawImage(images[pictureIndex++], 20, 20, null);
		
		if (pictureIndex >= images.length) {
			pictureIndex = 0;
		}
		
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

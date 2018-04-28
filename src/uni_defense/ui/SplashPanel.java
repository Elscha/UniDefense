package uni_defense.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SplashPanel extends JPanel {
	
	private BufferedImage img = StaticPictures.SPLASH_SCREEN;
	
	public SplashPanel() {
		Dimension dim = new Dimension(img.getWidth(), img.getHeight());
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setSize(dim);
	}
	
	@Override
	public void paint(Graphics g) {
    	g.drawImage(img, 0, 0, StaticPictures.SPLASH_SCREEN.getWidth(), StaticPictures.SPLASH_SCREEN.getHeight(), null);
    }

}

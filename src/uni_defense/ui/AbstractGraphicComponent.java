package uni_defense.ui;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class AbstractGraphicComponent extends JLabel {
	
	static final String BASE_FOLDER =  Enemy.class.getResource(".").getFile() + "/";

	public AbstractGraphicComponent() {
		setDoubleBuffered(true);
	}
}

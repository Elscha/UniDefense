package uni_defense.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AbstractGraphicComponent extends JLabel {
	
	static final String BASE_FOLDER =  Sprite.class.getResource(".").getFile() + "/";

	public AbstractGraphicComponent() {
		setDoubleBuffered(true);
	}
	
	public AbstractGraphicComponent(ImageIcon image) {
		super("", image, JLabel.CENTER);
	}
}

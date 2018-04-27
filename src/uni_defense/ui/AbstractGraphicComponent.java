package uni_defense.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AbstractGraphicComponent extends JLabel {
	
	

	public AbstractGraphicComponent() {
		setDoubleBuffered(true);
	}
	
	public AbstractGraphicComponent(ImageIcon image) {
		super("", image, JLabel.CENTER);
	}
}

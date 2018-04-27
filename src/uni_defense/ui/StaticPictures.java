package uni_defense.ui;

import java.awt.image.BufferedImage;
import java.io.File;

public class StaticPictures {
	
	// Grounding
	public static BufferedImage GRASS_BACKGROUND = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "ground/grass.png"));

	// Buildings
	public static BufferedImage ARCHER_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "buildings/archer.png"));
	public static BufferedImage CANON_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "buildings/canon.png"));
	public static BufferedImage CASTLE_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "buildings/castle.png"));
	
}

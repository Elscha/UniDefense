package uni_defense.ui;

import java.awt.image.BufferedImage;
import java.io.File;

public class StaticPictures {
	
	// Grounding
	public static BufferedImage GRASS_BACKGROUND = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "ground/grass.png"));
	public static BufferedImage WATER_BACKGROUND = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "ground/water.png"));
	public static BufferedImage STONE_BACKGROUND = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "ground/highground.png"));
	public static BufferedImage DIRT_BACKGROUND = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "ground/dirt.png"));

	// Buildings
	public static BufferedImage ARCHER_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "buildings/archer.png"));
	public static BufferedImage ARCHER2_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "buildings/archerlvl2.png"));
	public static BufferedImage CANON_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "buildings/canon.png"));
	public static BufferedImage CANON2_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "buildings/canonlvl2.png"));
	public static BufferedImage ICE_TOWER_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "buildings/icetower.png"));
	public static BufferedImage CASTLE_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "buildings/castle.png"));
	public static BufferedImage SPAWN_BUILDING = GraphicUtils.loadImage(new File(GraphicUtils.BASE_FOLDER  + "ground/spawn.png"));
	
}

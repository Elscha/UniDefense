package uni_defense.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import uni_defense.logic.world.GroundTile;
import uni_defense.logic.world.World;

public class WorldRenderer extends JPanel {
	
	private static final Map<GroundTile, Image> GROUND_TILE_MAPPING;
	
	static {
		// Ground tiles
		Map<GroundTile, Image> tmp = new HashMap<>();
		tmp.put(GroundTile.GRASS, StaticPictures.GRASS_BACKGROUND.getImage());
		GROUND_TILE_MAPPING = Collections.unmodifiableMap(tmp);
	}
	
	private World worldModel;
	
	public WorldRenderer(World worldModel) {
		this.worldModel = worldModel;
	}

	
	@Override
	public void paint(Graphics g) {
		// Draw Background
		
		
		for (int x = 0; x < worldModel.getWidth(); x++) {
			for (int y = 0; y < worldModel.getHeight(); y++) {
				
				GroundTile tile = worldModel.getGroundTile(x, y);
				
				int drawToX = x * 16;
				int drawToY = y * 16;
				
				g.drawImage(GROUND_TILE_MAPPING.get(tile), drawToX, drawToY, 16, 16, null);
				
			}
		}
		
		
//		g.drawImage(images[pictureIndex++], 0, 0, null);
//		
//		if (pictureIndex >= images.length) {
//			pictureIndex = 0;
//		}
		
	}
}

package uni_defense.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import uni_defense.logic.world.GroundTile;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.World;

public class WorldRenderer extends JPanel {
	
	/**
	 * Tile size in pixels.
	 */
	private static final int TILE_SIZE = 16;
	
	private static final Map<GroundTile, Image> GROUND_TILE_MAPPING;
	private Map<String, Sprite> enemies = new HashMap<>();
	
	static {
		// Ground tiles
		Map<GroundTile, Image> tmp = new HashMap<>();
		tmp.put(GroundTile.GRASS, StaticPictures.GRASS_BACKGROUND.getImage());
		GROUND_TILE_MAPPING = Collections.unmodifiableMap(tmp);
	}
	
	private World worldModel;
	
	public WorldRenderer(World worldModel) {
		this.worldModel = worldModel;
		Dimension size = new Dimension(worldModel.getWidth() * TILE_SIZE, worldModel.getHeight() * TILE_SIZE);
		this.setSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
	}

	
	@Override
	public void paint(Graphics g) {
		// Draw Background
		for (int x = 0; x < worldModel.getWidth(); x++) {
			for (int y = 0; y < worldModel.getHeight(); y++) {
				
				GroundTile tile = worldModel.getGroundTile(x, y);
				
				int drawToX = x * TILE_SIZE;
				int drawToY = y * TILE_SIZE;
				
				g.drawImage(GROUND_TILE_MAPPING.get(tile), drawToX, drawToY, TILE_SIZE, TILE_SIZE, null);
				
			}
		}
		
		
		// Draw enemies
		for (MovableObject enemy : worldModel.getObjects()) {
			int drawToX = (int) (enemy.getX() * TILE_SIZE);
			int drawToY = (int) (enemy.getY() * TILE_SIZE);
			
			
			String className = enemy.getClass().getSimpleName().toLowerCase();
			Sprite sprite = enemies.get(className);
			if (null == sprite) {
				sprite = new Sprite("sprites/enemies/" + className);
				enemies.put(className, sprite);
			}
			
			g.drawImage(sprite.getImage(), drawToX, drawToY, TILE_SIZE, TILE_SIZE, null);
		}
	}
}

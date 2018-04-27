package uni_defense.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import uni_defense.logic.enemies.Enemy;
import uni_defense.logic.world.GroundTile;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.World;
import uni_defense.ui.menus.MouseMenu;

public class WorldRenderer extends JPanel {
	
	/**
	 * Tile size in pixels.
	 */
	public static final int TILE_SIZE = 32;
	
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
		
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
		        doPop(e);
		    }

		    public void mouseReleased(MouseEvent e){
		        doPop(e);
		    }

		    private void doPop(MouseEvent e){
		    	MouseMenu menu = new MouseMenu();
		        menu.show(e.getComponent(), e.getX(), e.getY());
		        System.out.println("tile: " + pixelsToTile(e.getX()) +":"+ pixelsToTile(e.getY()));
		    }
		});
	}

	private int pixelsToTile(int pixel) {
		return pixel / TILE_SIZE;
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
			
			
			String id = enemy.getID();
			Sprite sprite = enemies.get(id);
			int size = (enemy instanceof Enemy) ? ((Enemy) enemy).getSize() : 0;
			if (null == sprite) {
				sprite = new Sprite("sprites/enemies/" + enemy.getClass().getSimpleName().toLowerCase(), size);
				enemies.put(id, sprite);
			}
			if (size == 0) {
				size = TILE_SIZE;
			}
			
			g.drawImage(sprite.getImage(), drawToX, drawToY, size, size, null);
		}
	}
}

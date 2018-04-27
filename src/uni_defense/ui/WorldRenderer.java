package uni_defense.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import uni_defense.logic.buildings.Archer;
import uni_defense.logic.buildings.Building;
import uni_defense.logic.buildings.Canon;
import uni_defense.logic.world.GroundTile;
import uni_defense.logic.world.MovableObject;
import uni_defense.logic.world.World;
import uni_defense.ui.menus.MouseMenu;

public class WorldRenderer extends JPanel {
	
    private static final long serialVersionUID = -4005332381854987871L;

    /**
	 * Tile size in pixels.
	 */
	public static final int TILE_SIZE = 32;
	
	private static final Map<GroundTile, Image> GROUND_TILE_MAPPING;
	private static final Map<Class<? extends Building>, Image> BUILDING_MAPPING;
	private Map<String, Sprite> enemies = new HashMap<>();
	private List<Class<? extends Building>> towers = new ArrayList<>();
	
	static {
		// Ground tiles
		Map<GroundTile, Image> tmpTiles = new HashMap<>();
		tmpTiles.put(GroundTile.GRASS, StaticPictures.GRASS_BACKGROUND);
		GROUND_TILE_MAPPING = Collections.unmodifiableMap(tmpTiles);
		
		// Buildings
		Map<Class<? extends Building>, Image> tmpBuildings = new HashMap<>();
		tmpBuildings.put(Archer.class, StaticPictures.ARCHER_BUILDING);
		tmpBuildings.put(Canon.class, StaticPictures.ARCHER_BUILDING);
		BUILDING_MAPPING = Collections.unmodifiableMap(tmpBuildings);
	}
	
	private World worldModel;
	
	public WorldRenderer(World worldModel) {
		this.worldModel = worldModel;
		Dimension size = new Dimension(worldModel.getWidth() * TILE_SIZE, worldModel.getHeight() * TILE_SIZE);
		this.setSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		towers.addAll(BUILDING_MAPPING.keySet());
		
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
		        doPop(e);
		    }

		    public void mouseReleased(MouseEvent e){
		        doPop(e);
		    }

		    private void doPop(MouseEvent e){
		    	int tileX = pixelsToTile(e.getX());
		    	int tileY = pixelsToTile(e.getY());
		    	MouseMenu menu = new MouseMenu(worldModel, towers, tileX, tileY);
		        menu.show(e.getComponent(), e.getX(), e.getY());
		        System.out.println("tile: " + pixelsToTile(e.getX()) +":"+ pixelsToTile(e.getY()));
		    }
		});
	}

	private int pixelsToTile(int pixel) {
		return (pixel / TILE_SIZE);
	}
	private int tileToPixels(int pixel) {
		return (pixel * TILE_SIZE);
	}
	
	@Override
	public void paint(Graphics g) {
		// Draw Background
		for (int x = 0; x < worldModel.getWidth(); x++) {
			for (int y = 0; y < worldModel.getHeight(); y++) {
				
				GroundTile tile = worldModel.getGroundTile(x, y);
				
				int drawToX = tileToPixels(x);
				int drawToY = tileToPixels(y);
				
				g.drawImage(GROUND_TILE_MAPPING.get(tile), drawToX, drawToY, TILE_SIZE, TILE_SIZE, null);
				
			}
		}
		
		// draw buildings
		for (int x = 0; x < worldModel.getWidth(); x++) {
            for (int y = 0; y < worldModel.getHeight(); y++) {
                
                Building building = worldModel.getBuilding(x, y);
                
                if (building != null) {
                    int drawToX = tileToPixels(x);
                    int drawToY = tileToPixels(y);
                    
                    g.drawImage(BUILDING_MAPPING.get(building.getClass()), drawToX, drawToY, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
		
		// Draw enemies
		for (MovableObject enemy : worldModel.getObjects()) {
			int drawToX = (int) (enemy.getX() * TILE_SIZE);
			int drawToY = (int) (enemy.getY() * TILE_SIZE);
			
			
			String id = enemy.getID();
			Sprite sprite = enemies.get(id);
			int size = enemy.getSize();
			if (null == sprite) {
				sprite = new Sprite("sprites/enemies/" + enemy.getClass().getSimpleName().toLowerCase(), size);
				enemies.put(id, sprite);
			}
			if (size == 0) {
				size = TILE_SIZE;
			}
			
			g.drawImage(sprite.getImage(), drawToX - size / 2 + TILE_SIZE / 2, drawToY - size / 2 + TILE_SIZE / 2, size, size, null);
		}
	}
}

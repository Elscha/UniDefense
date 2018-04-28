package uni_defense.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import uni_defense.logic.buildings.Archer;
import uni_defense.logic.buildings.ArcherLvl2;
import uni_defense.logic.buildings.Building;
import uni_defense.logic.buildings.BuildingModel;
import uni_defense.logic.buildings.Canon;
import uni_defense.logic.buildings.CanonLvl2;
import uni_defense.logic.buildings.IceTower;
import uni_defense.logic.buildings.bullets.IceTowerEffect;
import uni_defense.logic.enemies.Enemy;
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
	
	private static int waveNumber;
	
	private static String currentWaveName;
	
	static {
		// Ground tiles
		Map<GroundTile, Image> tmpTiles = new HashMap<>();
		tmpTiles.put(GroundTile.GRASS, StaticPictures.GRASS_BACKGROUND);
		tmpTiles.put(GroundTile.DIRT, StaticPictures.DIRT_BACKGROUND);
		tmpTiles.put(GroundTile.WATER, StaticPictures.WATER_BACKGROUND);
		tmpTiles.put(GroundTile.STONE, StaticPictures.STONE_BACKGROUND);
		GROUND_TILE_MAPPING = Collections.unmodifiableMap(tmpTiles);
		
		// Buildings
		Map<Class<? extends Building>, Image> tmpBuildings = new HashMap<>();
		tmpBuildings.put(Archer.class, StaticPictures.ARCHER_BUILDING);
		tmpBuildings.put(ArcherLvl2.class, StaticPictures.ARCHER2_BUILDING);
		tmpBuildings.put(Canon.class, StaticPictures.CANON_BUILDING);
		tmpBuildings.put(CanonLvl2.class, StaticPictures.CANON2_BUILDING);
		tmpBuildings.put(IceTower.class, StaticPictures.ICE_TOWER_BUILDING);
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
		towers.addAll(BuildingModel.BUILDING_PRICES.keySet());
		Collections.sort(towers, new Comparator<Class<? extends Building>>() {

			@Override
			public int compare(Class<? extends Building> o1, Class<? extends Building> o2) {
				return Integer.compare(BuildingModel.BUILDING_PRICES.get(o1), BuildingModel.BUILDING_PRICES.get(o2));
			}
		});
		
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
				if (e.isPopupTrigger()) {
					doPop(e);
				}
		    }

		    public void mouseReleased(MouseEvent e){
		    	if (e.isPopupTrigger()) {
					doPop(e);
				}
		    }

		    private void doPop(MouseEvent e){
		    	int tileX = pixelsToTile(e.getX());
		    	int tileY = pixelsToTile(e.getY());
		    	MouseMenu menu = new MouseMenu(worldModel, towers, tileX, tileY);
		        menu.show(e.getComponent(), e.getX(), e.getY());
//		        System.out.println("tile: " + pixelsToTile(e.getX()) +":"+ pixelsToTile(e.getY()));
		    }
		});
	}

	private int pixelsToTile(int pixel) {
		return (pixel / TILE_SIZE);
	}
	private int tileToPixels(int pixel) {
		return (pixel * TILE_SIZE);
	}
	
	public void setCurrentWaveName(String name) {
	    currentWaveName = name;
	    if (name != null) {
	        waveNumber++;
	    }
    }
	
	@Override
	public void paint(Graphics g) {
	    synchronized (worldModel) {
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
    		
    		// draw castle
    		{
    		    int x = worldModel.getCastlePos().getX();
    		    int y = worldModel.getCastlePos().getY();
    		    
    		    int drawToX = tileToPixels(x);
                int drawToY = tileToPixels(y);
    
                g.drawImage(StaticPictures.CASTLE_BUILDING, drawToX, drawToY, TILE_SIZE, TILE_SIZE, null);
                
    		}
    		
    		// draw spawn
    		{
    		    int x = worldModel.getSpawnPos().getX();
    		    int y = worldModel.getSpawnPos().getY();
    		    
    		    int drawToX = tileToPixels(x);
    		    int drawToY = tileToPixels(y);
    		    
    		    g.drawImage(StaticPictures.SPAWN_BUILDING, drawToX, drawToY, TILE_SIZE, TILE_SIZE, null);
    		    
    		}
    		
    		// Draw enemies
    		for (MovableObject obj : worldModel.getObjects()) {
    			int drawToX = (int) (obj.getX() * TILE_SIZE);
    			int drawToY = (int) (obj.getY() * TILE_SIZE);
    			
    			
    			if (obj instanceof IceTowerEffect) {
    			    // special case
    			    
    			    g.setColor(new Color(50, 50, 255, 100));
    			    g.fillOval(drawToX - TILE_SIZE * IceTower.RANGE + TILE_SIZE / 2, drawToY - TILE_SIZE * IceTower.RANGE + TILE_SIZE / 2, TILE_SIZE * IceTower.RANGE * 2, TILE_SIZE * IceTower.RANGE * 2);
    			    
    			    continue;
    			}
    			
    			String id = obj.getID();
    			Sprite sprite = enemies.get(id);
    			int size = obj.getSize();
    			if (null == sprite) {
    				sprite = new Sprite("sprites/enemies/" + obj.getClass().getSimpleName().toLowerCase(), size);
    				enemies.put(id, sprite);
    			}
    			if (size == 0) {
    				size = TILE_SIZE;
    			}
    			
    			g.drawImage(sprite.getImage(), drawToX - size / 2 + TILE_SIZE / 2, drawToY - size / 2 + TILE_SIZE / 2, size, size, null);
    			
    			if (obj instanceof Enemy) {
    			    Enemy enemy = (Enemy) obj;
    			    
    			    double hpRatio = (double) enemy.getHp() / enemy.getMaxHp();
    			    
    			    g.setColor(Color.RED);
    			    g.fillRect(drawToX - size / 2 + TILE_SIZE / 2, drawToY - size / 2 + TILE_SIZE / 2, (int) (size * hpRatio), 5);
    			    
    			    if (enemy.isFrozen()) {
                        g.setColor(new Color(0, 0, 255, 50));
                        g.fillOval(drawToX - size / 2 + TILE_SIZE / 2, drawToY - size / 2 + TILE_SIZE / 2, size, size);
    			    }
    			}
    		}
    		
    		g.setColor(Color.WHITE);
    		g.drawString((currentWaveName != null ? "Wave " + waveNumber + ": "  +currentWaveName : "Wave: (none)"), 10, 10);
    	}
	}

}

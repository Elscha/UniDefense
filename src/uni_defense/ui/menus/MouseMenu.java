package uni_defense.ui.menus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import uni_defense.logic.buildings.Building;
import uni_defense.logic.buildings.BuildingModel;
import uni_defense.logic.player.Player;
import uni_defense.logic.world.PathFinder;
import uni_defense.logic.world.Point;
import uni_defense.logic.world.World;

public class MouseMenu extends JPopupMenu {
	
	private World worldModel;
	private int tileX;
	private int tileY;
	
    public MouseMenu(World worldModel, List<Class<? extends Building>> towers, int tileX, int tileY) {
        
    	if (worldModel.isBuildable(tileX, tileY)) {
	    	for (Class<? extends Building> towerClass : towers) {
	    		int price = BuildingModel.BUILDING_PRICES.get(towerClass);
				JMenuItem btn = new JMenuItem(towerClass.getSimpleName() + " (" + price + ")");
				add(btn);
				if (price > Player.INSTANCE.getGold()) {
					btn.setEnabled(false);
				}
				
				btn.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						if (Player.INSTANCE.getGold() >= price) {
							try {
								Constructor<? extends Building> constructor = towerClass.getConstructor(World.class);
								Building newTower = constructor.newInstance(worldModel);
								worldModel.setBuildings(tileX, tileY, newTower);
								
								PathFinder finder = new PathFinder(worldModel);
								Point spawn = worldModel.getSpawnPos();
								Point destination = worldModel.getCastlePos();
								if (!finder.existPath(spawn, destination)) {
									worldModel.setBuildings(tileX, tileY, null);
								} else {
									Player.INSTANCE.updateGold(-price);
								}
							} catch (ReflectiveOperationException | SecurityException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				    }
				});
	    	}
		} else if (worldModel.getBuilding(tileX, tileY) != null) {
			JMenuItem btnUpgrade = new JMenuItem("Upgrade");
			add(btnUpgrade);
			// TODO action missing
			
			JMenuItem btnSell = new JMenuItem("Sell tower");
			add(btnSell);
			btnSell.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
				    Building building = worldModel.getBuilding(tileX, tileY);
				    if (building != null) {
				        int price = BuildingModel.BUILDING_PRICES.get(building.getClass());
				        price *= 0.8;
				        Player.INSTANCE.updateGold(price);
				        worldModel.setBuildings(tileX, tileY, null);
				    }
				}
			});
		}
    }
}
package uni_defense.ui.menus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map.Entry;

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
			
			Building b = worldModel.getBuilding(tileX, tileY);
			btnUpgrade.setEnabled(BuildingModel.UPGRADE_TARGET.containsKey(b.getClass()));
			
			add(btnUpgrade);
			btnUpgrade.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    Building building = worldModel.getBuilding(tileX, tileY);
                    if (building != null) {
                        Integer price = BuildingModel.UPGRADE_PRICES.get(building.getClass());
                        Class<? extends Building> upgrade = BuildingModel.UPGRADE_TARGET.get(building.getClass());
                        if (price != null && upgrade != null && Player.INSTANCE.getGold() >= price) {
                            try {
                                Player.INSTANCE.updateGold(-price);
                                worldModel.setBuildings(tileX, tileY, upgrade.getConstructor(World.class).newInstance(worldModel));
                            } catch (ReflectiveOperationException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (IllegalArgumentException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (SecurityException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            });
			// TODO action missing
			
			JMenuItem btnSell = new JMenuItem("Sell tower");
			add(btnSell);
			btnSell.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
				    Building building = worldModel.getBuilding(tileX, tileY);
				    if (building != null) {
				        Integer price = BuildingModel.BUILDING_PRICES.get(building.getClass());
				        if (price == null) {
				            Class<? extends Building> original = null;
				            for (Entry<Class<? extends Building>, Class<? extends Building>> entry : BuildingModel.UPGRADE_TARGET.entrySet()) {
				                if (entry.getValue() == building.getClass()) {
				                    original = entry.getKey();
				                    continue;
				                }
				            }
				            
				            if (original == null) {
				                return;
				            }
				            
				            price = BuildingModel.BUILDING_PRICES.get(original) + BuildingModel.UPGRADE_PRICES.get(original);
				        }
				        
				        price = (int) (price * 0.8);
				        Player.INSTANCE.updateGold(price);
				        worldModel.setBuildings(tileX, tileY, null);
				    }
				}
			});
		}
    }
}
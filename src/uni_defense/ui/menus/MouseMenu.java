package uni_defense.ui.menus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import uni_defense.logic.buildings.Building;
import uni_defense.logic.world.World;

public class MouseMenu extends JPopupMenu {
	
	private World worldModel;
	private int tileX;
	private int tileY;
	
    public MouseMenu(World worldModel, List<Class<? extends Building>> towers, int tileX, int tileY) {
        
    	if (worldModel.isBuildable(tileX, tileY)) {
	    	for (Class<? extends Building> towerClass : towers) {
				JMenuItem btn = new JMenuItem(towerClass.getSimpleName());
				add(btn);
				
				btn.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						try {
							Constructor<? extends Building> constructor = towerClass.getConstructor(World.class);
							Building newTower = constructor.newInstance(worldModel);
							worldModel.setBuildings(tileX, tileY, newTower);
						} catch (ReflectiveOperationException | SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }
				});
	    	}
		} else {
			JMenuItem btnUpgrade = new JMenuItem("Upgrade");
			add(btnUpgrade);
			btnUpgrade.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					worldModel.setBuildings(tileX, tileY, null);
				}
			});
			JMenuItem btnSell = new JMenuItem("Sell tower");
			add(btnSell);
			btnSell.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					worldModel.setBuildings(tileX, tileY, null);
				}
			});
		}
    }
}
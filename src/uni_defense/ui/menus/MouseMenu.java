package uni_defense.ui.menus;

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
        for (Class<? extends Building> towerClass : towers) {
			JMenuItem btn = new JMenuItem(towerClass.getSimpleName());
			add(btn);
		}
    }
}
package uni_defense.ui.menus;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import uni_defense.logic.world.World;

public class MouseMenu extends JPopupMenu {
	
	private World worldModel;
	private int tileX;
	private int tileY;
	
    JMenuItem anItem;
    public MouseMenu(World worldModel, int tileX, int tileY) {
        anItem = new JMenuItem("Build something");
        add(anItem);
    }
}
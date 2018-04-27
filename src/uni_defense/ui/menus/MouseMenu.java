package uni_defense.ui.menus;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MouseMenu extends JPopupMenu {
    JMenuItem anItem;
    public MouseMenu(){
        anItem = new JMenuItem("Build something");
        add(anItem);
    }
}
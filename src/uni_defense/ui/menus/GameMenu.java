package uni_defense.ui.menus;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import uni_defense.logic.player.Player;

public class GameMenu extends JToolBar {

	public GameMenu() {
		updateMenubar();
		this.setFloatable(false);
        this.setRollover(true);
	}

	private void updateMenubar() {
		
		JTextField jtextGold = new JTextField(("Gold : " + Player.INSTANCE.getGold()));
		jtextGold.setColumns(10);
		jtextGold.setEnabled(false);
        this.add(jtextGold);
        this.addSeparator();        

		JTextField jtextLifes = new JTextField(("Lifes : " + Player.INSTANCE.getCurrentlifes() + "/" + Player.INSTANCE.getMaxlifes()));
		jtextLifes.setColumns(10);
		jtextLifes.setEnabled(false);
        this.add(jtextLifes);
        this.addSeparator();        
        
		JTextField jtextWave = new JTextField(("Wave : " + Player.INSTANCE.getCurrentwave() + "/" + Player.INSTANCE.getEndwave()));
		jtextWave.setColumns(10);
		jtextWave.setEnabled(false);
        this.add(jtextWave);
        this.addSeparator();        
        
		JTextField jtextKills = new JTextField(("Total killcount : " + Player.INSTANCE.getEnemiesKilled()));
		jtextKills.setColumns(12);
		jtextKills.setEnabled(false);
        this.add(jtextKills);
        this.addSeparator();        

		JTextField jtextEnemies = new JTextField(("Enemies alive : " + Player.INSTANCE.getEnemiesAlive()));
		jtextEnemies.setColumns(10);
		jtextEnemies.setEnabled(false);
        this.add(jtextEnemies);
        this.addSeparator();        
          
	}
	
	public static void main(String[] args) {
		GameMenu menu = new GameMenu();
		JFrame frame = new JFrame("test");
		frame.add(menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
}
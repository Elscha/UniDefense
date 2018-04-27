package uni_defense.ui.menus;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class GameMenu extends JToolBar {

	private int gold;
	private int currentlifes;
	private int maxlifes;
	private int currentwave;
	private int endwave;
	private int enemiesKilled;
	private int enemiesAlive;
	
	public GameMenu() {
		updateMenubar();
		this.setFloatable(false);
        this.setRollover(true);
	}

	private void updateMenubar() {
		
		JTextField jtextGold = new JTextField(("Gold : " + gold));
		jtextGold.setColumns(10);
		jtextGold.setEnabled(false);
        this.add(jtextGold);
        this.addSeparator();        

		JTextField jtextLifes = new JTextField(("Lifes : " + currentlifes + "/" + maxlifes));
		jtextLifes.setColumns(10);
		jtextLifes.setEnabled(false);
        this.add(jtextLifes);
        this.addSeparator();        
        
		JTextField jtextWave = new JTextField(("Wave : " + currentwave + "/" + endwave));
		jtextWave.setColumns(10);
		jtextWave.setEnabled(false);
        this.add(jtextWave);
        this.addSeparator();        
        
		JTextField jtextKills = new JTextField(("Total killcount : " + enemiesKilled));
		jtextKills.setColumns(12);
		jtextKills.setEnabled(false);
        this.add(jtextKills);
        this.addSeparator();        

		JTextField jtextEnemies = new JTextField(("Enemies alive : " + enemiesAlive));
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
	
	public void updateGold(int goldChange) {
		gold = gold + goldChange;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void updateCurrentlifes(int lifesChange) {
		currentlifes = currentlifes + lifesChange;
	}
	
	public void setCurrentlifes(int currentlifes) {
		this.currentlifes = currentlifes;
	}
	
	public void setMaxlifes(int maxlifes) {
		this.maxlifes = maxlifes;
	}
	
	public void increaseWaveByOne() {
		currentwave++;
	}
	
	public void setCurrentwave(int currentwave) {
		this.currentwave = currentwave;
	}
	
	public void setEndwave(int endwave) {
		this.endwave = endwave;
	}
	
	public void increaseEnemiesKilledByOne() {
		enemiesKilled++;
	}
	
	public void setEnemiesKilled(int enemiesKilled) {
		this.enemiesKilled = enemiesKilled;
	}
	
	public void decreaseEnemiesAliveByOne() {
		enemiesAlive--;
	}
	
	public void setEnemiesAlive(int enemiesAlive) {
		this.enemiesAlive = enemiesAlive;
	}
}

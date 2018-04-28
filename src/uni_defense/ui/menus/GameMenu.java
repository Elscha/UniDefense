package uni_defense.ui.menus;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import uni_defense.logic.player.Player;

public class GameMenu extends JToolBar implements Runnable {

	private JTextField jtextGold;
	private JTextField jtextLifes;
	private JTextField jtextWave;
	private JTextField jtextKills;
	private JTextField jtextEnemies;
	
	public GameMenu() {
		this.setFloatable(false);
        this.setRollover(true);
        
        jtextGold = new JTextField("");
        jtextGold.setColumns(10);
        jtextGold.setEnabled(false);
        this.add(jtextGold);
        this.addSeparator();        
        
        jtextLifes = new JTextField("");
        jtextLifes.setColumns(10);
        jtextLifes.setEnabled(false);
        this.add(jtextLifes);
        this.addSeparator();        
        
        jtextWave = new JTextField("");
        jtextWave.setColumns(10);
        jtextWave.setEnabled(false);
        this.add(jtextWave);
        this.addSeparator();        
        
        jtextKills = new JTextField("");
        jtextKills.setColumns(12);
        jtextKills.setEnabled(false);
        this.add(jtextKills);
        this.addSeparator();        
        
        jtextEnemies = new JTextField("");
        jtextEnemies.setColumns(10);
        jtextEnemies.setEnabled(false);
        this.add(jtextEnemies);
        this.addSeparator();        
        
        new Thread(this).start();
	}

	private void updateMenubar() {
		jtextGold.setText(("Gold : " + Player.INSTANCE.getGold()));
		jtextLifes.setText(("Lifes : " + Player.INSTANCE.getCurrentlifes() + "/" + Player.INSTANCE.getMaxlifes()));
		jtextWave.setText(("Wave : " + Player.INSTANCE.getCurrentwave() + "/" + Player.INSTANCE.getEndwave())); 
		jtextKills.setText(("Total killcount : " + Player.INSTANCE.getEnemiesKilled())); 
		jtextEnemies.setText(("Enemies alive : " + Player.INSTANCE.getEnemiesAlive())); 
	}
	
	public static void main(String[] args) {
		GameMenu menu = new GameMenu();
		JFrame frame = new JFrame("test");
		frame.add(menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}

	@Override
	public void run() {
		while(true) {
			updateMenubar();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
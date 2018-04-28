package uni_defense.ui.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import uni_defense.logic.enemies.wave.AbstractWave;
import uni_defense.logic.enemies.wave.FlyWave;
import uni_defense.logic.enemies.wave.Wave2;
import uni_defense.logic.enemies.wave.WorkerBossWave;
import uni_defense.logic.enemies.wave.WorkerWave;
import uni_defense.logic.player.Player;
import uni_defense.ui.MainWindow;

public class GameMenu extends JToolBar implements Runnable, ActionListener {

	private JTextField jtextGold;
	private JTextField jtextLifes;
	private JTextField jtextWave;
	private JTextField jtextKills;
	private JTextField jtextEnemies;
	
	private MainWindow window;
	
	public GameMenu(MainWindow window) {
	    this.window = window;
	    
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
        
        if (MainWindow.NETWORK) {
            JPanel waveButtons = new JPanel();
            waveButtons.setLayout(new BoxLayout(waveButtons, BoxLayout.Y_AXIS));
            JButton btn0 = new JButton("Worker Wave (20)");
            btn0.setActionCommand("WorkerWave");
            btn0.addActionListener(this);
            waveButtons.add(btn0);
            JButton btn1 = new JButton("Worker Boss Wave (40)");
            btn1.setActionCommand("BossWave");
            btn1.addActionListener(this);
            waveButtons.add(btn1);
            JButton btn2 = new JButton("Horrible Shrew (60)");
            btn2.setActionCommand("Shrew");
            btn2.addActionListener(this);
            waveButtons.add(btn2);
            JButton btn3 = new JButton("Flies (40)");
            btn3.setActionCommand("Fly");
            btn3.addActionListener(this);
            waveButtons.add(btn3);
            this.add(waveButtons);
        }
        
        new Thread(this).start();
	}

	private void updateMenubar() {
		jtextGold.setText(("Gold : " + Player.INSTANCE.getGold()));
		jtextLifes.setText(("Lifes : " + Player.INSTANCE.getCurrentlifes() + "/" + Player.INSTANCE.getMaxlifes()));
		jtextWave.setText(("Wave : " + Player.INSTANCE.getCurrentwave())); 
		jtextKills.setText(("Total killcount : " + Player.INSTANCE.getEnemiesKilled())); 
		jtextEnemies.setText(("Enemies alive : " + Player.INSTANCE.getEnemiesAlive())); 
	}
	
	public static void main(String[] args) {
		GameMenu menu = new GameMenu(null);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Class<? extends AbstractWave> wave = null;
        int cost = 0;
        switch (e.getActionCommand()) {
        case "WorkerWave":
            wave = WorkerWave.class;
            cost = 20;
            break;
        case "BossWave":
            wave = WorkerBossWave.class;
            cost = 40;
            break;
        case "Shrew":
            wave = Wave2.class;
            cost = 60;
            break;
        case "Fly":
            wave = FlyWave.class;
            cost = 40;
            break;
        default:
           return;
        }
        
        if (Player.INSTANCE.getGold() >= cost) {
            Player.INSTANCE.updateGold(-cost);
            window.sendWave(wave);
        }
    }
}
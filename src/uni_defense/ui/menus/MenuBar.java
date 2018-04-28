package uni_defense.ui.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import uni_defense.logic.world.World;
import uni_defense.ui.MainWindow;

public class MenuBar extends JMenuBar {
	
	private World world;
	private MainWindow window;
	
	private double oldSpeed = 0;
	
	public MenuBar(MainWindow window, World world) {
		this.world = world;
		this.window = window;
		
		if (!MainWindow.NETWORK) {
			createSpeedMenu(window);
		}
	}

	private void createSpeedMenu(MainWindow window) {
		JMenu speedMenu = new JMenu("Game Speed");
		speedMenu.setMnemonic(KeyEvent.VK_S);
		add(speedMenu);
		
		// Pause
		JCheckBoxMenuItem cbPause = new JCheckBoxMenuItem("Pause the game");
		cbPause.setMnemonic(KeyEvent.VK_P);
		speedMenu.add(cbPause);
		cbPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (window.getSpeed() != 0) {
					oldSpeed = window.getSpeed();
					window.setSpeed(0);
				} else {
					window.setSpeed(oldSpeed);
					oldSpeed = 0;
				}
			}
		});
		
		// double speed
		JMenuItem doubleSpeed = new JMenuItem("x2", KeyEvent.VK_PLUS);
		speedMenu.add(doubleSpeed);
		doubleSpeed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setSpeed(window.getSpeed() * 2);
			}
		});
		
		// double speed
		JMenuItem halfSpeed = new JMenuItem("1/2", KeyEvent.VK_MINUS);
		speedMenu.add(halfSpeed);
		halfSpeed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setSpeed(window.getSpeed() / 2);
			}
		});
	}

}

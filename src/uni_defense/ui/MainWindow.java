package uni_defense.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import uni_defense.logic.world.World;
import uni_defense.ui.menus.GameMenu;

public class MainWindow extends JFrame implements Runnable {
	
	private World world = new World();
	
	public MainWindow() {
		super("UniDefense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        WorldRenderer renderer = new WorldRenderer(world);
        JScrollPane worldPane = new JScrollPane(renderer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        GameMenu menu = new GameMenu();
        Dimension menuSize = new Dimension(renderer.getWidth(), 100);
        menu.setMinimumSize(menuSize);
        menu.setMaximumSize(menuSize);
        menu.setPreferredSize(menuSize);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, worldPane, new GameMenu());
        setSize(renderer.getWidth(), renderer.getHeight() + 100);
        splitPane.setDividerLocation(renderer.getHeight());
        splitPane.setMinimumSize(renderer.getMinimumSize());
        splitPane.setMaximumSize(renderer.getMaximumSize());
        splitPane.setPreferredSize(renderer.getPreferredSize());
        
//        add(new WorldRenderer(world));
//        add(new GameMenu());
        add(splitPane);
        
        setVisible(true);
        Thread th = new Thread(this);
        th.start();
	}

	public static void main(String[] args) {
		new MainWindow();
	}

	@Override
	public void run() {
		final int stepSize = 200;
		while(true) {
			world.step(stepSize);
			repaint();
			
			try {
				Thread.sleep(stepSize);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

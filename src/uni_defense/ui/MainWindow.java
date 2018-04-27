package uni_defense.ui;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import uni_defense.logic.world.World;
import uni_defense.ui.menus.GameMenu;

public class MainWindow extends JFrame implements Runnable {
	
	private World world = new World();
	
	public MainWindow() {
		super("UniDefense");
        setSize(800, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new WorldRenderer(world), new GameMenu());
        splitPane.setDividerLocation(150);
        
//        add(new WorldRenderer(world));
//        add(new GameMenu());
        add(splitPane);
        
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

package uni_defense.ui;

import javax.swing.JFrame;

import uni_defense.logic.world.World;

public class MainWindow extends JFrame implements Runnable {
	
	private World world = new World();
	
	public MainWindow() {
		super("UniDefense");
        setSize(800, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        add(new WorldRenderer(world));
        
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

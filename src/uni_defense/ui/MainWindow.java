package uni_defense.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import uni_defense.audio.Music;
import uni_defense.logic.world.World;
import uni_defense.ui.menus.GameMenu;

public class MainWindow extends JFrame implements Runnable {
	
    private static final long serialVersionUID = -5181257872336051731L;
    
    private World world = new World();
    
    private WorldRenderer renderer;
	
	public MainWindow() {
		super("UniDefense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        renderer = new WorldRenderer(world);
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
        
        new Music("bgm/1.wav").musicStart();
        
        Thread th = new Thread(this);
        th.start();
	}

	public static void main(String[] args) {
		new MainWindow();
	}

	@Override
	public void run() {
	    final int wantedFps = 60;
	    
	    final double wantedDtime = 1000.0 / wantedFps;
	    
		long lastStep = System.nanoTime();
		double tLast = System.nanoTime() / 1000000.0;
		
		while(true) {
		    
		    long currentStep = System.nanoTime();
		    double dtime = (currentStep - lastStep ) / 1000000.0;
			world.step(dtime);
			lastStep = currentStep;
			
			renderer.setDtime(dtime);
			repaint();
			
		    try {
		        Thread.sleep((long) Math.max(0, wantedDtime - ((System.nanoTime() / 1000000.0) - tLast)));
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    tLast = System.nanoTime() / 1000000.0;
		}
		
	}

}

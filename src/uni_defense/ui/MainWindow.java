package uni_defense.ui;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import uni_defense.audio.Music;
import uni_defense.logic.enemies.Worker;
import uni_defense.logic.player.Player;
import uni_defense.logic.world.World;
import uni_defense.logic.world.loading.WorldManager;
import uni_defense.ui.menus.GameMenu;

public class MainWindow extends JFrame implements Runnable {

    private static final long serialVersionUID = -5181257872336051731L;

    private WorldRenderer renderer;

	private World world;
	
	public MainWindow() throws IOException {
		super("UniDefense");

		world = WorldManager.loadMap(new File(getClass().getClassLoader().getResource("sampleMap_01.csv").getFile()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        renderer = new WorldRenderer(world);
        Player.INSTANCE.setGold(100);
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
        
        new Music("bgm/1.wav").musicStart();
        
        Thread th = new Thread(this);
        th.start();
	}

	public static void main(String[] args) throws IOException {

		new MainWindow();
	}

	@Override
	public void run() {
	    final int wantedFps = 60;
	    
	    final double wantedDtime = 1000.0 / wantedFps;
	    
		long lastStep = System.nanoTime();
		double tLast = System.nanoTime() / 1000000.0;
		
		double timer = 0.0;
		
		while(true) {
		    
		    long currentStep = System.nanoTime();
		    double dtime = (currentStep - lastStep ) / 1000000.0;
			world.step(dtime);
			lastStep = currentStep;

			timer += dtime;
			if (timer > 5000) {
			    if (Math.random() < 0.1) {
			        world.addObject(new Worker(world, true));
			    } else {
			        world.addObject(new Worker(world));
			    }
			    timer = 0.0;
			}
			
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

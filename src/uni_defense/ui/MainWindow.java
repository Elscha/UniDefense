package uni_defense.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import uni_defense.audio.Music;
import uni_defense.logic.enemies.wave.AbstractWave;
import uni_defense.logic.enemies.wave.Wave2;
import uni_defense.logic.enemies.wave.WorkerBossWave;
import uni_defense.logic.enemies.wave.WorkerWave;
import uni_defense.logic.player.Player;
import uni_defense.logic.world.World;
import uni_defense.logic.world.loading.WorldManager;
import uni_defense.networking.Client;
import uni_defense.networking.IWaveListener;
import uni_defense.networking.Server;
import uni_defense.networking.Side;
import uni_defense.ui.menus.GameMenu;

public class MainWindow extends JFrame implements Runnable, IWaveListener {

    public static final boolean NETWORK = true;
    
    private static final long serialVersionUID = -5181257872336051731L;

    private WorldRenderer renderer;

	private World world;
	
	private double speed = 1.0;
	private JPanel glass;
	
	private Deque<AbstractWave> wavesToDo = new LinkedList<>();
	
	private Side comm;
	
	public MainWindow() throws IOException {
		super("UniDefense");
		
		
		world = WorldManager.loadMap(new File(getClass().getClassLoader().getResource("sampleMap_01.csv").getFile()));
		
		if (NETWORK) {
		    int result = JOptionPane.showOptionDialog(null, "Server or Client?", "Networking Type", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Server", "Client" }, "Server");
		    
		    if (result == 0) {
		        comm = new Server(1224, world, this);
		        
		    } else if (result == 1) {
		        String address = JOptionPane.showInputDialog("Address");
		        comm = new Client(address, 1224, world, this);
		        
		        
		    } else {
		        return;
		    }
		    
		} else {
	        wavesToDo.add(new WorkerWave(world));
	        wavesToDo.add(new WorkerWave(world));
	        wavesToDo.add(new WorkerBossWave(world));
	        wavesToDo.add(new WorkerWave(world));
	        wavesToDo.add(new WorkerBossWave(world));
	        wavesToDo.add(new WorkerBossWave(world));
	        wavesToDo.add(new WorkerBossWave(world));
	        wavesToDo.add(new WorkerWave(world));
	        wavesToDo.add(new WorkerWave(world));
	        wavesToDo.add(new Wave2(world));
		}

		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        glass = (JPanel) this.getGlassPane();
        
        renderer = new WorldRenderer(world);
        Player.INSTANCE.setGold(100);
        WorldRenderer renderer = new WorldRenderer(world);
        JScrollPane worldPane = new JScrollPane(renderer, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Dimension dim = new Dimension(renderer.getMinimumSize().width + 21, renderer.getMinimumSize().height + 20);
        worldPane.setMinimumSize(renderer.getSize());
        worldPane.setMaximumSize(renderer.getSize());
        worldPane.setPreferredSize(renderer.getSize());
        GameMenu menu = new GameMenu(this);
        Dimension menuSize = new Dimension(dim.width, 100);
        menu.setMinimumSize(menuSize);
        menu.setMaximumSize(menuSize);
        menu.setPreferredSize(menuSize);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, worldPane, new GameMenu(this));
        setSize((int)dim.getWidth(), (int) dim.getHeight() + 120);
        splitPane.setDividerLocation(renderer.getHeight());
        splitPane.setMinimumSize(dim);
        splitPane.setMaximumSize(dim);
        splitPane.setPreferredSize(dim);

        add(splitPane);
        
        setVisible(true);
        
        new Music("bgm/1.wav").musicStart();
        
        Thread th = new Thread(this);
        th.start();
	}

	public void setSpeed(double speed) {
        this.speed = speed;
    }
	
	@Override
	public void addWave(AbstractWave wave) {
	    wavesToDo.addLast(wave);
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
		
		AbstractWave currentWave = null;
		
		double goldTimer = 0;
		
		while(Player.INSTANCE.getCurrentlifes() > 0) {
		    
		    long currentStep = System.nanoTime();
		    double dtime = (currentStep - lastStep ) / 1000000.0;
		    dtime *= speed;
		    
		    goldTimer += dtime;
		    if (goldTimer >= 10000) {
		        goldTimer = 0;
		        Player.INSTANCE.updateGold(10);
		    }
		    
		    synchronized (world) {
		        world.step(dtime);
            }
			lastStep = currentStep;

			if (currentWave == null || currentWave.done()) {
			    currentWave = wavesToDo.poll();
			    renderer.setCurrentWaveName(currentWave != null ? currentWave.getName() : null);
			}
			
			if (currentWave != null) {
			    currentWave.step(dtime);
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
		JLabel lblGameOver = new JLabel("Game Over");
		Font oldFont = lblGameOver.getFont();
		lblGameOver.setFont(new Font(oldFont.getFontName(), Font.BOLD, 60));
		glass.add(lblGameOver);
		glass.setVisible(true);
		glass.revalidate();
		glass.repaint();
	}
	
	public void sendWave(Class<? extends AbstractWave> wave) {
	    if (NETWORK) {
	        comm.sendWave(wave);
	    }
	}

}

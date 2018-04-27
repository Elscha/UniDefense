package test.elscha;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import uni_defense.ui.AbstractGraphicComponent;
import uni_defense.ui.BackgroundTile;
import uni_defense.ui.Enemy;

public class Display implements Runnable {
	
		private List<AbstractGraphicComponent> components = new ArrayList<>();
	
	    private JFrame jframe;
	    private static AbstractGraphicComponent canvas;
	    private String title;
	    private int width, height;

	    public Display(String tuade, int rong, int dai) {
	        this.title = tuade;
	        this.width = dai;
	        this.height = rong;
	        initCanvas();
	    }

	    private void add(AbstractGraphicComponent comp) {
	    	jframe.add(comp);
	    	components.add(comp);
	    }
	    
	    private void initCanvas() {

	        jframe = new JFrame(title);
	        jframe.setSize(width, height);
	        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        jframe.setResizable(true);
	        jframe.setVisible(true);
	        jframe.setLocationRelativeTo(null);
	        int rows = 1;
	        int columns = 1;
	        //jframe.setLayout(new GridLayout(64, 64));
	        
	        BackgroundTile background = new BackgroundTile("/ground/grass.png");
	        
	        for (int row = 0; row < rows; row++) {
	        	for (int column = 0; column < columns; column++) {
	        		add(background);
	        	}
				
			}

	        canvas = new Enemy("sprites/enemies/worker");
	        canvas.setPreferredSize(new Dimension(width, height));
	        canvas.setMaximumSize(new Dimension(width, height));
	        canvas.setMinimumSize(new Dimension(width, height));

	        add(canvas);
//	        jframe.add(canvas);
	        jframe.pack();

	        Thread th = new Thread(this);
	        th.start();
	    }

	
	public static void main(String[] args) {
		new Display("Test", 640, 480);
	}

	@Override
	public void run() {
		while(true) {
			for (AbstractGraphicComponent abstractGraphicComponent : components) {
				abstractGraphicComponent.update(abstractGraphicComponent.getGraphics());
				
			}
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

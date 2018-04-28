package uni_defense.ui;

import java.io.IOException;

import javax.swing.JFrame;

public class SplashFrame extends JFrame {
    
    public SplashFrame() {
        super("UniDefense loads...");
        SplashPanel splash = new SplashPanel();
        setSize(splash.getWidth(), splash.getHeight());
        add(splash);
        
        setVisible(true);
        setLocationRelativeTo(null);
        toFront();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setUndecorated(true);
        
        run();
    }

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dispose();
		try {
			new MainWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

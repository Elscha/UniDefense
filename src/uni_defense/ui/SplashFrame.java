package uni_defense.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uni_defense.audio.Music;

public class SplashFrame extends JFrame {
	private Music splashSong = new Music("bgm/splashSong.wav");
    
    public SplashFrame() {
        super("UniDefense loads...");
        splashSong.musicStart();
        setLayout(new BorderLayout());
        SplashPanel splash = new SplashPanel();
        setSize(splash.getWidth(), splash.getHeight() + 100);
        add(splash, BorderLayout.CENTER);
        
        JPanel pnlSouthButtons = new JPanel();
        pnlSouthButtons.setBorder(BorderFactory.createTitledBorder("Choose Game Mode"));
        pnlSouthButtons.setToolTipText("Choose your side");
        add(pnlSouthButtons, BorderLayout.SOUTH);
        JButton btnSinglePlayer = new JButton("Single Player");
        btnSinglePlayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				splashSong.musicStop();
				SplashFrame.this.dispose();
				try {
					new MainWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        pnlSouthButtons.add(btnSinglePlayer);
        JButton btnMultiplayer = new JButton("Multi-Player");
        pnlSouthButtons.add(btnMultiplayer);
        btnMultiplayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				splashSong.musicStop();
				SplashFrame.this.dispose();
				try {
					MainWindow.NETWORK = true;
					new MainWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        
        setVisible(true);
        setLocationRelativeTo(null);
        toFront();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setUndecorated(true);
        
        //run();
    }

//	public void run() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		this.dispose();
//		try {
//			new MainWindow();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}

package uni_defense.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class GameOverPane extends JComponent implements ItemListener {
	Point point;
	 
    //React to change button clicks.
    public void itemStateChanged(ItemEvent e) {
        setVisible(e.getStateChange() == ItemEvent.SELECTED);
    }
 
    protected void paintComponent(Graphics g) {
        if (point != null) {
            g.setColor(Color.red);
            g.fillOval(point.x - 10, point.y - 10, 20, 20);
        }
    }
 
    public void setPoint(Point p) {
        point = p;
    }
 
    public GameOverPane() {
    	JLabel gameOver = new JLabel("Game Over");
    	gameOver.setFont(new Font("Comic Sans", Font.ITALIC, 20));
    	gameOver.setForeground(Color.RED);
    	add(gameOver);
    }
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Pellet extends JComponent
{
	//field
	private Rectangle2D.Double ball;
	
	//constructor
	public Pellet(int x, int y)
	{
		ball = new Rectangle2D.Double(13,13,4,4);
		setLocation(x,y);
		setSize(25,25);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.PINK);
		g2.fill(ball);
	}
}
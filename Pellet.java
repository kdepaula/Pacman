import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Pellet extends JComponent
{
	//field
	private Ellipse2D.Double ball;
	
	//constructor
	public Pellet(int x, int y)
	{
		ball = new Ellipse2D.Double(12,12,6,6);
		setLocation(x,y);
		setSize(25,25);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(ball);
	}
}
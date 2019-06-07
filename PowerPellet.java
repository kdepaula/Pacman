import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class PowerPellet extends JComponent
{
	//field
	private Ellipse2D.Double ball;

	//constructor
	public PowerPellet(int x, int y)
	{
		ball = new Ellipse2D.Double(8,8,14,14);
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
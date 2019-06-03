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
		ball = new Ellipse2D.Double(0,0,10,10);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(ball);
	}
}
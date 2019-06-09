import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Wall extends JComponent
{
	private Rectangle2D.Double wall;
	private int thickness = 30;
	private int x;
	private int y;
	
	public Wall(int a, int b)
	{
		x = a;
		y = b;
		wall = new Rectangle2D.Double(0,0,thickness,thickness);
		setLocation(x,y);
		setSize(thickness,thickness);
	}
	
	public int getXValue()
	{
		return x;
	}
	
	public int getYValue()
	{
		return y;
	}
	
	public void  paintComponent (Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.fill(wall);
	}
	
	public int getThickness()
	{
		return thickness;
	}
	
}

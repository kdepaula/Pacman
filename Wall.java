import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Wall extends JComponent
{
	private Rectangle wall;
	
	public Wall(int x, int y)
	{
		wall = new Rectangle(0,0,30,30);
		setLocation(x,y);
		setSize(30,30);
	}
	
	public void  paintComponent (Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(wall);
	}
	
}

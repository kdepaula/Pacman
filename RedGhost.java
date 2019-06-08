
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class RedGhost extends JComponent
{

		private int dx;
		private int dy;
		private int height = 20;
		private int width = 10;
		
		public RedGhost()
		{
			ImageIcon imageIcon = new ImageIcon("images/red_ghost.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, width, height);
			add(imageLabel);
		}
		
		public void update()
		{
			setLocation(getX() + dx, getY() + dy);
		}
		
		public void setDx(int a)
		{
			dx = a;
		}
		
		public void setDy(int a)
		{
			dy = a;
		}

		public int getDx() 
		{
			return dx;
		}
		
		public int getDy()
		{
			return dy;
		}
}

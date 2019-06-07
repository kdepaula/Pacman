	import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class PacMan extends JComponent
{
		private int dx;
		private int dy;
		private int diameter = 20;
		
		public PacMan()
		{
			ImageIcon imageIcon = new ImageIcon("images/pacman open.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(diameter, diameter,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, diameter, diameter);
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
		
		public boolean canGoUp(int[][] map)
		{
			if(map[(getY()/20)-1][getX()/20] == 1) {return false;}
			return true;
		}
		public boolean canGoDown(int[][] map)
		{
			if(map[(getY()/20)+1][getX()/20] == 1) {return false;}
			return true;
		}
		public boolean canGoLeft(int[][] map)
		{
			if(map[(getY()/20)][(getX()/20)-1] == 1) {return false;}
			return true;
		}
		public boolean canGoRight(int[][] map)
		{
			if(map[(getY()/20)-1][(getX()/20)+1] == 1) {return false;}
			return true;
		}
		
		public int getDiameter()
		{
			return diameter;
		}
		
		
}

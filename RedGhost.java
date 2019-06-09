
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class RedGhost extends JComponent
{

		private int dx;
		private int dy;
		private int diameter = 30;
		
		public RedGhost()
		{
			ImageIcon imageIcon = new ImageIcon("images/Pink Ghost Left.png");
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
			if(map[((getY()-2)/30)][getX()/30] == 1 || map[((getY()-2)/30)][(getX()-2)/30 + 1] == 1) {setDy(0); return false;}
			return true;
		}
		public boolean canGoDown(int[][] map)
		{
			if(map[((getY()+2)/30)+1][getX()/30] == 1 || map[((getY()+2)/30)+1][(getX()-2)/30 + 1] == 1) {setDy(0); return false;}
			return true;
		}
		public boolean canGoLeft(int[][] map)
		{
			if(map[(getY()/30)][((getX()-2)/30)] == 1 || map[(getY()-2)/30 + 1][((getX()-2)/30)] == 1) {setDx(0); return false;}
			return true;
		}
		public boolean canGoRight(int[][] map)
		{
			if(map[(getY()/30)][((getX()+2)/30)+1] == 1 || map[(getY()-2)/30 + 1][((getX()+2)/30) + 1] == 1) {setDx(0); return false;}
			return true;
		}
		
		public int getDiameter()
		{
			return diameter;
		}
		
		public void setLeft()
		{
			removeAll();
			ImageIcon imageIcon = new ImageIcon("images/Red Ghost Left.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(getDiameter(), getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, getDiameter(), getDiameter());
			add(imageLabel);
		}
		
		public void setRight()
		{
			removeAll();
			ImageIcon imageIcon = new ImageIcon("images/Red Ghost Right.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(getDiameter(), getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, getDiameter(), getDiameter());
			add(imageLabel);
		}
}

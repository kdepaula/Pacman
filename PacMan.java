import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class PacMan extends JComponent
{
		private int dx;
		private int dy;
		private int diameter = 20;
		private Rectangle2D.Double rect;
		
		/**
		 * Creates the ImageIcon of Pacman, adds it to a JLabel
		 * and then adds the JLabel to the JComponent
		 */
		public PacMan()
		{
			ImageIcon imageIcon = new ImageIcon("images/pacman closed.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(diameter, diameter,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, diameter, diameter);
			add(imageLabel);
			rect = new Rectangle2D.Double(0, 0, diameter, diameter);
		}
		
		public void  paintComponent (Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.PINK);
			g2.draw(rect);
		}
		
		/**
		 * Updates the location of pacman based on its current location and dy and dx
		 */
		public void update()
		{
			setLocation(getX() + dx, getY() + dy);
		}
		
		/**
		 * the horizontal speed of pacman
		 * @param a The value you would like dx to be changed to
		 */
		public void setDx(int a)
		{
			dx = a;
		}
		
		/**
		 * the vertical speed of pacman
		 * @param a the value you would like dy to be changed to
		 */
		public void setDy(int a)
		{
			dy = a;
		}
		
		/**
		 * returns the value of dx
		 * @return the value of dx
		 */
		public int getDx() 
		{
			return dx;
		}
		
		/**
		 * returns the value of dy
		 * @return the value of dy
		 */
		public int getDy()
		{
			return dy;
		}
		
		public boolean canGoUp(int[][] map)
		{
			if(map[(getY()/30)-1][getX()/30] == 1) {return false;}
			return true;
		}
		public boolean canGoDown(int[][] map)
		{
			if(map[(getY()/30)+1][getX()/30] == 1) {return false;}
			return true;
		}
		public boolean canGoLeft(int[][] map)
		{
			if(map[(getY()/30)][(getX()/30)-1] == 1) {return false;}
			return true;
		}
		public boolean canGoRight(int[][] map)
		{
			if(map[(getY()/30)-1][(getX()/30)+1] == 1) {return false;}
			return true;
		}
		
		/**
		 * returns the diameter of the pacman image
		 * @return the diameter of the pacman image
		 */
		public int getDiameter()
		{
			return diameter;
		}
		
		/**
		 * Returns the rectangle around pacman
		 * @return the invisible rectangle surrounding pacman
		 */
		public Rectangle2D.Double getRect()
		{
			return rect;
		}
		
		
}


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class BlueGhost extends JComponent
{

		private int dx;
		private int dy;
		private int diameter = 30;
		private char lastDirectionTurned = 'd';
		private int relativeQuadrant = 1;
		
		public BlueGhost()
		{
			ImageIcon imageIcon = new ImageIcon("images/Blue Ghost Left Alt.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(diameter, diameter,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, diameter, diameter);
			add(imageLabel);
		}
		
		
		public void setDx(int a)
		{
			dx = a;
		}
		
		public void setDy(int a)
		{
			dy = a;
		}

		public void setRelativeQuadrant(int a)
		{
			relativeQuadrant = a;
		}
		
		public int getRelativeQuadrant()
		{
			return relativeQuadrant;
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
			if(getX() < 3 && getDx() <0)
			{
				setLocation(540, getY());
			}
			else if(map[(getY()/30)][((getX()-2)/30)] == 1 || map[(getY()-2)/30 + 1][((getX()-2)/30)] == 1) {setDx(0); return false;}
			return true;
		}
		public boolean canGoRight(int[][] map)
		{
			if(getX() > 537 && getDx() >0)
			{
				setLocation(3, getY());
			}
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
			ImageIcon imageIcon = new ImageIcon("images/Blue Ghost Left.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(getDiameter(), getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, getDiameter(), getDiameter());
			add(imageLabel);
		}
		
		public void setLeftAlt()
		{
			removeAll();
			ImageIcon imageIcon = new ImageIcon("images/Blue Ghost Left Alt.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(getDiameter(), getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, getDiameter(), getDiameter());
			add(imageLabel);
		}
		
		public void setRightAlt()
		{
			removeAll();
			ImageIcon imageIcon = new ImageIcon("images/Blue Ghost Right Alt.png");
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
			ImageIcon imageIcon = new ImageIcon("images/Blue Ghost Right.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(getDiameter(), getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, getDiameter(), getDiameter());
			add(imageLabel);
		}
		
		public double findAngle(JComponent man)
		{
			int angle;
			int opposite = man.getY() - this.getY();
			int adjacent = man.getX() - this.getX();
			if(adjacent !=0)
			{
				angle = (int) (Math.toDegrees(Math.abs(Math.atan(opposite/adjacent))));
				if(this.getX() > man.getX() && this.getY() < man.getY())
				{
					angle = 180 + angle;
				}
				
				if(this.getX() > man.getX() && this.getY() > man.getY())
				{
					angle = 180 - angle;
				}
			
				if(this.getX() < man.getX() && this.getY() < man.getY())
				{
					angle = 360-angle;
				}
			}
			else
			{
				angle = 90;
			}
			return angle;
		}
		
		public void lastDirectionTurned()
		{
			if(getDy() > 0 && getDx() == 0)
			{
				lastDirectionTurned = 'd';
			}
			if(getDy() < 0 && getDx() == 0)
			{
				lastDirectionTurned = 'u';
			}			
			if(getDx() > 0 && getDy() == 0)
			{
				lastDirectionTurned = 'r';
			}			
			if(getDx() < 0 && getDy() == 0)
			{
				lastDirectionTurned = 'l';
			}
		}
		
		public char getLastDirectionTurned()
		{
			return lastDirectionTurned;
		}
		
		public boolean isToTheLeft(JComponent man)
		{
			if(man.getX() < this.getX())
			{
				return true;
			}
			return false;
		}
		
		public boolean isToTheRight(JComponent man)
		{
			if(man.getX() > this.getX())
			{
				return true;
			}
			return false;
		}

}

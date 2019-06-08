import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PacmanBoard extends JFrame implements ActionListener
{
	PacMan man = new PacMan();

	private int counter = 0;
	private char currentImage = 'r';
	
	private int[][] map = {
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
		{1,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,0,1},
		{1,2,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,2,1},
		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,1},
		{1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1},
		{1,1,1,1,0,1,1,1,4,1,4,1,1,1,0,1,1,1,1},
		{4,4,4,1,0,1,4,4,4,4,4,4,4,1,0,1,4,4,4},
		{1,1,1,1,0,1,4,1,1,4,1,1,4,1,0,1,1,1,1},
		{4,4,4,4,0,4,4,1,4,4,4,1,4,4,0,4,4,4,4},
		{1,1,1,1,0,1,4,1,1,1,1,1,4,1,0,1,1,1,1},
		{4,4,4,1,0,1,4,4,4,4,4,4,4,1,0,1,4,4,4},
		{1,1,1,1,0,1,4,1,1,1,1,1,4,1,0,1,1,1,1},
		{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
		{1,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,0,1},
		{1,2,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,2,1},
		{1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1},
		{1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1},
		{1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1},
		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	};
	public PacmanBoard()
	{
		setBounds(200, 50, 585, 695);
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		man.setBounds(275, 483, man.getDiameter() + 10, man.getDiameter() + 10);
		this.add(man);
		setResizable(false);
		int xVal = 0;
		int yVal = 0;
			
		this.addKeyListener(new KeyListener()
		{

			public void keyPressed(KeyEvent e) 
			{	
				
				if(e.getKeyCode() == e.VK_S)
				{
					currentImage = 'd';
					setDown();
					man.setDy(2);
					man.setDx(0);					
				}
					
				if(e.getKeyCode() == e.VK_W)
				{
						currentImage = 'u';
						setUp();
						man.setDy(-2);
						man.setDx(0);
				}
					
				if(e.getKeyCode() == e.VK_D)
				{
					currentImage = 'r';
					setRight();
					man.setDx(2);
					man.setDy(0);
				}
					
				if(e.getKeyCode() == e.VK_A)
				{
					currentImage = 'l';
					setLeft();
					man.setDx(-2);
					man.setDy(0);
				}
			}

				public void keyReleased(KeyEvent e) 
				{

				}

				public void keyTyped(KeyEvent e) 
				{
					
				}
			});
			
			Timer t1 = new Timer(1000/60, this);
			t1.start();
			
			for(int i = 0; i < map.length; i++)
			{
				xVal = 0;
				for(int j = 0; j < map[0].length; j++)
				{
					if(map[i][j] == 1)
					{
						Wall wall = new Wall(xVal, yVal);
						add(wall);
					}
					if(map[i][j] == 0)
					{
						Pellet pellet = new Pellet(xVal, yVal);
						add(pellet);
					}
					if(map[i][j] == 2)
					{
						PowerPellet power = new PowerPellet(xVal,yVal);
						add(power);
					}
					xVal += 30;
				}
				yVal += 30;
			}
			
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
		public void actionPerformed(ActionEvent e) 
		{	
			if(man.canGoUp(map) && man.canGoDown(map) && man.canGoLeft(map) && man.canGoRight(map))
			{
				man.update();
				this.fixBounds();
			}
			counter++;
			if(counter == 6)
			{
				setCurrentImage();
			}
			
			if(counter == 12)
			{
				closeMouth();
				counter = 0;
			}

		}
		
		public void fixBounds()
		{
			
			if(man.getX() < 0)
			{
				man.setLocation(0, man.getY());
			}
			if(man.getX() > (this.getWidth() - man.getWidth()))
			{
				man.setLocation(this.getWidth() - man.getWidth(), man.getY());
			}
			if(man.getY() < 0)
			{
				man.setLocation(man.getX(), 0);
			}
			if(man.getY() > this.getHeight() - man.getHeight())
			{
				man.setLocation(man.getX(), this.getHeight() - man.getHeight());
			}
			if(man.getX() == 0 && (300 < man.getY() << 320))
			{
				man.setLocation(585, man.getY());
			}
			if(man.getX() == (540) && (300 < man.getY() << 320))
			{
				man.setLocation(0, man.getY());
			}
		}
		
		public void setLeft()
		{
			man.removeAll();
			ImageIcon imageIcon = new ImageIcon("images/pacman open left.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
			man.add(imageLabel);
		}
		
		public void setRight()
		{
			man.removeAll();
			ImageIcon imageIcon = new ImageIcon("images/pacman open.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
			man.add(imageLabel);
		}
		
		public void setUp()
		{
			man.removeAll();
			ImageIcon imageIcon = new ImageIcon("images/pacman open up.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
			man.add(imageLabel);
		}
		
		public void setDown()
		{
			man.removeAll();
			ImageIcon imageIcon = new ImageIcon("images/pacman open down.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
			man.add(imageLabel);
		}
		
		public void closeMouth()
		{
				man.removeAll();
				ImageIcon imageIcon = new ImageIcon("images/pacman closed.png");
				Image image = imageIcon.getImage(); 
				Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(newimg);
				JLabel imageLabel = new JLabel(imageIcon);
				imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
				man.add(imageLabel);
		}
		public void setCurrentImage()
		{
			if(currentImage == 'r')
				{
					man.removeAll();
					ImageIcon imageIcon = new ImageIcon("images/pacman open.png");
					Image image = imageIcon.getImage(); 
					Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(newimg);
					JLabel imageLabel = new JLabel(imageIcon);
					imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
					man.add(imageLabel);
				}
				
				if(currentImage == 'l')
				{
					man.removeAll();
					ImageIcon imageIcon = new ImageIcon("images/pacman open left.png");
					Image image = imageIcon.getImage(); 
					Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(newimg);
					JLabel imageLabel = new JLabel(imageIcon);
					imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
					man.add(imageLabel);
				}
				
				if(currentImage == 'u')
				{
					man.removeAll();
					ImageIcon imageIcon = new ImageIcon("images/pacman open up.png");
					Image image = imageIcon.getImage(); 
					Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(newimg);
					JLabel imageLabel = new JLabel(imageIcon);
					imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
					man.add(imageLabel);
				}
				
				if(currentImage == 'd')
				{
					man.removeAll();
					ImageIcon imageIcon = new ImageIcon("images/pacman open down.png");
					Image image = imageIcon.getImage(); 
					Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(newimg);
					JLabel imageLabel = new JLabel(imageIcon);
					imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
					man.add(imageLabel);
				}
		}
	
	public int[][] getMap()
	{
		return map;
	}
	public static void main (String[] args)
	{
		PacmanBoard board = new PacmanBoard();
	}
	
}

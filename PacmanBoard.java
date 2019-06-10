import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PacmanBoard extends JFrame implements ActionListener
{
	PacMan man = new PacMan();
	BlueGhost blueGhost = new BlueGhost();
	Sound sound = new Sound();

	private int counter = 0;
	private ArrayList<Wall> arr;
	private ArrayList<Pellet> arr2;
	private ArrayList<PowerPellet> arr3;
	private int score1;
	JLabel score;
	
	
	
	/**
	 * keeps track of the most recently pressed key
	 * so that the current image of pacman may be displayed
	 * 'r' is right, 'l' is left, 'd' is down, 'u' is up
	 */
	private char currentImage = 'r';
	
	/**
	 * two dimensional array that displays the map
	 */
	private int[][] map = 
	{
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
		arr = new ArrayList<Wall>();
		arr2 = new ArrayList<Pellet>();
		arr3 = new ArrayList<PowerPellet>();
		sound.playSound();
		setBounds(200, 50, 585, 695);
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		man.setBounds(270, 360, man.getDiameter() + 2, man.getDiameter() + 2);
		blueGhost.setBounds(0, 0, blueGhost.getWidth(), blueGhost.getHeight());
		this.add(man);
		this.add(blueGhost);
		man.setDx(0);
		man.setDy(0);
		setResizable(false);
		int xVal = 0;
		int yVal = 0;
		score = new JLabel("Score:" + score1);
		score.setBounds(0, 0, 100, 20);
		add(score);	
		this.addKeyListener(new KeyListener()
		{

			public void keyPressed(KeyEvent e) 
			{	
				if(e.getKeyCode() == e.VK_S)
				{
					currentImage = 'd';	
				}
					
				if(e.getKeyCode() == e.VK_W)
				{
					currentImage = 'u';
				}
					
				if(e.getKeyCode() == e.VK_D)
				{
					currentImage = 'r';
				}
					
				if(e.getKeyCode() == e.VK_A)
				{
					currentImage = 'l';
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
						arr.add(wall);
					}
					if(map[i][j] == 0)
					{
						Pellet pellet = new Pellet(xVal, yVal);
						add(pellet);
						arr2.add(pellet);
					}
					if(map[i][j] == 2)
					{
						PowerPellet power = new PowerPellet(xVal,yVal);
						add(power);
						arr3.add(power);
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
			man.update(currentImage, map);
			counter++;
			if(counter == 16)
			{
				counter = 0;
			}
			else if (counter > 8)
			{
				closeMouth();
			}
			else
			{
				setCurrentImage();
				
				if(man.getDy() == -3) {setUp();}
				if(man.getDy() == 3) {setDown();}
				if(man.getDx() == -3) {setLeft();}
				if(man.getDx() == 3) {setRight();}
				
			}
			blueGhost.update();
			this.fixBounds();
			for(Pellet a : arr2)
			{
				if(man.getX() == a.getX() && man.getY() == a.getY())
				{
					if(a.isVisible())
					{
						score1++;
						score.setText("Score: " + score1);
					}
					a.setVisible(false);
					
				}
			}
			for(PowerPellet b : arr3)
			{
				if(man.getX() == b.getX() && man.getY() == b.getY())
				{
					if(b.isVisible())
					{
						score1 += 5;
						score.setText("Score: " + score1);
					}
					b.setVisible(false);
					
				}
			}
		}
		
		/**
		 * fixes the bounds of pacman so that it does
		 * not go outside of the screen
		 */
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
		}
		
		public boolean isTouchingWall()
		{
			for(Wall w: arr)
			{
				if(man.getRect().intersects(w.getXValue(), w.getYValue(), w.getThickness(), w.getThickness()));
				{

					System.out.println("touching a wall");
					return true;
				}
			}
			System.out.println("OK");
			return false;
		}
		
		
		
		/**
		 * sets the image of pacman to the left
		 */
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
		
		/**
		 * sets the image of pacman to the right
		 */
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
		
		/**
		 * sets the image of pacman up
		 */
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
		
		/**
		 * sets the image of pacman down
		 */
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
		
		/**
		 * sets the image of pacman to the closed mouth
		 */
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
		
		/**
		 * sets the pacman to the appropriate image based on
		 * the key most recently pressed
		 */
		public void setCurrentImage()
		{
			if(currentImage == 'r')
				{
					setRight();
				}
				
				if(currentImage == 'l')
				{
					setLeft();
				}
				
				if(currentImage == 'u')
				{
					setUp();
				}
				
				if(currentImage == 'd')
				{
					setDown();
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

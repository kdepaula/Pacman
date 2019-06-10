import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
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
	RedGhost red = new RedGhost();
	BlueGhost blue = new BlueGhost();
	Sound sound = new Sound();

	private int counter = 0;
	
	
	/**
	 * keeps track of the most recently pressed key
	 * so that the current image of pacman may be displayed
	 * 'r' is right, 'l' is left, 'd' is down, 'u' is up
	 */
	private char currentImage = 'r';
	
	private ArrayList<Wall> arr;
	private ArrayList<Pellet> arr2;
	private ArrayList<PowerPellet> arr3;
	private int score1;
	JLabel score;
	
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
		{1,1,1,1,0,1,4,1,1,1,1,1,4,1,0,1,1,1,1},
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
		setBounds(200, 50, 578, 695);
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		man.setBounds(270, 360, man.getDiameter() + 2, man.getDiameter() + 2);
		red.setBounds(270, 360, red.getDiameter(), red.getDiameter());
		blue.setBounds(200, 360, blue.getDiameter(), blue.getDiameter());
		add(man);
		add(red);
		add(blue);
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
			counter++;
			if(counter == 14)
			{
				counter = 0;
			}
			else if (counter > 7)
			{
				man.closeMouth();
				if(red.isToTheLeft(man))
				{
					red.setLeftAlt();
				}
				if(red.isToTheRight(man))
				{
					red.setRightAlt();
				}
				if(blue.isToTheLeft(man))
				{
					blue.setLeftAlt();
				}
				if(blue.isToTheRight(man))
				{
					blue.setRightAlt();
				}
				notMovingRed();
			}
			else
			{
				if(red.isToTheLeft(man))
				{
					red.setLeft();
				}
				if(red.isToTheRight(man))
				{
					red.setRight();
				}
				if(blue.isToTheLeft(man))
				{
					blue.setLeft();
				}
				if(blue.isToTheRight(man))
				{
					blue.setRight();
				}
				setCurrentImage();
				
				if(man.getDy() == -3) {man.setUp();}
				if(man.getDy() == 3) {man.setDown();}
				if(man.getDx() == -3) {man.setLeft();}
				if(man.getDx() == 3) {man.setRight();}
				
			}
			setGhostDirection();
			setBlueGhostDirection();
			updateGhostDirection();
		}
		
		
		
		/**
		 * sets the pacman to the appropriate image based on
		 * the key most recently pressed
		 */
		public void setCurrentImage()
		{
			if(currentImage == 'r')
				{
					man.setRight();
				}
				
				if(currentImage == 'l')
				{
					man.setLeft();
				}
				
				if(currentImage == 'u')
				{
					man.setUp();
				}
				
				if(currentImage == 'd')
				{
					man.setDown();
				}
		}
		
		
		public void setGhostDirection()
		{
			if(0 < red.findAngle(man) && red.findAngle(man) < 90)
			{
				red.setRelativeQuadrant(2);
			}
			
			if(90 < red.findAngle(man) && red.findAngle(man) < 180)
			{
				red.setRelativeQuadrant(1);
			}
			
			if(180 < red.findAngle(man) && red.findAngle(man) < 270)
			{
				red.setRelativeQuadrant(3);
			}
			
			if(270 < red.findAngle(man) && red.findAngle(man) < 360)
			{
				red.setRelativeQuadrant(4);
			}
			
		}
		
		public void setBlueGhostDirection()
		{
			if(0 < blue.findAngle(man) && blue.findAngle(man) < 90)
			{
				blue.setRelativeQuadrant(2);
			}
			
			if(90 < blue.findAngle(man) && blue.findAngle(man) < 180)
			{
				blue.setRelativeQuadrant(1);
			}
			
			if(180 < blue.findAngle(man) && blue.findAngle(man) < 270)
			{
				blue.setRelativeQuadrant(3);
			}
			
			if(270 < blue.findAngle(man) && blue.findAngle(man) < 360)
			{
				blue.setRelativeQuadrant(4);
			}
			
		}
		
		public void updateGhostDirection()
		{
			if(red.canGoUp(map) && red.findAngle(man) == 90)
			{
				red.moveUp();
			}
			else if(red.canGoDown(map) && red.findAngle(man) == 270)
			{
				red.moveDown();
			}
			else if(red.canGoLeft(map) && red.findAngle(man) == 180)
			{
				red.moveLeft();
			}
			else if(red.canGoRight(map) && (red.findAngle(man) == 0 || red.findAngle(man) == 360))
			{
				red.moveRight();
			}
			if(red.canGoUp(map) && (red.getRelativeQuadrant() == 1 || red.getRelativeQuadrant() == 2))
			{
				red.moveUp();
			}
			else if(red.canGoDown(map) && (red.getRelativeQuadrant() == 3 || red.getRelativeQuadrant() == 4))
			{
				red.moveDown();
			}
			else if(red.canGoLeft(map) && (red.getRelativeQuadrant() == 1 || red.getRelativeQuadrant() == 3))
			{
				red.moveLeft();
			}
			else if(red.canGoRight(map) && (red.getRelativeQuadrant() == 2 || red.getRelativeQuadrant() == 4))
			{
				red.moveRight();
			}
		}
		
		public void notMovingRed()
		{
			if(red.getDy() == 0 && red.getDx() == 0)
			{
				int value = (int) ((Math.random() * 10) + 1);
				if(value <= 2)
				{
					if(red.canGoDown(map))
					red.moveDown();
					if(red.canGoUp(map))
						red.moveUp();
					if(red.canGoRight(map))
						red.moveRight();
				}
				else if(2 < value && value <= 4)
				{
					if(red.canGoUp(map))
						red.moveRight();
					else if(red.canGoLeft(map))
							red.moveLeft();
					else if(red.canGoRight(map))
							red.moveRight();
				}
				else if(4 < value && value <= 8)
				{
					if(red.canGoRight(map))
						red.moveRight();
					else if(red.canGoUp(map))
							red.moveUp();
					else if(red.canGoLeft(map))
							red.moveLeft();	
				}
				else if(8 < value && value <= 10)
				{
					if(red.canGoLeft(map))
						red.moveLeft();
					else if(red.canGoUp(map))
							red.moveUp();
					else if(red.canGoRight(map))
							red.moveRight();	
				}
			}
		}
		
		
	public static void main (String[] args)
	{
		PacmanBoard board = new PacmanBoard();
	}
	
}

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
	RedGhost red = new RedGhost();
	Sound sound = new Sound();

	private int counter = 0;
	
	
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
		sound.playSound();
		setBounds(200, 50, 585, 695);
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		man.setBounds(270, 360, man.getDiameter() + 2, man.getDiameter() + 2);
		red.setBounds(270, 360, red.getDiameter() + 2, red.getDiameter() + 2);
		red.setVisible(true);
		this.add(man);
		this.add(blueGhost);
		man.setDx(0);
		man.setDy(0);
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
			man.update(currentImage, map);
			counter++;
			if(counter == 16)
			{
				counter = 0;
			}
			else if (counter > 8)
			{
				man.closeMouth();
			}
			else
			{
				setCurrentImage();
				
				if(man.getDy() == -3) {man.setUp();}
				if(man.getDy() == 3) {man.setDown();}
				if(man.getDx() == -3) {man.setLeft();}
				if(man.getDx() == 3) {man.setRight();}
				
			}
			
			red.update();
			fixBounds();
		}
		
		/**
		 * fixes the bounds of pacman so that it does
		 * not go outside of the screen
		 */
		public void fixBounds()
		{

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
	
	public static void main (String[] args)
	{
		PacmanBoard board = new PacmanBoard();
	}
	
}

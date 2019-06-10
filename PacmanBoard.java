import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PacmanBoard extends JFrame implements ActionListener
{
	PacMan man = new PacMan();
	RedGhost red = new RedGhost("Red");
	BlueGhost blue = new BlueGhost("Blue");
	OrangeGhost orange = new OrangeGhost("Orange");
	PinkGhost pink = new PinkGhost("Pink");
	static Sound sound = new Sound();

	private boolean frightened = false;
	/**
	 * counts number of pellets
	 */
	private int counts = 0;
	/**
	 * counter and largeCounter time the images of ghosts
	 */
	private int counter = 0;
	private int largeCounter = 0;
	private int scaredCounter = 0;
	private boolean hitRed = false;
	private boolean hitPink = false;
	private boolean hitOrange= false;
	private boolean hitBlue = false;
	private int numTimesDied = 0;
	private JLabel imageLabel;
	private JLabel imageLabel1;
	private JLabel imageLabel11;
	private JLabel imageLabel2;
	private JLabel gameOver = new JLabel("GAME OVER");
	private JLabel win = new JLabel("YOU WON");
	private boolean endAll = false;
	
	/**
	 * keeps track of the most recently pressed key
	 * so that the current image of pacman may be displayed
	 * 'r' is right, 'l' is left, 'd' is down, 'u' is up
	 */
	private char currentImage = 'r';
	
	private ArrayList<Wall> arr;
	private ArrayList<Pellet> arr2;
	private ArrayList<PowerPellet> arr3;
	private int score1 = 0;
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
		setBounds(200, 50, 578, 750);
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		man.setBounds(270, 480, man.getDiameter() + 2, man.getDiameter() + 2);
		red.setBounds(270, 240, red.getDiameter() + 2, red.getDiameter() + 2);
		blue.setBounds(240,300, blue.getDiameter() + 2, blue.getDiameter() + 2);
		orange.setBounds(300,300, orange.getDiameter() + 2, orange.getDiameter() + 2);
		pink.setBounds(270,300, pink.getDiameter() + 2, pink.getDiameter() + 2);
		ImageIcon imageIcon = new ImageIcon("images/pacman open.png");
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(10, 680, 20, 20);
		add(imageLabel);
		ImageIcon imageIcon1 = new ImageIcon("images/pacman open.png");
		Image image1 = imageIcon1.getImage(); 
		Image newimg1 = image1.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
		imageIcon1 = new ImageIcon(newimg1);
		imageLabel1 = new JLabel(imageIcon1);
		imageLabel1.setBounds(40, 680, 20, 20);
		add(imageLabel1);
		ImageIcon imageIcon11 = new ImageIcon("images/pacman open.png");
		Image image11 = imageIcon11.getImage(); 
		Image newimg11 = image11.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
		imageIcon11 = new ImageIcon(newimg11);
		imageLabel11 = new JLabel(imageIcon11);
		imageLabel11.setBounds(70, 680, 20, 20);
		add(imageLabel11);
		ImageIcon imageIcon2 = new ImageIcon("images/strawberry.png");
		Image image2 = imageIcon2.getImage(); 
		Image newimg2 = image2.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
		imageIcon2 = new ImageIcon(newimg2);
		imageLabel2 = new JLabel(imageIcon2);
		imageLabel2.setBounds(270, 365, 20, 20);
		add(gameOver);
		gameOver.setVisible(false);
		gameOver.setForeground (Color.red);
		gameOver.setBounds(140, 120, 500, 500);
		gameOver.setFont(new Font("Serif", Font.PLAIN, 50));
		add(win);
		win.setVisible(false);
		win.setForeground(Color.yellow);
		win.setBounds(140,120,500,500);
		win.setFont(new Font("Serif",Font.PLAIN,50));
		add(man);
		add(red);
		add(blue);
		add(orange);
		add(pink);
		man.setDx(0);
		man.setDy(0);
		setResizable(false);
		int xVal = 0;
		int yVal = 0;
		score = new JLabel("Score:" + score1);
		score.setLocation(0, -140);
		score.setSize(200, 300);
		score.setFont(new Font("Serif", Font.PLAIN, 25));
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
			if(!endAll && !frightened && ((red.getX() < man.getX() + 10 && red.getX() > man.getX() - 10 && red.getY() < man.getY() + 10 && red.getY() > man.getY() - 10
				|| (pink.getX() < man.getX() + 10 && pink.getX() > man.getX() - 10 && pink.getY() < man.getY() + 10 && pink.getY() > man.getY() - 10)
				||(orange.getX() < man.getX() + 10 && orange.getX() > man.getX() - 10 && orange.getY() < man.getY() + 10 && orange.getY() > man.getY() - 10)
				||(blue.getX() < man.getX() + 10 && blue.getX() > man.getX() - 10 && blue.getY() < man.getY() + 10 && blue.getY() > man.getY() - 10))))
			{
				sound.playSound("death");
				numTimesDied++;
				man.setDy(0);
				man.setDx(0);
				red.setDx(0);
				red.setDy(0);
				man.setLocation(270, 480);
				red.setLocation(270, 240);
				pink.setLocation(270, 300);
				orange.setLocation(240, 300);
				blue.setLocation(300, 300);
				largeCounter = 0;
				if(numTimesDied == 1)
				{
					imageLabel.setVisible(false);
				}
				else if(numTimesDied == 2)
				{
					imageLabel1.setVisible(false);
				}
				else if(numTimesDied == 3)
				{
					imageLabel11.setVisible(false);
					gameOver.setVisible(true);
					endAll = true;
				}
			}
			else if(!endAll)
			{	largeCounter++;
				if(largeCounter == 150)
				{
					pink.setLocation(270, 240);
				}
				if(largeCounter == 300)
				{
					blue.setLocation(270, 240);
				}
				if(largeCounter == 450)
				{
					orange.setLocation(270, 240);
					add(imageLabel2);
				}
				notMoving(red);
				notMoving(orange);
				notMoving(blue);
				notMoving(pink);
				man.update(currentImage, map);
				for(Pellet a : arr2)
				{
				if(man.getX() == a.getX() && man.getY() == a.getY())
				{
					if(a.isVisible())
					{
						score1++;
						score.setText("Score: " + score1);
						sound.playSound("chomp");
						counts++;
					}
					a.setVisible(false);
					
				}
			}
			for(PowerPellet b : arr3)
			{
				if(man.getX() == b.getX() && man.getY() == b.getY() && b.isVisible())
				{
					frightened = true;
					if(b.isVisible())
					{
						score1 += 5;
						score.setText("Score: " + score1);
						sound.playSound("chomp");
					}
					b.setVisible(false);
					scaredCounter = 0;
				}
			}
			if(counts == 153)
			{
				man.setDy(0);
				man.setDx(0);
				red.setDx(0);
				red.setDy(0);
				man.setLocation(270, 480);
				red.setLocation(270, 240);
				pink.setLocation(270, 300);
				orange.setLocation(240, 300);
				blue.setLocation(300, 300);
				win.setVisible(true);
				endAll = true;
				sound.playSound("intermission");
			}
			counter++;
			scaredCounter++;
			if(counter == 12)
			{
				counter = 0;
			}
			else if (counter > 6)
			{
				man.closeMouth();
				if(frightened && !hitRed)
				{
					red.setScaredAlt();
				}
				else if(red.isToTheLeft(man))
				{
					red.setLeftAlt("Red");
					red.setVisible(true);
				}
				else if(red.isToTheRight(man))
				{
					red.setRight("Red");
					red.setVisible(true);
				}
				if(frightened && !hitBlue)
				{
					blue.setScaredAlt();
				}
				else if(blue.isToTheLeft(man))
				{
					blue.setLeftAlt("Blue");
					blue.setVisible(true);
				}
				else if(blue.isToTheRight(man))
				{
					blue.setRightAlt("Blue");
					blue.setVisible(true);
				}
				if(frightened && !hitPink)
				{
					pink.setScaredAlt();
				}
				else if(pink.isToTheLeft(man))
				{
					pink.setLeftAlt("Pink");
					pink.setVisible(true);
				}
				else if(pink.isToTheRight(man))
				{
					pink.setRightAlt("Pink");
					pink.setVisible(true);
				}
				if(frightened && !hitOrange)
				{
					orange.setScaredAlt();
				}
				else if(orange.isToTheLeft(man))
				{
					orange.setLeftAlt("Orange");
					orange.setVisible(true);
				}
				else if(orange.isToTheRight(man))
				{
					orange.setRightAlt("Orange");
					orange.setVisible(true);
				}
			}
			else
			{
				if(!frightened)
				{
					setPinkGhostDirection();
					setOrangeGhostDirection();
					setBlueGhostDirection();
					setRedGhostDirection();
					updateGhostDirectionRed();
					updateGhostDirectionOrange();
					updateGhostDirectionBlue();
					updateGhostDirectionPink();
				}
				if(frightened && scaredCounter < 300 && !hitRed)
				{
					red.setScared();
					ghostHitRed();
					updateScaredGhost(red);
				}
				else if(red.isToTheLeft(man))
				{
					red.setLeft("Red");
					frightened = false;
					hitRed = false;
				}
				else if(red.isToTheRight(man))
				{
					red.setRight("Red");
					frightened = false;
					hitRed = false;
				}
				if(frightened && scaredCounter < 300 && !hitBlue)
				{
					blue.setScared();
					ghostHitBlue();
					updateScaredGhost(blue);
				}
				else if(blue.isToTheLeft(man))
				{
					blue.setLeft("Blue");
					hitBlue = false;
				}
				else if(blue.isToTheRight(man))
				{
					blue.setRight("Blue");
					hitBlue = false;
				}
				if(frightened && scaredCounter < 300 && !hitPink)
				{
					pink.setScared();
					ghostHitPink();
					updateScaredGhost(pink);
				}
				else if(pink.isToTheLeft(man))
				{
					pink.setLeft("Pink");
					hitPink = false;
				}
				else if(pink.isToTheRight(man))
				{
					pink.setRight("Pink");
					hitPink = false;
				}
				if(frightened && scaredCounter < 300 && !hitOrange)
				{
					orange.setScared();
					ghostHitOrange();
					updateScaredGhost(orange);
				}
				else if(orange.isToTheLeft(man))
				{
					orange.setLeft("Orange");
					hitOrange = false;
				}
				else if(orange.isToTheRight(man))
				{
					orange.setRight("Orange");
					hitOrange = false;
				}
				
				setCurrentImage();
				if(man.getDy() == -3) {man.setUp();}
				if(man.getDy() == 3) {man.setDown();}
				if(man.getDx() == -3) {man.setLeft();}
				if(man.getDx() == 3) {man.setRight();}
			}
		}	
			fruitVisibility(imageLabel2);
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
		
		public void setRedGhostDirection()
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
		
		public void setPinkGhostDirection()
		{
			if(0 < pink.findAngle(man) && pink.findAngle(man) < 90)
			{
				pink.setRelativeQuadrant(2);
			}
			
			if(90 < pink.findAngle(man) && pink.findAngle(man) < 180)
			{
				pink.setRelativeQuadrant(1);
			}
			
			if(180 < pink.findAngle(man) && pink.findAngle(man) < 270)
			{
				pink.setRelativeQuadrant(3);
			}
			
			if(270 < pink.findAngle(man) && pink.findAngle(man) < 360)
			{
				pink.setRelativeQuadrant(4);
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
		
		public void setOrangeGhostDirection()
		{
			if(0 < orange.findAngle(man) && orange.findAngle(man) < 90)
			{
				orange.setRelativeQuadrant(2);
			}
			
			if(90 < orange.findAngle(man) && orange.findAngle(man) < 180)
			{
				orange.setRelativeQuadrant(1);
			}
			
			if(180 < orange.findAngle(man) && orange.findAngle(man) < 270)
			{
				orange.setRelativeQuadrant(3);
			}
			
			if(270 < orange.findAngle(man) && orange.findAngle(man) < 360)
			{
				orange.setRelativeQuadrant(4);
			}
		}
		
		
		public void updateScaredGhost(Ghost j)
		{
			int value = (int) ((Math.random() * 10) + 1);
			if(value <= 2)
			{
				if(j.canGoDown(map))
				j.moveDown();
				else if(j.canGoUp(map))
					j.moveUp();
				else if(j.canGoRight(map))
					j.moveRight();
			}
			else if(2 < value && value <= 4)
			{
				if(j.canGoUp(map))
					j.moveRight();
				else if(j.canGoLeft(map))
						j.moveLeft();
				else if(j.canGoRight(map))
						j.moveRight();
			}
			else if(4 < value && value <= 8)
			{
				if(j.canGoRight(map))
					j.moveRight();
				else if(j.canGoUp(map))
						j.moveUp();
				else if(j.canGoLeft(map))
						j.moveLeft();	
			}
			else if(8 < value && value <= 10)
			{
				if(j.canGoLeft(map))
					j.moveLeft();
				else if(j.canGoUp(map))
						j.moveUp();
				else if(j.canGoRight(map))
						j.moveRight();	
			}
			if(j.canGoDown(map) && j.findAngle(man) == 90)
			{
				j.moveDown();
			}
			else if(j.canGoUp(map) && j.findAngle(man) == 270)
			{
				j.moveUp();
			}
			else if(j.canGoRight(map) && j.findAngle(man) == 180)
			{
				j.moveRight();
			}
			else if(j.canGoLeft(map) && (j.findAngle(man) == 0 || j.findAngle(man) == 360))
			{
				j.moveLeft();
			}
			if(j.canGoUp(map) && (j.getRelativeQuadrant() == 3 || j.getRelativeQuadrant() == 4))
			{
				j.moveUp();
			}
			else if(j.canGoDown(map) && (j.getRelativeQuadrant() == 1 || j.getRelativeQuadrant() == 2))
			{
				j.moveDown();
			}
			else if(j.canGoLeft(map) && (j.getRelativeQuadrant() == 2 || j.getRelativeQuadrant() == 4))
			{
				j.moveLeft();
			}
			else if(j.canGoRight(map) && (j.getRelativeQuadrant() == 1 || j.getRelativeQuadrant() == 3))
			{
				j.moveRight();
			}
		}
		
		
		public void updateGhostDirectionPink()
		{
			if(pink.canGoDown(map) && pink.findAngle(man) == 270)
			{
				pink.moveDown();
			}
			else if(pink.canGoUp(map) && pink.findAngle(man) == 90)
			{
				pink.moveUp();
			}
			else if(pink.canGoLeft(map) && pink.findAngle(man) == 180)
			{
				pink.moveLeft();
			}
			else if(pink.canGoRight(map) && (pink.findAngle(man) == 0 || pink.findAngle(man) == 360))
			{
				pink.moveRight();
			}
			else if(pink.canGoDown(map) && (pink.getRelativeQuadrant() == 3 || pink.getRelativeQuadrant() == 4))
			{
				pink.moveDown();
			}
			if(pink.canGoUp(map) && (pink.getRelativeQuadrant() == 1 || pink.getRelativeQuadrant() == 2))
			{
				pink.moveUp();
			}
			else if(pink.canGoLeft(map) && (pink.getRelativeQuadrant() == 1 || pink.getRelativeQuadrant() == 3))
			{
				pink.moveLeft();
			}
			else if(pink.canGoRight(map) && (pink.getRelativeQuadrant() == 2 || pink.getRelativeQuadrant() == 4))
			{
				pink.moveRight();
			}
		}
		
		public void updateGhostDirectionOrange()
		{

			if(orange.canGoLeft(map) && orange.findAngle(man) == 180)
			{
				orange.moveLeft();
			}
			else if(orange.canGoUp(map) && orange.findAngle(man) == 90)
			{
				orange.moveUp();
			}
			else if(orange.canGoDown(map) && orange.findAngle(man) == 270)
			{
				orange.moveDown();
			}
			else if(orange.canGoRight(map) && (orange.findAngle(man) == 0 || orange.findAngle(man) == 360))
			{
				orange.moveRight();
			}
			else if(orange.canGoLeft(map) && (orange.getRelativeQuadrant() == 1 || orange.getRelativeQuadrant() == 3))
			{
				orange.moveLeft();
			}
			if(orange.canGoUp(map) && (orange.getRelativeQuadrant() == 1 || orange.getRelativeQuadrant() == 2))
			{
				orange.moveUp();
			}
			else if(orange.canGoDown(map) && (orange.getRelativeQuadrant() == 3 || orange.getRelativeQuadrant() == 4))
			{
				orange.moveDown();
			}
			else if(orange.canGoRight(map) && (orange.getRelativeQuadrant() == 2 || orange.getRelativeQuadrant() == 4))
			{
				orange.moveRight();
			}
		}
		
		public void updateGhostDirectionRed()
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
		
		public void updateGhostDirectionBlue()
		{
			if(blue.canGoRight(map) && (blue.findAngle(man) == 0 || blue.findAngle(man) == 360))
			{
				blue.moveRight();
			}
			else if(blue.canGoUp(map) && blue.findAngle(man) == 90)
			{
				blue.moveUp();
			}
			else if(blue.canGoDown(map) && blue.findAngle(man) == 270)
			{
				blue.moveDown();
			}
			else if(blue.canGoLeft(map) && blue.findAngle(man) == 180)
			{
				blue.moveLeft();
			}
			else if(blue.canGoRight(map) && (blue.getRelativeQuadrant() == 2 || blue.getRelativeQuadrant() == 4))
			{
				blue.moveRight();
			}
			if(blue.canGoUp(map) && (blue.getRelativeQuadrant() == 1 || blue.getRelativeQuadrant() == 2))
			{
				blue.moveUp();
			}
			else if(blue.canGoDown(map) && (blue.getRelativeQuadrant() == 3 || blue.getRelativeQuadrant() == 4))
			{
				blue.moveDown();
			}
			else if(blue.canGoLeft(map) && (blue.getRelativeQuadrant() == 1 || blue.getRelativeQuadrant() == 3))
			{
				blue.moveLeft();
			}
			else if(blue.canGoRight(map) && (blue.getRelativeQuadrant() == 2 || blue.getRelativeQuadrant() == 4))
			{
				blue.moveRight();
			}
		}
		
		public void fruitVisibility(JLabel j)
		{
			if(j.getX() < man.getX() + 10 && j.getX() > man.getX() - 10 && j.getY() < man.getY() + 10 && j.getY() > man.getY() - 10 && j.isVisible())
			{
				j.setVisible(false);
				score1 += 200;
				sound.playSound("eatfruit");
			}
		}
		
		public void ghostHitRed()
		{
			if(red.getX() < man.getX() + 10 && red.getX() > man.getX() - 10 && red.getY() < man.getY() + 10 && red.getY() > man.getY() - 10)
			{
				red.setLocation(270, 240);
				hitRed = true;
				score1 += 200;
				sound.playSound("eatghost");
			}
		}
		
		public void ghostHitPink()
		{
			if(pink.getX() < man.getX() + 10 && pink.getX() > man.getX() - 10 && pink.getY() < man.getY() + 10 && pink.getY() > man.getY() - 10)
			{
				pink.setLocation(270, 240);
				hitPink = true;
				score1 += 200;
				sound.playSound("eatghost");
			}
		}
		
		public void ghostHitBlue()
		{
			if(blue.getX() < man.getX() + 10 && blue.getX() > man.getX() - 10 && blue.getY() < man.getY() + 10 && blue.getY() > man.getY() - 10)
			{
				blue.setLocation(270, 240);
				hitBlue = true;
				score1 += 200;
				sound.playSound("eatghost");
			}
		}
		public void ghostHitOrange()
		{
			if(orange.getX() < man.getX() + 10 && orange.getX() > man.getX() - 10 && orange.getY() < man.getY() + 10 && orange.getY() > man.getY() - 10)
			{
				orange.setLocation(270, 240);
				hitOrange = true;
				score1 += 200;
				sound.playSound("eatghost");
			}
		}
		
		public void notMoving(Ghost j)
		{
			if(j.getDy() == 0 && j.getDx() == 0)
			{
				int value = (int) ((Math.random() * 10) + 1);
				if(value <= 2)
				{
					if(j.canGoDown(map))
					j.moveDown();
					else if(j.canGoUp(map))
						j.moveUp();
					else if(j.canGoRight(map))
						j.moveRight();
				}
				else if(2 < value && value <= 4)
				{
					if(j.canGoUp(map))
						j.moveRight();
					else if(j.canGoLeft(map))
							j.moveLeft();
					else if(j.canGoRight(map))
							j.moveRight();
				}
				else if(4 < value && value <= 8)
				{
					if(j.canGoRight(map))
						j.moveRight();
					else if(j.canGoUp(map))
							j.moveUp();
					else if(j.canGoLeft(map))
							j.moveLeft();	
				}
				else if(8 < value && value <= 10)
				{
					if(j.canGoLeft(map))
						j.moveLeft();
					else if(j.canGoUp(map))
							j.moveUp();
					else if(j.canGoRight(map))
							j.moveRight();	
				}
			}
		}
		
		
	public static void main (String[] args)
	{
		PacmanBoard board = new PacmanBoard();
		sound.playSound("beginning");
	}
	
}

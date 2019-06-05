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

public class Frame extends JFrame implements ActionListener
{
	PacMan man = new PacMan();

	private int counter = 0;
	private char currentImage = 'r';
	
	public Frame()
	{
		this.setBounds(100, 100, 600, 600);
		this.setLayout(null);
		man.setBounds(0, 0, man.getDiameter(), man.getDiameter());
		this.add(man);
		
		this.addKeyListener(new KeyListener()
		{

			public void keyPressed(KeyEvent e) 
			{	
				
					if(e.getKeyCode() == e.VK_S)
					{
						currentImage = 'd';
						setDown();
						man.setDy(3);
						man.setDx(0);
					}
				
					if(e.getKeyCode() == e.VK_W)
					{
						currentImage = 'u';
						setUp();
						man.setDy(-3);
						man.setDx(0);
					}
				
					if(e.getKeyCode() == e.VK_D)
					{
						currentImage = 'r';
						setRight();
						man.setDx(3);
						man.setDy(0);
					}
				
					if(e.getKeyCode() == e.VK_A)
					{
						currentImage = 'l';
						setLeft();
						man.setDx(-3);
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
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) 
	{	
		this.fixBounds();
		man.update();
		counter++;
		if(counter == 20)
		{
			closeMouth();
			counter = 0;
			System.out.print(counter);
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
		counter++;
		System.out.print(counter);
		if(counter % 60 < 30)
		{
			man.removeAll();
			ImageIcon imageIcon = new ImageIcon("images/pacman closed.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(man.getDiameter(), man.getDiameter(),  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, man.getDiameter(), man.getDiameter());
			add(imageLabel);
			counter = 0;
		}
		else
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
				add(imageLabel);
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
				add(imageLabel);
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
				add(imageLabel);
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
				add(imageLabel);
			}
		}
	}
	
	public static void main(String args[])
	{
		new Frame();
	}

}

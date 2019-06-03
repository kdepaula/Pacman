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
	
	public Frame()
	{
		this.setBounds(100, 100, 600, 600);
		this.setLayout(null);
		man.setBounds(0, 0, 40, 40);
		this.add(man);
		
		this.addKeyListener(new KeyListener()
		{

			public void keyPressed(KeyEvent e) 
			{	
				
					if(e.getKeyCode() == e.VK_S)
					{
						man.setDy(3);
						man.setDx(0);
					}
				
					if(e.getKeyCode() == e.VK_W)
					{
						man.setDy(-3);
						man.setDx(0);
					}
				
					if(e.getKeyCode() == e.VK_D)
					{
						man.setDx(3);
						man.setDy(0);
						setRight();
					}
				
					if(e.getKeyCode() == e.VK_A)
					{
						man.setDx(-3);
						man.setDy(0);
						setLeft();
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
	}
	
	public void fixBounds()
	{
		
		if(man.getX() < 0)
		{
			man.setLocation(0, man.getY());
		}
		if(man.getX() > this.getWidth() - man.getWidth())
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
		Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(0, 0, 40, 40);
		man.add(imageLabel);
	}
	
	public void setRight()
	{
		man.removeAll();
		ImageIcon imageIcon = new ImageIcon("images/pacman open.png");
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(0, 0, 40, 40);
		man.add(imageLabel);
	}
	
	public static void main(String args[])
	{
		new Frame();
	}

}

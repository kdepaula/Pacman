	import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class PacMan extends JComponent
{
		private int dx;
		private int dy;
		
		public PacMan()
		{
			ImageIcon imageIcon = new ImageIcon("images/pacman open.png");
			Image image = imageIcon.getImage(); 
			Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setBounds(0, 0, 40, 40);
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
		
}

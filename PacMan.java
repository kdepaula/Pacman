	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.geom.Ellipse2D;
	import java.awt.geom.Rectangle2D;
	import javax.swing.JComponent;
import javax.swing.JLabel;

public class PacMan extends JLabel
{
		private int dx;
		private int dy;
		
		
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
		
		public boolean canGoUp(int[][] map)
		{
			if(map[(getY()/20)-1][getX()/20] == 1) {return false;}
			return true;
		}
		public boolean canGoDown(int[][] map)
		{
			if(map[(getY()/20)+1][getX()/20] == 1) {return false;}
			return true;
		}
		public boolean canGoLeft(int[][] map)
		{
			if(map[(getY()/20)][(getX()/20)-1] == 1) {return false;}
			return true;
		}
		public boolean canGoRight(int[][] map)
		{
			if(map[(getY()/20)-1][(getX()/20)+1] == 1) {return false;}
			return true;
		}
		
		public int getDy()
		{
			return dy;
		}
		
}

import javax.swing.JFrame;

public class PacmanBoard extends JFrame
{
	private int[][] map = {
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
		{1,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,0,1},
		{1,2,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,2,1},
		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,1},
		{1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1},
		{1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1},
		{0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,0},
		{1,1,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1,1,1},
		{0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0},
		{1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1},
		{0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,0},
		{1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1},
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
		setBounds(0, 0, 585, 695);
		setLayout(null);
		int xVal = 0;
		int yVal = 0;
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
				xVal += 30;
			}
			yVal += 30;
		}
		setVisible(true);
		
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

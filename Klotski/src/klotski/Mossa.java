package klotski;

public class Mossa
{
	private Coord c_in;
	private Coord c_fin;
	private char dir;
	private int num_pezzo;
	
	public Mossa(Coord cin, Coord cfin, int pezzo)
	{
		c_in = cin;
		c_fin = cfin;
		num_pezzo = pezzo;
		
		if(c_fin.compare(c_in.down()))
		{
			dir = 'D';
		}
		
		if(c_fin.compare(c_in.up()))
		{
			dir = 'U';
		}
		
		if(c_fin.compare(c_in.right()))
		{
			dir = 'R';
		}
		
		if(c_fin.compare(c_in.left()))
		{
			dir = 'L';
		}
		
	}

	public Mossa(Coord cin, char DIR, int pezzo)
	{
		c_in = cin;	
		dir = DIR;
		
		switch(DIR) {
		 case 'U':
			 c_fin = c_in.up();
			 break;
			 
		 case 'L':
			 c_fin = c_in.left();
			 break;
			 
		 case 'D':
			 c_fin = c_in.down();
			 break;
			 
		 case 'R':
			 c_fin = c_in.right();
			 break;
		default:
			c_fin = c_in;
			break;
			
		}
		
		num_pezzo = pezzo;
	}

	public int getNp()
	{
		return num_pezzo;
	}

	public void setNp(int np)
	{
		num_pezzo = np;
	}
	public char getDir()
	{
		return dir;
	}
	public Coord getCin()
	{
		return c_in;
	}
	public Coord getCfin()
	{
		return c_fin;
	}

	public Mossa reverse()
	{
		
		char d = 'D';
		Coord c = new Coord(c_in.getY(), c_in.getX());
		
		if(dir == 'U')
		{
			d='D';
			c=c.up();
		}
		else if(dir == 'L')
		{
			d='R';
			c=c.left();
		}
		else if(dir == 'D')
		{
			d='U';
			c=c.down();
		}
		else if(dir == 'R')
		{
			d='L';
			c=c.right();
		}
		Mossa m = new Mossa(c, d, num_pezzo);
		return m;
	}

}
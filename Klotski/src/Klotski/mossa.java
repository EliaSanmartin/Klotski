package Klotski;

public class mossa
{
	coord c_in;
	char dir;
	int num_pezzo;
	
	public mossa(coord cin, char DIR, int pezzo)
	{
		c_in = cin;
		dir = DIR;
		num_pezzo = pezzo;
	}
	
	public mossa reverse()
	{
		
		char d = 'D';
		coord c = new coord(c_in.y, c_in.x);
		
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
		mossa m = new mossa(c, d, num_pezzo);
		return m;
	}

}
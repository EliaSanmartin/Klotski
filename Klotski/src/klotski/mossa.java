package klotski;

public class mossa
{
	coord c_in;
	coord c_fin;
	char dir;
	int num_pezzo;
	
	public mossa(coord cin, coord cfin, int pezzo)
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
	
	public mossa(coord cin, char DIR, int pezzo)
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
		}
		
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
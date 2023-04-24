package Klotski;

public class pezzo 
{
	int dim1;
	int dim2;
	coord coord = new coord(99,99);
	
	public pezzo(int y, int x)
	{
		dim1 = x; 
		dim2 = y;	
	}
	
	public void reset()
	{
		coord.x=99;
		coord.y=99;
	}
	
	
	
	public boolean controlla_mossa(coord c, int [][] tab, int num_pezzo)
	{
	//	coord c = new coord(coord2, coord1);
		//controllo se si fa una mossa valida o se è la prima mossa
				if (coord.x != 99)
				{
					int diff1 = coord.x-c.x;
				
					int diff2 = coord.y-c.y;
					if (diff1 < 0)
						diff1 = -diff1;
				
					if (diff2 < 0)
						diff2 = -diff2;					
					
					if (diff1 + diff2 != 1)
						return false;
				}
				
				//controllo se la possa è plausibile
				if ((tab.length < c.x+dim1) || (tab[0].length < c.y+dim2))
				{
					return false;
				}

				if((c.x < 0) || (c.y < 0))
				{
					return false;
				}
				//controllo se dove voglio posizionarmi c'è spazio
				for (int i=c.x; i<c.x+dim1; i++)
					for (int j=c.y; j<c.y+dim2; j++)
						if ((tab[i][j] != 0) && (tab[i][j] != num_pezzo))
							return false;
		return true;
	}
	
	
	
	public boolean imposta_coord(coord c, int [][] tab, int num_pezzo)
	{		
		if (controlla_mossa(c, tab, num_pezzo) == false)
			return false;
		
		// muovo pezzo
		//modifico coordinate
		coord.x = c.x;
		coord.y = c.y;
		
		//cancello pezzo se già presente
		for (int i=0; i<tab.length; i++)
			for (int j=0; j<tab[0].length; j++)
				if (tab[i][j] == num_pezzo)
					tab[i][j] = 0;
		
		for (int i=c.x; i<c.x+dim1; i++)
			for (int j=c.y; j<c.y+dim2; j++)
				tab[i][j] = num_pezzo;
		
		return true;	
	}
	

	public boolean controlla_mosse(int [][] tab)
	{
		coord c = new coord(coord.x, coord.y);
		boolean trovato = false;
		//su
			if(controlla_mossa(coord.up(), tab, tab[coord.x][coord.y]))	
			{
				tab[coord.x - 1][coord.y] = 50; 
				trovato = true;
			}			
				
		//destra
			if(controlla_mossa(coord.right(), tab, tab[coord.x][coord.y]))
			{
				tab[coord.x][coord.y + dim2] = 51; 
				trovato = true;
			}
		//giù
			if(controlla_mossa(coord.down(), tab, tab[coord.x][coord.y]))
			{
				tab[coord.x + dim1][coord.y] = 52; 
				trovato = true;
			}
		//sinistra	
			if(controlla_mossa(coord.left(), tab, tab[coord.x][coord.y]))
			{
				tab[coord.x][coord.y - 1] = 53; 
				trovato = true;
			}
		return trovato;
	}
}
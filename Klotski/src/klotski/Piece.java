package klotski;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Piece 
{
	int dim1;
	int dim2;
	coord coord = new coord(99,99);
	private Rectangle rectangle;
	
	public Piece(int y, int x)
	{
		dim1 = x; 
		dim2 = y;	
	}
	
	public void reset()
	{
		coord.x=99;
		coord.y=99;
	}
	
	 public Rectangle getRectangle() 
	 {
	        return rectangle;
	 }

    public int getX() 
    {
        return coord.x;
    }

    public int getY()
    {
        return coord.y;
    }
		
	
	public boolean controlla_mossa(mossa m, int [][] tab)
	{
		
			
		if (m.dir == 'U')
			m.c_in = m.c_in.up();
		else if(m.dir == 'R')
			m.c_in = m.c_in.right();
		else if(m.dir == 'D')
			m.c_in = m.c_in.down();
		else if(m.dir == 'L')
			m.c_in = m.c_in.left();
		
		//controllo se si fa una mossa valida o se è la prima mossa

			if (coord.x != 99)
			{
				int diff1 = coord.x - m.c_in.x;
			
				int diff2 = coord.y - m.c_in.y;;
				
				if (diff1 < 0)
					diff1 = -diff1;
			
				if (diff2 < 0)
					diff2 = -diff2;					
				
				if (diff1 + diff2 != 1)
					return false;
			}

			
			//controllo se la possa è plausibile
			if ((tab.length < m.c_in.x + dim1) || (tab[0].length < m.c_in.y+dim2))
			{
				return false;
			}
			

			if((m.c_in.x < 0) || (m.c_in.y < 0))
			{
				return false;
			}
			//controllo se dove voglio posizionarmi c'è spazio
			for (int i=m.c_in.x; i<m.c_in.x+dim1; i++)
				for (int j=m.c_in.y; j<m.c_in.y+dim2; j++)
					if ((tab[i][j] != 0) && (tab[i][j] != m.num_pezzo))				
						return false;
		return true;
	}
	
	
	
	public boolean imposta_coord(mossa M, int [][] tab)
	{
		// muovo pezzo
		//modifico coordinate
		
		mossa m = new mossa(M.c_in, M.dir, M.num_pezzo);
		
		
		if (controlla_mossa(m, tab) == false)
		{
			return false;
		}

		if (m.dir == 'U')
			coord = coord.up();
		else if(m.dir == 'R')
			coord = coord.right();
		else if(m.dir == 'D')
			coord = coord.down();
		else if(m.dir == 'L')
			coord = coord.left();

		if (coord.x == 99)
		{
			coord = m.c_in;
		}
		
		//cancello pezzo se già presente
		for (int i=0; i<tab.length; i++)
			for (int j=0; j<tab[0].length; j++)
				if (tab[i][j] == m.num_pezzo)
					tab[i][j] = 0;
		
		for (int i=coord.x; i<coord.x + dim1; i++)
			for (int j=coord.y; j<coord.y + dim2; j++)
				tab[i][j] = m.num_pezzo;
			
		return true;	
	}
	

	public boolean controlla_mosse(int [][] tab)
	{
			boolean trovato = false;
		//su
			if(controlla_mossa(new mossa(coord, 'U', tab[coord.x][coord.y] ), tab))	
			{
				tab[coord.x - 1][coord.y] = 50; 
				trovato = true;
			}			
				
		//destra
			if(controlla_mossa(new mossa(coord, 'R', tab[coord.x][coord.y] ), tab))
			{
				tab[coord.x][coord.y + dim2] = 51; 
				trovato = true;
			}
		//giù
			if(controlla_mossa(new mossa(coord, 'D', tab[coord.x][coord.y] ), tab))
			{
				tab[coord.x + dim1][coord.y] = 52; 
				trovato = true;
			}
		//sinistra	
			if(controlla_mossa(new mossa(coord, 'L', tab[coord.x][coord.y] ), tab))
			{
				tab[coord.x][coord.y - 1] = 53; 
				trovato = true;
			}
		return trovato;
	}
}
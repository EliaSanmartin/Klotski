package klotski;

public class Shape 
{
	//dimensine pezzo
	private int dim1;
	private int dim2;
	
	//coordinate del pezzo
	private Coord coord = new Coord(99,99);

	
	//costruttore
	public Shape(int y, int x)
	{
		dim1 = x; 
		dim2 = y;	
	}
	
	public Shape(int y, int x, Coord c)
	{
		dim1 = x; 
		dim2 = y;	
		coord.setX(c.getY());
		coord.setY(c.getX());
	}
	
	
	public Coord getCoord() 
	{
	    return coord;
	}

    public int getDim1()
    {
        return dim1;
    }
    
    public int getDim2()
    {
        return dim2;
    }
	
    //controlla se la mossa proposta è possibile
	public boolean controlla_mossa(Mossa m, int [][] tab)
	{
		
		//controllo se si fa una mossa valida o se è la prima mossa

			if (coord.getX() != 99)//se il pezzo è gia nella griglia
			{
				int diff1 = coord.getX() - m.c_fin.getX();//differenza asse x
			
				int diff2 = coord.getY() - m.c_fin.getY();// differenza asse y
				
				if (diff1 < 0)
					diff1 = -diff1;
			
				if (diff2 < 0)
					diff2 = -diff2;					
				
				if (diff1 + diff2 != 1)//se fa più di una mossa
					return false;
			}

			
			//controllo se esce dalla griglia
			if ((tab.length < m.c_fin.getX() + dim1) || (tab[0].length < m.c_fin.getY()+dim2))
			{
				return false;
			}
			
			//controllo se coordinate siano positive
			if((m.c_fin.getX() < 0) || (m.c_fin.getY() < 0))
			{
				return false;
			}
			
			//controllo se dove voglio posizionarmi c'è spazio
			for (int i=m.c_fin.getX(); i<m.c_fin.getX()+dim1; i++)
				for (int j=m.c_fin.getY(); j<m.c_fin.getY()+dim2; j++)
					if ((tab[i][j] != 0) && (tab[i][j] != m.num_pezzo))				
						return false;
			
		return true;//se tutto va bene restituisci true
	}
	
	
	//dando per scontato che la mossa sia possibile esegue tal mossa (cambia coordinate e matrice)
	public short imposta_coord(Mossa M, int [][] tab)
	{
	
		// muovo pezzo
		//modifico coordinate
		
		Mossa m = new Mossa(M.c_in, M.dir, M.num_pezzo);
		
	
		coord = m.c_fin;
		
		
		//cancello pezzo se già presente
		for (int i=0; i<tab.length; i++)
			for (int j=0; j<tab[0].length; j++)
				if (tab[i][j] == m.num_pezzo)
					tab[i][j] = 0;
		
		//posiziono il pezzo nella griglia
		for (int i=coord.getX(); i<coord.getX() + dim1; i++)
			for (int j=coord.getY(); j<coord.getY() + dim2; j++)
				tab[i][j] = m.num_pezzo;
		
		if(m.num_pezzo == 1)
		{
			if(m.c_fin.compare(new Coord(1,3)))
			{
				return 1;
			}
		}
			
		return 0;	
	}
	
}
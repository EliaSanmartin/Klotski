package klotski;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Piece 
{
	//dimensine pezzo
	private int dim1;
	private int dim2;
	
	//coordinate del pezzo
	private Coord coord = new Coord(99,99);
	private Rectangle rectangle;
	
	//costruttore
	public Piece(int y, int x)
	{
		dim1 = x; 
		dim2 = y;	
	}
	
	public Piece(int y, int x, Coord c)
	{
		dim1 = x; 
		dim2 = y;	
		coord.setX(c.getY());
		coord.setY(c.getX());
	}
	
	//posiziona pezzo a coordinate impossibili
	public void reset()
	{
		coord.setX(99);
		coord.setY(99);
	}
	
	 public Rectangle getRectangle() 
	 {
	        return rectangle;
	 }
	 
	 public Coord getCoord() 
    {
        return coord;
    }
	 
    public int getX() 
    {
        return coord.getX();
    }

    public int getY()
    {
        return coord.getY();
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
				int diff1 = coord.getX() - m.getCfin().getX();//differenza asse x
			
				int diff2 = coord.getY() - m.getCfin().getY();// differenza asse y
				
				if (diff1 < 0)
					diff1 = -diff1;
			
				if (diff2 < 0)
					diff2 = -diff2;					
				
				if (diff1 + diff2 != 1)//se fa più di una mossa
					return false;
			}

			
			//controllo se esce dalla griglia
			if ((tab.length < m.getCfin().getX() + dim1) || (tab[0].length < m.getCfin().getY()+dim2))
			{
				return false;
			}
			
			//controllo se coordinate siano positive
			if((m.getCfin().getX() < 0) || (m.getCfin().getY() < 0))
			{
				return false;
			}
			
			//controllo se dove voglio posizionarmi c'è spazio
			for (int i=m.getCfin().getX(); i<m.getCfin().getX()+dim1; i++)
				for (int j=m.getCfin().getY(); j<m.getCfin().getY()+dim2; j++)
					if ((tab[i][j] != 0) && (tab[i][j] != m.getNp()))				
						return false;
			
		return true;//se tutto va bene restituisci true
	}
	
	
	//dando per scontato che la mossa sia possibile esegue tal mossa (cambia coordinate e matrice)
	public short imposta_coord(Mossa M, int [][] tab)
	{
	
		// muovo pezzo
		//modifico coordinate
		
		Mossa m = new Mossa(M.getCin(), M.getDir(), M.getNp());
		
	
		coord = m.getCfin();
		
		
		//cancello pezzo se già presente
		for (int i=0; i<tab.length; i++)
			for (int j=0; j<tab[0].length; j++)
				if (tab[i][j] == m.getNp())
					tab[i][j] = 0;
		
		//posiziono il pezzo nella griglia
		for (int i=coord.getX(); i<coord.getX() + dim1; i++)
			for (int j=coord.getY(); j<coord.getY() + dim2; j++)
				tab[i][j] = m.getNp();
		
		if(m.getNp() == 1)
		{
			if(m.getCfin().compare(new Coord(1,3)))
			{
				return 1;
			}
		}
			
		return 0;	
	}
	
}
package klotski;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

// pattern che incapsula l'oggetto (singleton)
// manipoli l'array tramite le chiamate alla classe ed i metodi al posto di accedere direttamente all'array
//tvb
public class Level {
	
    private static Color colorBigSquare = Color.CORAL;
    private static Color colorNormalSquare = Color.LIGHTSEAGREEN;
    
	private List<Piece> pieces = new ArrayList<>();	
	private ArrayList<GeometricShape> rectangles = new ArrayList<>();
	
	private int level;
	private int [][] mat;
	//costruttore
	public Level(int l, int r, int c)
	{
		mat = new int[r][c];
		level = l;
		set_level();
	}
	
	public void set_level()
	{
		pieces.clear();
		rectangles.clear();
		
		//svuoto la matrice
		for(int i=0;i<mat.length;i++)
			for(int j=0;j<mat[0].length;j++)
				mat[i][j] = 0;
		
		
		
		
		switch(level) {
		 case 1:
		   // primo livello
			 
			 pieces.add(new Piece(2,2));
			 pieces.add(new Piece(2,1));
			 for(int i=0; i<4;i++)
				 pieces.add(new Piece(1,2));
			 for(int i=0; i<4;i++)
				 pieces.add(new Piece(1,1));
			 
			 
			 
		 	pieces.get(0).imposta_coord(new mossa(new Coord(1, 0),'N', 1), mat);
			pieces.get(1).imposta_coord(new mossa(new Coord(1, 2),'N', 2), mat);
			pieces.get(2).imposta_coord(new mossa(new Coord(0, 0),'N', 3), mat);
			pieces.get(3).imposta_coord(new mossa(new Coord(3, 0),'N', 4), mat);
			pieces.get(4).imposta_coord(new mossa(new Coord(3, 2),'N', 5), mat);
			pieces.get(5).imposta_coord(new mossa(new Coord(0, 2),'N', 6), mat);
			pieces.get(6).imposta_coord(new mossa(new Coord(0, 4),'N', 7), mat);
			pieces.get(7).imposta_coord(new mossa(new Coord(1, 3),'N', 8), mat);
			pieces.get(8).imposta_coord(new mossa(new Coord(2, 3),'N', 9), mat);
			pieces.get(9).imposta_coord(new mossa(new Coord(3, 4),'N', 10), mat);
			
		
		    break;
	    
		  case 2:
		    // secondo livello
			  pieces.add(new Piece(2,2));
				 pieces.add(new Piece(2,1));
				 for(int i=0; i<4;i++)
					 pieces.add(new Piece(1,2));
				 
				 
				 
			 	pieces.get(0).imposta_coord(new mossa(new Coord(1, 0),'N', 1), mat);
				pieces.get(1).imposta_coord(new mossa(new Coord(1, 2),'N', 2), mat);
				pieces.get(2).imposta_coord(new mossa(new Coord(0, 0),'N', 3), mat);
				pieces.get(3).imposta_coord(new mossa(new Coord(3, 0),'N', 4), mat);
				pieces.get(4).imposta_coord(new mossa(new Coord(3, 2),'N', 5), mat);
				pieces.get(5).imposta_coord(new mossa(new Coord(0, 2),'N', 6), mat);
				
				

			    break;
			
		  case 3:
			// terzo livello
			  // secondo livello
			  pieces.add(new Piece(1,1));
			  pieces.add(new Piece(1,2));
			  pieces.add(new Piece(1,1));
			  pieces.add(new Piece(1,1));
				 
				 
			 	pieces.get(0).imposta_coord(new mossa(new Coord(1, 0),'N', 1), mat);
			 	pieces.get(1).imposta_coord(new mossa(new Coord(1, 1),'N', 2), mat);
			 	pieces.get(2).imposta_coord(new mossa(new Coord(0, 2),'N', 3), mat);
			 	pieces.get(3).imposta_coord(new mossa(new Coord(2, 2),'N', 4), mat);
			
		    break;
		    
		  default:
			 System.out.println("Qualcosa è andato storto.");
			 return;
		}
		Coord c = pieces.get(0).getCoord();
		rectangles.add(new GeometricShape(pieces.get(0).getDim1()*100, pieces.get(0).getDim2()*100, pieces.get(0).getY()*100 , pieces.get(0).getX()*100, Color.CORAL, 1)); 
		for(int i=1; i<pieces.size();i++)
			rectangles.add(new GeometricShape(pieces.get(i).getDim1()*100, pieces.get(i).getDim2()*100, pieces.get(i).getY()*100 , pieces.get(i).getX()*100, Color.LIGHTSEAGREEN, i+1));
	}
	
	public List<Piece> getPieces()
	{
		return pieces;
	}
	
	public ArrayList<GeometricShape> getRectangles()
	{
		return rectangles;
	}
	
	public int [][] getGrid()
	{
		return mat;
	}
	 
	
	public int getSize() 
	{
		return rectangles.size();
	}
		

}

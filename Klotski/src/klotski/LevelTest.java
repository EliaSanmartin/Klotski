package klotski;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;



public class LevelTest {
    
	private List<Shape> pieces = new ArrayList<>();	
	private ArrayList<GeometricShape> rectangles = new ArrayList<>();
	
	private int level;
	private int [][] mat;
	//costruttore
	public LevelTest(int level, int r, int c)
	{
		mat = new int[r][c];
		this.level = level;
		set_level();
	}
	
	
	
	
	private void set_level()
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
			 mat = new int [][] {
				  {2, 1, 1, 4},
				  {3, 1, 1, 5},
				  {9, 8, 7, 6},
				  {10, 11, 12, 13},
				  {15, 0, 0, 14}};
		    break;
	    
		  case 2:
			  mat = new int [][] {
				  {3, 3, 3, 3},
				  {6, 1, 1, 4},
				  {6, 2, 13, 5},
				  {7, 8, 9, 12},
				  {11, 0, 0, 10}};
			    break;
			
		  case 3:
			  mat = new int [][] {
				  {0, 1, 1, 5},
				  {2, 1, 1, 4},
				  {3, 0, 0, 4},
				  {6, 0, 0, 8},
				  {7, 0, 0, 8}};
		    break;
		    
		  default:
			 System.out.println("Qualcosa � andato storto.");
			 return;
		}
			
		int num = 1;
		int dim1=1;
		int dim2=1;
		Coord c = new Coord(99,99);
		
		while(true)
		{
			c.setX(99);
			c.setY(99);
			
			dim1=1;
			dim2=1;
			for(int i=0;i<mat.length;i++)
			{
				for(int j=0;j<mat[0].length;j++)
					if(mat[i][j] == num)
					{
						c.setX(j);
						c.setY(i);
						break;
					}
				if (c.getX() != 99)
					break;
			}
			
			if (c.getX() == 99)
				break;
			
			
			for(int i=1;i<mat.length-c.getY();i++)
				if(mat[i+c.getY()][c.getX()] == num)
					dim1 ++;
				else
					break;
			for(int i=1;i<mat[0].length - c.getX();i++)
				if(mat[c.getY()][c.getX()+i] == num)
					dim2 ++;
				else
					break;
			
			
			pieces.add(new Shape(dim2, dim1, c));
			Color col = Color.LIGHTSEAGREEN;//CORAL;
			if (num == 1)
				{
					col = Color.CORAL;
					if(level == 2)
						col = Color.BLUEVIOLET;
					else if(level == 3)
						col = Color.CRIMSON;
				}
			
			rectangles.add(new GeometricShape(dim1*100, dim2*100, c.getX()*100 , c.getY()*100, col, num));
			num ++;
		}
	}
	
	public List<Shape> getPieces()
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
	 
	

		

}

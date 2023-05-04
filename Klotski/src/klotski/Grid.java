package klotski;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Grid 
{
	final private int righe = 5;
	final private int colonne = 4;
	
	private double mouseX;
    private double mouseY;
    
    private int gridSize = 100;
	
    private int id_old = 0;
    
	int num_mosse = 0;
	
	int [][] mat = new int[righe][colonne];
	
	Level l = new Level(2, righe, colonne);
	
	ArrayList<GeometricShape> rectangles = new ArrayList<>();
	List<Piece> pezzi = new ArrayList<>();
	List<mossa> mosse = new ArrayList<>();
	
	FileS salvataggio = new FileS("salvataggi/prova.txt");	
	
	@FXML
    private Text moves;
	
	public Grid(Text m)
	{
		salvataggio.contenuto.set(0, "X,0");//formazione e num_mosse
		this.moves = m;
		imposta_formazione(1);
		
	}
	
	public Grid()
	{
		salvataggio.contenuto.set(0, "X,0");//formazione e num_mosse
		imposta_formazione(1);
		
	}	
	
	//resetta le grid e imposta una nuova formazione
	public void imposta_formazione(int formazione)
	{
		salvataggio.imposta_formazione(formazione);
		
		pezzi.clear();
		//svuoto la matrice
		for(int i=0;i<mat.length;i++)
			for(int j=0;j<mat[0].length;j++)
				mat[i][j] = 0;

		num_mosse = 0;//resetto numero mosse
		//-----------reset di tutti i pezzi------------
		for(int i=0;i<pezzi.size();i++)
			pezzi.get(i).reset();
		//---------------------------------------------
		l = new Level(formazione, righe, colonne);
		pezzi = l.getPieces();
		mat = l.getGrid();
		rectangles = l.getRectangles();
		
	}


	
	//Stampa grid su terminale
	public void print()
	{
				
		for(int i=0;i<mat.length ;i++)
		{
			System.out.print("\n\n|\t");
			for(int j=0;j<mat[0].length;j++)
			{
				System.out.print(mat[i][j]);
				System.out.print("\t-\t");
			}	
		}
		System.out.print("\n\n");
	}
		
	
	//--------funzione per eseguire una mossa indietro---------
	public void undo()
	{
		
		if(mosse.size() != 0)
		{
			
			mossa m = mosse.get(mosse.size()-1).reverse();//trovo la mossa contraria all'ultima fatta
			GeometricShape r = rectangles.get(m.num_pezzo - 1);//trovo quadrato da muovere
			Node node = r.getRectangle();

			//-----sposto quadrato-----
			node.setLayoutX(m.c_fin.getY()*100 - r.getX());
			node.setLayoutY(m.c_fin.getX()*100 - r.getY());

			//-----esegui mossa-----
			pezzi.get(m.num_pezzo-1).imposta_coord(m, mat);

			mosse.remove(mosse.size()-1);
		
			if(id_old != r.getID() - 1)//se si cambia il blocco spostato
        	{
        		id_old = r.getID() - 1;//segna il blocco nuovo che � stato spostato
				add_move();
        	}
		}
	}
	
	
	//----------------------------------------------------------
	public void drag(GeometricShape rectangle){
        
    	Node node = rectangle.getRectangle();
        node.setOnMouseDragged(mouseEvent -> {
        	
            mouseX = mouseEvent.getSceneX()/gridSize;	//trovo coord x del mouse
            mouseY = mouseEvent.getSceneY()/gridSize;   //trovo coord y del mouse
            
            Coord c1 = pezzi.get(rectangle.getID() - 1).getCoord();//coordinata iniziale
            
            int x = (int) (mouseX % colonne)*gridSize;//trovo la casella in cui voglio muovermi
            int y = (int) (mouseY % righe)*gridSize;
            
            int dim1 = pezzi.get(rectangle.getID() - 1).getDim1();
            int dim2 = pezzi.get(rectangle.getID() - 1).getDim2();
            
            Coord c2 = new Coord(x/100, y/100);//coordinata finale
            
            if (dim2 == 2)// se il pezzo ha dimensione 2
            {
            	if(c1.getY() == (c2.getY()-1))//controllo se si � selezionato la coordinata iniziale del blocco
            	{
            		if(c1.getX() != c2.getX())//se non si vuole muovere a destra e a sinistra
            		{
            			c2.setY(c1.getY());//imposto coordinata y finale uguale a quella iniziale 
                		x -= 100;	
            		}
            		
            	}
            }
            
            if (dim1 == 2)
            {
            	if(c1.getX() == (c2.getX()-1))
            	{
            		if(c1.getY() != c2.getY())
            		{
            			c2.setX(c1.getX());
                		y -= 100;	
            		}
               	}
            }
            
            //controllo se la mossa che vuole fare � fattibile
            boolean check = pezzi.get(rectangle.getID() - 1).controlla_mossa(new mossa(c1, c2, rectangle.getID()), mat);
            //la condizione � true solo se � possibile fare la mossa 
             
            if(check)//se la mossa � possibile
            {
            	mosse.add(new mossa(c1, c2, rectangle.getID()));//aggiungo a lista mosse
      	
            	//sposto il rettangolo graficamente
            	node.setLayoutX(x - rectangle.getX());
            	node.setLayoutY(y - rectangle.getY());
            	
            	//sposto il blocco nel "programma" 
            	pezzi.get(rectangle.getID() - 1).imposta_coord(mosse.get(mosse.size() - 1), mat);
            	
            	print();
            	
            	if(id_old != rectangle.getID() - 1)//se si cambia il blocco spostato
            	{
            		id_old = rectangle.getID() - 1;//segna il blocco nuovo che � stato spostato
					add_move();

					salvataggio.add(mosse.get(mosse.size()-1));//aggiungi mossa al file
            	}
            }
        });
    }
    
	public int getNumMove()
	{
		return num_mosse;
	}
	private void add_move()
	{
		num_mosse += 1;	//aumenta variabile numero di mosse di uno
		moves.setText("Moves: "+num_mosse);
		System.out.print(num_mosse +"\n");
		
		//modifico salvataggio che sar� il contenuto del file dove verr� salvata la partita
		salvataggio.add_mossa(num_mosse);
		
	}

}

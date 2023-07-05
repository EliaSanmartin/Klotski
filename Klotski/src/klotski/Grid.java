package klotski;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Grid 
{
	private boolean test=false;
	
	
	private final int righe = 5;
	private final int colonne = 4;
	
	//-------Posizione del mouse------
	private short victory = 0;
    private int gridSize = 100;//dimensione quandrato in javafx
	
    private int id_old = 0;//in questa variabile viene salvato l'id del pezzo mosso per sapere quando si muove un altro pezzo
    
    private int num_mosse = 0;//numero mosse
	
    private int [][] mat = new int[righe][colonne];//Griglia che dice come sono posizionati i pezzi
	
	private ArrayList<GeometricShape> rectangles = new ArrayList<>();//pezzi per la parte grafica
	private List<Shape> pezzi = new ArrayList<>();//pezzi per la parte dei controlli
	private List<Mossa> mosse = new ArrayList<>();//tutte le mosse fatte fino ad ora per tornare indietro e per salvarle

	private String nome_file;//dove verr� salvato il file

    private FileS salvataggio;//in questa classe verranno salvate le mosse e le aggiunger� ad un file
	
	@FXML
    private Text moves;//stampa il numero di mosse
	
	/*-----------------------Costruttore----------------------*/
	public Grid(Text m)
	{
		//------------Trovo il nome del file in base alla data e ora-----------
		LocalTime x = LocalTime.now();
		String s = x.getHour()+"-"+x.getMinute();
		LocalDate y = LocalDate.now();
		s = s +"_"+y.getDayOfMonth() + "-"+ y.getMonth() + "-"+y.getYear() ;
		//---------------------------------------------------------------------
		
		salvataggio = new FileS("salvataggi//"+ s + ".txt");
		salvataggio.setContenuto(0, "X,0");//formazione e num_mosse
		this.moves = m;
		
		imposta_formazione(1);//imposto il livello uno di defoult
	}
	/*----------------------------------------------------------------------*/
	public Mossa getMossaF()
	{
		return mosse.get(mosse.size() - 1);
	}
	public void addMossa(Mossa m)
	{
		mosse.add(m);
	}
	public ArrayList<GeometricShape> getRectangles()
	{
		return rectangles;
	}
	public List<Shape> getPezzi()
	{
		return pezzi;
	}
	public int [][] getMat()
	{
		return mat;
	}
	public short getVictory()
	{
		return victory;
	}

	public int getId_old()
	{
		return id_old;
	}

	public void setId_old(int i)
	{
		id_old = i;
	}
	public void setVictory(short v)
	{
		victory = v;
	}
	public int getRow()
	{
		return righe;
	}

	public int getColonne()
	{
		return colonne;
	}
	public void addSalvataggio(Mossa m)
	{
		salvataggio.add(m);
	}

	//---Crea salvataggio---
	public void save_game()
	{
		salvataggio.crea_file();//creo o aggiusto il file per il salvataggio
	}
	//----------------------
	
	//----resetta le grid e imposta una nuova formazione----
	public void imposta_formazione(int formazione)
	{
		salvataggio.imposta_formazione(formazione);//cambio formazione nel file per il salvataggio
		
		//-------------------cancello pezzi vecchi--------------------
		pezzi.clear();
		rectangles.clear();
		//svuoto la matrice
		for(int i=0;i<mat.length;i++)
			for(int j=0;j<mat[0].length;j++)
				mat[i][j] = 0;

		num_mosse = 0;//resetto numero mosse
		
		//trovo nuovi pezzi e nuova formazione
		LevelTest lt = new LevelTest(formazione, righe, colonne);
		Level l = new Level(formazione, righe, colonne);
		
		if(test==true)
		{
			pezzi = lt.getPieces();
			mat = lt.getGrid();
			rectangles = lt.getRectangles();
		}
		else {
			pezzi = l.getPieces();
			mat = l.getGrid();
			rectangles = l.getRectangles();
		}
	}

	//--------------Converti la matrice in una stringa--------
	public String toString()
	{
		String s = "";
		int id=0;
		
		for(int i=0; i<righe; i++)
		{
			for(int j=0; j<colonne; j++)
			{
				id = mat[i][j]-1;
				if (id == -1)
					s=s+"0";
				else if((pezzi.get(id).getDim1() == 1) && ((pezzi.get(id).getDim2() == 1)))
					s = s+"4";
				else if((pezzi.get(id).getDim1() == 2) && ((pezzi.get(id).getDim2() == 1)))
					s = s + "2";
				else if((pezzi.get(id).getDim1() == 2) && ((pezzi.get(id).getDim2() == 2)))
					s = s + "1";
				else if((pezzi.get(id).getDim1() == 1) && ((pezzi.get(id).getDim2() == 2)))
					s = s + "3";
			}
		}
		return s;
	}

	//----------------------Cerca ed esegui miglior mossa---------------------------
	public void next_move() throws IOException
	{
		
		FileS all_moves = new FileS("best_move//move.txt");//prendo tutte le migliori mosse dal file
		
		Mossa m = all_moves.find_move(this.toString());//cerco la mossa migliore
		
		if(m.getDir() == 'N')//se non l'ha trovata
		{

			Stage stage = (Stage)(moves.getScene().getWindow());
			PrintMessage.showMessage(stage , "Mossa non trovata!");
			return;//fine funzione
		}
		//se ha trovato la mossa
		m.setNp(mat[m.getCin().getX()][m.getCin().getY()]);//seleziono il pezzo da muovere
		
		salvataggio.add(m);//aggiungi mossa al file
		mosse.add(m);//aumenta numero mosse
		
		GeometricShape r = rectangles.get(m.getNp() - 1);//trovo quadrato da muovere
		
		Node node = r.getRectangle();

		r = rectangles.get(m.getNp() - 1);//trovo quadrato da muovere
		node = r.getRectangle();
		//-----sposto quadrato-----
		
		node.setLayoutX(m.getCfin().getY()*gridSize - r.getX());
		node.setLayoutY(m.getCfin().getX()*gridSize - r.getY());
		if(id_old != r.getID() - 1)//se si cambia il blocco spostato
    	{
    		id_old = r.getID() - 1;//segna il blocco nuovo che � stato spostato
			add_move();
    	}
		victory = pezzi.get(m.getNp()-1).imposta_coord(m, mat);//fai la mossa
		if (victory == 1)
		{
			victory = 2;
			victory();
		}
			
	}
	
	
	//--------funzione per eseguire una mossa indietro---------
	public void undo()
	{
		if(mosse.size() != 0)
		{
			Mossa m = mosse.get(mosse.size()-1).reverse();//trovo la mossa contraria all'ultima fatta
			GeometricShape r = rectangles.get(m.getNp() - 1);//trovo quadrato da muovere
			Node node = r.getRectangle();

			//-----sposto quadrato-----
			node.setLayoutX(m.getCfin().getY()*gridSize - r.getX());
			node.setLayoutY(m.getCfin().getX()*gridSize - r.getY());

			//-----esegui mossa-----
			victory = pezzi.get(m.getNp()-1).imposta_coord(m, mat);

			mosse.remove(mosse.size()-1);
		
			if(id_old != r.getID() - 1)//se si cambia il blocco spostato
        	{
        		id_old = r.getID() - 1;//segna il blocco nuovo che � stato spostato
				add_move();
        	}
		}
	}
	

	public void resumegames(FileS file)
	{
		Node node;// = rectangle.getRectangle();
		GeometricShape r;
		salvataggio = file;//se devo salvo il file lo salvo sullo stesso file
		num_mosse = file.getNumMove();//capisco quante mosse sono state fatte
		mosse.clear();//cancello tutte le mosse fatte fino ad ora
		
		for(int i=1;i<file.getContenuto().size();i++)
		{			
			Mossa m = file.get_move(i);//ottengo la mossa fatta
			mosse.add(m);
			
			r = rectangles.get(m.getNp() - 1);//trovo quadrato da muovere
			node = r.getRectangle();
			//-----sposto quadrato-----
			
			node.setLayoutX(m.getCfin().getY()*gridSize - r.getX());
			node.setLayoutY(m.getCfin().getX()*gridSize - r.getY());
			
			victory = pezzi.get(m.getNp()-1).imposta_coord(m, mat);
			
		}
	}

	protected void victory() throws IOException
	{
		Stage stage;
		Scene scene;
		Parent root = FXMLLoader.load(getClass().getResource("Victory.fxml"));
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public int getNumMove()
	{
		return num_mosse;
	}
	
	protected void add_move()
	{
		num_mosse += 1;	//aumenta variabile numero di mosse di uno
		moves.setText("Moves: "+num_mosse);
		
		//modifico salvataggio che sara' il contenuto del file dove verr� salvata la partita
		salvataggio.add_mossa(num_mosse);
	}
}

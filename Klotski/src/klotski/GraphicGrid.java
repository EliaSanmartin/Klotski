package klotski;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class GraphicGrid extends Grid{

	public GraphicGrid(Text m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	private double mouseX;
    private double mouseY;
  
    private int gridSize = 100;
    
    private AnchorPane gameViewer;

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
              
              Coord c2 = new Coord(x/gridSize, y/gridSize);//coordinata finale
              
              if (dim2 == 2)// se il pezzo ha dimensione 2
              {
              	if(c1.getY() == (c2.getY()-1))//controllo se si è selezionato la coordinata iniziale del blocco
              	{
              		if(c1.getX() != c2.getX())//se non si vuole muovere a destra e a sinistra
              		{
              			c2.setY(c1.getY());//imposto coordinata y finale uguale a quella iniziale 
                  		x -= gridSize;	
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
                  		y -= gridSize;	
              		}
                 	}
              }
              
              //controllo se la mossa che vuole fare è fattibile
              boolean check = pezzi.get(rectangle.getID() - 1).controlla_mossa(new Mossa(c1, c2, rectangle.getID()), mat);
              //la condizione è true solo se è possibile fare la mossa 
              if(victory != 0)
              	check = false;
              if(check)//se la mossa è possibile
              {
              	mosse.add(new Mossa(c1, c2, rectangle.getID()));//aggiungo a lista mosse
              	
              	//sposto il rettangolo graficamente
              	node.setLayoutX(x - rectangle.getX());
              	node.setLayoutY(y - rectangle.getY());
              	
              	//sposto il blocco nel "programma" 
              
  					victory = pezzi.get(rectangle.getID() - 1).imposta_coord(mosse.get(mosse.size() - 1), mat);
  			
              	salvataggio.add(mosse.get(mosse.size()-1));//aggiungi mossa al file
                  
              	
              	if(id_old != rectangle.getID() - 1)//se si cambia il blocco spostato
              	{
              		id_old = rectangle.getID() - 1;//segna il blocco nuovo che è stato spostato
  					add_move();

              	}

              }
              if (victory == 1)
              {
              	victory = 2;
      			try {
      				victory();
      			} catch (IOException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
              }
          });
          
      }
   
    
    public AnchorPane getGameViewer() {
        return gameViewer;
    }
}
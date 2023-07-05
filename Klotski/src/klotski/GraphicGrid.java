package klotski;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

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
              
              Coord c1 = getPezzi().get(rectangle.getID() - 1).getCoord();//coordinata iniziale
              
              int x = (int) (mouseX % getColonne())*gridSize;//trovo la casella in cui voglio muovermi
              int y = (int) (mouseY % getRow())*gridSize;
              
              int dim1 = getPezzi().get(rectangle.getID() - 1).getDim1();
              int dim2 = getPezzi().get(rectangle.getID() - 1).getDim2();
              
              Coord c2 = new Coord(x/gridSize, y/gridSize);//coordinata finale

              if ((dim2 == 2) && (dim1 == 1))// se il pezzo ha dimensione 2
              {
              	if(c1.getY() == (c2.getY()-1))//controllo se si � selezionato la coordinata iniziale del blocco
              	{
              		if(c1.getX() != c2.getX())//se non si vuole muovere a destra e a sinistra
              		{
              			c2.setY(c1.getY());//imposto coordinata y finale uguale a quella iniziale 
                  		x -= gridSize;
              		}
              		
              	}
              }
              
              if((dim1 == 2) && (dim2 == 1))
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

			  if ((dim2 == 2) && (dim1 == 2))// se il pezzo ha dimensione 2
			  {
                  if((c1.getY() != c2.getY())&&(c1.getX() != c2.getX()))
                  {
                      if (c1.getY() < c2.getY() - 1) {
                          c2.setX(c1.getX());
                          c2.setY(c1.getY()+1);
                          x += gridSize * (c1.getX() - c2.getX() - 1);
                          y += gridSize * (c1.getY() - c2.getY());
                      }

                      else if (c1.getY() > c2.getY()) {
                          c2.setX(c1.getX());
                          c2.setY(c1.getY() - 1);
                          x += gridSize * (c1.getX() - c2.getX());
                          y += gridSize * (c1.getY() - c2.getY() - 2);
                      }
                      else if (c1.getX() < c2.getX() - 1) {


                          c2.setX(c1.getX() + 1);
                          c2.setY(c1.getY());
                          x += gridSize * (c1.getX() - c2.getX());
                          y += gridSize * (c1.getY() - c2.getY() - 1);
                      }

                      else if (c1.getX() > c2.getX()) {

                          c2.setX(c1.getX() - 1);
                          c2.setY(c1.getY());
                          x += gridSize * (c1.getX() - c2.getX() - 2);
                          y += gridSize * (c1.getY() - c2.getY());
                      }
                  }
			  }




              //controllo se la mossa che vuole fare � fattibile
              boolean check = getPezzi().get(rectangle.getID() - 1).controlla_mossa(new Mossa(c1, c2, rectangle.getID()), getMat());
              //la condizione � true solo se � possibile fare la mossa 
              if(getVictory() != 0)
              	check = false;
              if(check)//se la mossa � possibile
              {
              	addMossa(new Mossa(c1, c2, rectangle.getID()));//aggiungo a lista mosse
              	
              	//sposto il rettangolo graficamente
              	node.setLayoutX(x - rectangle.getX());
              	node.setLayoutY(y - rectangle.getY());
              	
              	//sposto il blocco nel "programma" 
              
  					setVictory(getPezzi().get(rectangle.getID() - 1).imposta_coord(getMossaF(), getMat()));
  			
              	addSalvataggio(getMossaF());//aggiungi mossa al file
                  
              	
              	if(getId_old() != rectangle.getID() - 1)//se si cambia il blocco spostato
              	{
              		setId_old(rectangle.getID() - 1);//segna il blocco nuovo che � stato spostato
  					add_move();

              	}

              }
              if (getVictory() == 1)
              {
              	setVictory((short) 2);
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
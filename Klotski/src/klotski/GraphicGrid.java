package klotski;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class GraphicGrid {

	private double mouseX;
    private double mouseY;
  
    private int gridWidth = 4; //numero di quadratini in larghezza
    private int gridHeight = 5; //numero di blocchi in altezza
    private int gridSize = 100;
    
    private AnchorPane gameViewer;

    public GraphicGrid(AnchorPane gameViewer) {
      
        this.gameViewer = gameViewer;
    }
    
    public void drag(GeometricShape rectangle){
        
    	Node node = rectangle.getRectangle();
        node.setOnMouseDragged(mouseEvent -> {
        	
            mouseX = mouseEvent.getSceneX()/gridSize;
            mouseY = mouseEvent.getSceneY()/gridSize;   
            coord c1 = new coord(rectangle.getX()/100, rectangle.getY()/100);
            int x = (int) (mouseX % gridWidth)*gridSize;
            int y = (int) (mouseY % gridHeight)*gridSize;
            coord c2 = new coord(x/100, y/100);
            
            //parte di codice da modificare per Elia x e y coorispondono cordinate pixel di 100 in 100 
            //quindi basta dividerle per 100 se vuoi avere le cordinate tipo 1,0
            
            
            //mettere nella condizione se è possibile fare la mossa 
            //e in caso sia possibile il quadratino può essere trascinato 
            if(true){
            	System.out.println("Id retangolo ");
            	System.out.println("Griglia x " + x/100);
            	System.out.println("Griglia y " + y/100);
            	node.setLayoutX(x - rectangle.getX());
            	node.setLayoutY(y - rectangle.getY());
            }
        });
    }
    
    public AnchorPane getGameViewer() {
        return gameViewer;
    }
}
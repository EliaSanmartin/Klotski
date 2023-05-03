package klotski;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GeometricShape {

    private Rectangle rectangle;
    private int gridX;
    private int gridY;
    private int id;
    //inizializza il rettangolo con larghezza altezza e posizione nella griglia e colore, posizione su griglia elia
    public GeometricShape(int height, int width, int gridX, int gridY, Color rectangleColor, int id_) {
    	this.gridX = gridX;
    	this.gridY = gridY;
        id = id_;
    	rectangle = new Rectangle(gridX, gridY, width, height);
        rectangle.setFill(rectangleColor);
        rectangle.setStrokeWidth(3);
        rectangle.setStroke(Color.DARKSLATEGREY);
    	
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getX() {
        return gridX;
    }

    public int getY() {
        return gridY;
    }
    
    public void setX(int a) {
        gridX = a;
    }

    public void setY(int a) {
        gridY = a;
    }
    
    public int getID() {
        return id;
    }
}
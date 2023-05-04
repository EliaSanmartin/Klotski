package klotski;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//implemento interfaccia Initializable per metodo initialize
public class Controller implements Initializable {

	//variabili per visualizzare le varie scene di gioco
	private Stage stage;
	private Scene scene;
	private static int level = 1;
	
	//variabili per il pannello che fa visualizzare il gioco nella scena play
	@FXML
	private AnchorPane gameViewer;
	private Text moves = new Text("fdfdfdfdfdfdfdfffffffffffffffffffffffffffffffffff");
	private StackPane mos = new StackPane(moves);
	
	private Grid grid;
	
	//va alla scena Menu
	public void switchToMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//va alla scena Resume game
	public void switchToResumeGame(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ResumeGame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//va alla scena How to play
	public void switchToHowToPlay(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HowToPlay.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//mossa precedente
	public void previousMove(ActionEvent event) throws IOException {
		grid.undo();
	}
	
	//next best move
	public void nextBestMove(ActionEvent event) throws IOException {
		System.out.println("Premuto next best move");
	}
	
	//Reset
	public void reset(ActionEvent event) throws IOException {
	
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//va alla scena Play - Level 1
	public void switchToPlay(ActionEvent event) throws IOException {
		level = 1;
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
		
	//va alla scena Level 2
	public void level2(ActionEvent event) throws IOException {
		level = 2;
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
		
	//va alla scena Level 3
	public void level3(ActionEvent event) throws IOException {
		level = 3;
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
		
	//va alla scena Level 3
	public void saveGame(ActionEvent event) throws IOException {
		System.out.println("Modificami per salvare il gioco");
	}
			
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		//moves.setText("Semp");
		
		
    	if (gameViewer!= null) {
    		grid = new Grid(gameViewer);
    		grid.imposta_formazione(level);

    		
    		
    		for (int i=0; i<grid.rectangles.size(); i++) 
    		{
    			GeometricShape rectangle = grid.rectangles.get(i);
    			gameViewer.getChildren().add(grid.rectangles.get(i).getRectangle());
    			grid.drag(rectangle);
    		}	

			
    	}
   	}
}
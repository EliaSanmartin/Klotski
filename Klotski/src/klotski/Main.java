package klotski;


//caricare sempre stessa scena play ma che attraverso variabile  if1 if2 if3 carica pezzi diversi nella nuova scena


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.Image;


public class Main extends Application {

	@Override
	public void start(Stage stage) {
		try {
		
			Grid scacc = new Grid();	
			//scacc.new_partita();
			
			//scena iniziale
			Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			//Icona e titolo gioco
			Image icon = new Image("icon.jpg");
			stage.getIcons().add(icon);
			stage.setTitle("Klotski");
			
			//Disabilita ridimensionamento finestra
			stage.setResizable(false);
		
			stage.setScene(scene);
			stage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

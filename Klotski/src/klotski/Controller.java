package klotski;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.io.File;
import java.io.FileWriter;

//implemento interfaccia Initializable per metodo initialize
public class Controller implements Initializable {

	// variabili per visualizzare le varie scene di gioco
	private Stage stage;
	private Scene scene;
	private static int level = 1;

	// variabili per il pannello che fa visualizzare il gioco nella scena play
	@FXML
	private AnchorPane gameViewer;
	@FXML
	private Text moves;
	@FXML
	private Label move;
	@FXML
	private AnchorPane ScrollSave;
	@FXML
	private ScrollPane saver;

	private FileS salv;
	private GraphicGrid grid;

	// va alla scena Menu
	public void switchToMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// va alla scena Resume game
	public void switchToResumeGame(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("ResumeGame.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	// va alla scena How to play
	public void switchToHowToPlay(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HowToPlay.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
	}

	// effettua la mossa precedente
	public void previousMove(ActionEvent event) throws IOException {
		grid.undo();

	}

	// effettua la next best move
	public void nextBestMove(ActionEvent event) throws IOException {
		grid.next_move();
	}

	// Resetta la posizione dei quadratini
	public void reset(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// imposta quadratini in formazione Level 1
	public void switchToPlay(ActionEvent event) throws IOException {
		level = 1;
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// imposta quadratini in formazione Level 2
	public void level2(ActionEvent event) throws IOException {
		level = 2;
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// imposta quadratini in formazione Level 3
	public void level3(ActionEvent event) throws IOException {
		level = 3;
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// crea salvataggio
	public void saveGame(ActionEvent event) throws IOException {
		PrintMessage.showMessage((Stage) ((Node) event.getSource()).getScene().getWindow(), "Saved");
		grid.save_game();
	}

	// Pulsante di conferma della vittoria (Ok)
	public void victoryOk(ActionEvent event) throws IOException {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	//in base agli elementi grafici presenti nella scena corrente
	//inizializza i paramentri/caratteristiche degli elementi grafici e le loro funzionalità
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		//Se la schermata corrente è la schermata "resume game"
		if (ScrollSave != null && saver != null) {
			List<String> nomi = new ArrayList<>();
			List<String> nomi_aggiustati = new ArrayList<>();
			int index = 0;

			File f = new File("salvataggi");//apro la cartella salvataggi
			for (File file : f.listFiles()) {//trovo tutti i file nella cartella salvataggi e prendo i nomi
				nomi.add(file.getName());
			}
			//aggiusto la formattazzione dei nomi che andranno stampati-------------------
			for (String str : nomi) {
				String s = str;
				for (index = 0; index < str.length(); index++) {
					if (str.charAt(index) == '_') {
						s = str.substring(0, index) + ' ' + str.substring(index + 1, str.length() - 4);
						break;
					}
				}
				for (int i = 0; i < index; i++) {
					if (str.charAt(i) == '-') {
						s = s.substring(0, i) + ':' + s.substring(i + 1, s.length());
						break;
					}
				}
				for (int i = index; i < s.length(); i++) {
					if (str.charAt(i) == '-') {
						s = s.substring(0, i) + '/' + s.substring(i + 1, s.length());
					}
				}
				nomi_aggiustati.add(s);
			}
			//-------------------------------------------------
			//visualizza graficamente i vari file come label e da ad ogni label la possibilità di essere cliccato
			for (int i = 0; i < nomi.size(); i++) {
				move = new Label(nomi_aggiustati.get(i));
				move.setTextFill(Color.BLACK);
				move.setFont(Font.font("Franklin Gothic Heavy", 17));
				move.setLayoutY(i * 30);

				ScrollSave.getChildren().add(move);

				String s = nomi.get(i);
				File file = new File("file_moves.txt");

				//gestisce l'evento "click" del mouse di ogni label attraverso una funzione lambda
				move.setOnMouseClicked(e -> {
					//passa l'informazione del nome del file che deve aprire da "resume game" alla scena "play"
					try {
						FileWriter fl = new FileWriter("file_moves.txt");
						file.createNewFile();
						fl.write(s);
						fl.close();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//faccio partire la schermata play
					try {
						Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
						stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			}
		}
		//Se la schermata corrente è la schermata "play"
		if (gameViewer != null) {

			grid = new GraphicGrid(moves);
			moves.setText("Moves: " + grid.getNumMove());
			String old_p;
			//controlla se devo aprire un vecchio salvataggio e nel caso lo apre
			FileS fls = new FileS("file_moves.txt");
			if (fls.getContenuto().get(0).length() != 0) {
				old_p = fls.getContenuto().get(0);
				fls.remove();
				fls = new FileS("salvataggi//" + old_p);
				level = fls.getLevel();
			}
			//imposta i pezzi in base al livello
			grid.imposta_formazione(level);

			for (int i = 0; i < grid.getRectangles().size(); i++) {
				GeometricShape rectangle = grid.getRectangles().get(i);
				gameViewer.getChildren().add(grid.getRectangles().get(i).getRectangle());
				grid.drag(rectangle);
			}
			if (fls.getContenuto().get(0).length() != 0) {
				grid.resumegames(fls);

				moves.setText("Moves: " + grid.getNumMove());
			}

		}
	}
}
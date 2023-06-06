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

	// mossa precedente
	public void previousMove(ActionEvent event) throws IOException {
		grid.undo();

	}

	// next best move
	public void nextBestMove(ActionEvent event) throws IOException {
		grid.next_move();
	}

	// Reset
	public void reset(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// va alla scena Play - Level 1
	public void switchToPlay(ActionEvent event) throws IOException {
		level = 1;
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// va alla scena Level 2
	public void level2(ActionEvent event) throws IOException {
		level = 2;
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// va alla scena Level 3
	public void level3(ActionEvent event) throws IOException {
		level = 3;
		Parent root = FXMLLoader.load(getClass().getResource("Play.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// saveGame
	public void saveGame(ActionEvent event) throws IOException {
		PrintMessage.showMessage((Stage) ((Node) event.getSource()).getScene().getWindow(), "Saved");
		grid.save_game();
	}

	// VictoryOk
	public void victoryOk(ActionEvent event) throws IOException {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		if (ScrollSave != null && saver != null) {
			List<String> nomi = new ArrayList<>();
			List<String> nomi_aggiustati = new ArrayList<>();
			int index = 0;

			File f = new File("salvataggi");
			for (File file : f.listFiles()) {
				nomi.add(file.getName());
			}
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

			for (int i = 0; i < nomi.size(); i++) {
				move = new Label(nomi_aggiustati.get(i));
				move.setTextFill(Color.BLACK);
				move.setFont(Font.font("Franklin Gothic Heavy", 17));
				move.setLayoutY(i * 30);

				ScrollSave.getChildren().add(move);

				String s = nomi.get(i);
				File file = new File("file_moves.txt");

				move.setOnMouseClicked(e -> {

					try {
						FileWriter fl = new FileWriter("file_moves.txt");
						file.createNewFile();
						fl.write(s);
						fl.close();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					salv = new FileS("salvataggi\\" + s);
					Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("Play.fxml"));
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
		if (gameViewer != null) {

			grid = new GraphicGrid(moves);
			moves.setText("Moves: " + grid.getNumMove());
			String old_p;

			FileS fls = new FileS("file_moves.txt");
			if (fls.contenuto.get(0).length() != 0) {
				old_p = fls.contenuto.get(0);
				fls.remove();
				fls = new FileS("salvataggi//" + old_p);
				level = fls.getLevel();
			}

			grid.imposta_formazione(level);

			for (int i = 0; i < grid.rectangles.size(); i++) {
				GeometricShape rectangle = grid.rectangles.get(i);
				gameViewer.getChildren().add(grid.rectangles.get(i).getRectangle());
				grid.drag(rectangle);
			}
			if (fls.contenuto.get(0).length() != 0) {
				grid.resumegames(fls);

				moves.setText("Moves: " + grid.getNumMove());
			}

		}
	}
}
package klotski;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public final class PrintMessage {
	
    public static void showMessage(Stage passedStage, String message)
    {
    	//posizione di dove viene stampata il print message
        Stage toastStage=new Stage();
        toastStage.setX(passedStage.getX()+50);
        toastStage.setY(passedStage.getY()+575);
   
        toastStage.initOwner(passedStage);
        toastStage.setResizable(false);
        
        toastStage.initStyle(StageStyle.TRANSPARENT); // rende finestra senza tasti chiusura 

        Text text = new Text(message);
        text.setFont(Font.font("Franklin Gothic Heavy", 25));
        text.setFill(Color.BLACK);

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-radius: 10; -fx-background-color: rgba(256, 256, 256, 0.5); -fx-padding: 2px;");
        root.setOpacity(0);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(200), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1)); 
        fadeInTimeline.getKeyFrames().add(fadeInKey1);   
        fadeInTimeline.setOnFinished((ae) -> 
        {
            new Thread(() -> {
                try
                {
                    Thread.sleep(400);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                   Timeline fadeOutTimeline = new Timeline();
                    KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(200), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0)); 
                    fadeOutTimeline.getKeyFrames().add(fadeOutKey1);   
                    fadeOutTimeline.setOnFinished((aeb) -> toastStage.close()); 
                    fadeOutTimeline.play();
            }).start();
        }); 
        fadeInTimeline.play();
    }
}
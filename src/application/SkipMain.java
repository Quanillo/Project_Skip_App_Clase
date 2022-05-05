package application;

import java.io.IOException;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class SkipMain extends Application {
	
	private double xOffset=0;
	private double yOffset=0;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		//Creación de la escena principal

		Parent root=FXMLLoader.load(getClass().getResource("skipAppMain.fxml"));
		primaryStage.initStyle(StageStyle.UNDECORATED);//Quita el borde de windows
		
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
		
		//move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
		
		primaryStage.setTitle("Tus pellas organizadas");
		primaryStage.setScene(new Scene(root,800,600));
		primaryStage.show(); 

	}
	

	public static void main(String[] args) {
		launch(args);
		
	}
}

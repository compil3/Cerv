package Cerv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mastergui.fxml"));
        primaryStage.setTitle("Cerv v.1.0.0 Beta");
        primaryStage.setScene(new Scene(root, 966,584 ));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

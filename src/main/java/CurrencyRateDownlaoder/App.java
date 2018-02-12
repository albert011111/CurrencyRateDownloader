package CurrencyRateDownlaoder;

import CurrencyRateDownlaoder.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainController.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        primaryStage.setScene(new Scene(root, Color.DARKGRAY));
        primaryStage.setTitle("Kantor");
        primaryStage.show();
    }
}

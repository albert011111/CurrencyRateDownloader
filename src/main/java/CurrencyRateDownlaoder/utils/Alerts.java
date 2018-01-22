package CurrencyRateDownlaoder.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Patryk on 27.07.2017.
 */
public class Alerts {


    public static Optional<ButtonType> confirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("INFO");
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public static void warningAlert(String message) {
        Alert alert;
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ostrzeżenie");
        alert.setHeaderText("INFO");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void infoAlert(String message) {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText("INFO");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void errorAlert(String message) {
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("INFO");
        alert.setContentText(message);
        alert.showAndWait();
    }


}

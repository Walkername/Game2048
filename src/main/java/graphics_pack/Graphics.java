package graphics_pack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class Graphics extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root); // x: 700, y: 700

        stage.setResizable(false);
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            logout(stage);
        });
    }

    public void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to quit this great game?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

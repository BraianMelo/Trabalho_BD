package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Criptideo;
import model.dao.CriptideoDAO;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);  // Inicia a aplicação JavaFX primeiro
        System.out.println("Terminou!");
    }

    @Override
    public void start(Stage stage) {
        try {
            // Carregar a interface gráfica
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/MenuView.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);

            // Definir ícone da janela
            Image icone = new Image(getClass().getResourceAsStream("/gui/images/Icon.png"));
            stage.getIcons().add(icone);

            stagePreferences(stage);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stagePreferences(Stage stage) {
        stage.setTitle("SGBD: Criptozoologia");
        stage.setResizable(false);
    }
}

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

import util.WindowsUtil;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);  // Inicia a aplicação JavaFX primeiro
        System.out.println("Aplicação encerrada!");
    }

    @Override
    public void start(Stage stage) {
        WindowsUtil.abrirJanela("/gui/MenuView.fxml", "/gui/styles/MenuView.css", "SGBD: Criptozoologia", "/gui/images/Icon.png");
    }
}

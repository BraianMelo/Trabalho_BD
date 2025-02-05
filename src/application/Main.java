package application;

import javafx.application.Application;
import javafx.stage.Stage;
import util.Utils;

public class Main extends Application {
	
    public static void main(String[] args) {
        launch(args);  // Inicia a aplicação JavaFX primeiro
        System.out.println("Aplicação encerrada!");
    }

    @Override
    public void start(Stage stage) {
        Utils.abrirJanela("/view/MenuView.fxml", "/view/styles/MenuViewDarkMode.css", "SGBD: Criptozoologia", "/view/images/Icone_Aplicativo.png");
    }
    

    
}

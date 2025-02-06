package app;

import javafx.application.Application;
import javafx.stage.Stage;
import utilitario.Utilitario;

public class Aplicacao extends Application {
	
    public static void main(String[] args) {
        launch(args);  // Inicia a aplicação JavaFX primeiro
        System.out.println("Aplicação encerrada!");
    }

    @Override
    public void start(Stage stage) {
        Utilitario.abrirJanela("/visao/MenuVisao.fxml", "/visao/estilos/MenuVisaoModoEscuro.css", "SGBD: Criptozoologia", "/visao/imagens/Icone_Aplicativo.png");
    }
    

    
}

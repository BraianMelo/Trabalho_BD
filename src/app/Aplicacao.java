package app;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;
import persistencia.ConexaoBD;
import utilitario.Utilitario;

public class Aplicacao extends Application {
	
    public static void main(String[] args) {
        launch(args);  
        System.out.println("Aplicação encerrada!");
    }

    @Override
    public void start(Stage stage) {
        try {
            Connection conexao = ConexaoBD.getConexao();
            Utilitario.abrirJanela("/visao/MenuVisao.fxml", "/visao/estilos/MenuVisaoModoEscuro.css", "SGBD: Criptozoologia", "/visao/imagens/Icone_Aplicativo.png");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("Fechando conexão com o banco de dados...");
        ConexaoBD.fecharConexao();
    }
}

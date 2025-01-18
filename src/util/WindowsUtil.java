package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import model.Criptideo;
import gui.CryptidViewController;

public class WindowsUtil {

    private static final boolean REDIMENSIONAVEL = false;

	public static void abrirJanela(String caminhoFXML, String caminhoCss, String titulo, String iconePath) {
		try {
			FXMLLoader loader = new FXMLLoader(WindowsUtil.class.getResource(caminhoFXML));
			Parent parent = loader.load();

			Scene scene = new Scene(parent);

			// Adiciona o CSS à cena, se o caminho for fornecido
			if (caminhoCss != null && !caminhoCss.isEmpty()) {
				scene.getStylesheets().add(WindowsUtil.class.getResource(caminhoCss).toExternalForm());
			}

			Stage stage = new Stage();
			stage.setScene(scene);

			// Configura o ícone, se fornecido
			if (iconePath != null && !iconePath.isEmpty()) {
				Image icone = new Image(WindowsUtil.class.getResourceAsStream(iconePath));
				stage.getIcons().add(icone);
			}

			stage.setTitle(titulo);
			stage.setResizable(REDIMENSIONAVEL);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void abrirJanelaComCriptideo(String caminhoFXML, String caminhoCss, String titulo, String iconePath, Criptideo criptideo) {
        try {
            // Carregar o arquivo FXML
            FXMLLoader loader = new FXMLLoader(WindowsUtil.class.getResource(caminhoFXML));

            // Passar o objeto Criptideo para o controlador
            loader.setControllerFactory(controllerClass -> new CryptidViewController(criptideo));

            // Carregar a cena
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // Adiciona o CSS à cena, se o caminho for fornecido
			if (caminhoCss != null && !caminhoCss.isEmpty()) {
				scene.getStylesheets().add(WindowsUtil.class.getResource(caminhoCss).toExternalForm());
			}

            // Criar a janela
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

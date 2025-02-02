package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import model.Criptideo;

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
	
	public boolean mostrarAlertaConfirmacao(String titulo){
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(titulo);
        alert.setContentText("Escolha OK para continuar ou Cancelar para sair.");

        // Exibindo o alerta e aguardando a resposta
        Optional<ButtonType> result = alert.showAndWait();

        // Verificando a resposta
        if (result.isPresent() && result.get() == ButtonType.OK)
			return true;
			
		return false;
	}
	
	public void mostrarAlertaErro(String texto) {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("Erro");
		errorAlert.setHeaderText(texto);
		errorAlert.showAndWait();
	}
    
    public String formatarEnum(String enumStr){
		String strFormatada = enumStr.toLowerCase();
		strFormatada = strFormatada.substring(0, 1).toUpperCase() + strFormatada.substring(1);
		return strFormatada;
	}

}

package util;

import java.io.IOException;
import java.util.Optional;

import controller.MenuViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Utils {

    private static final boolean REDIMENSIONAVEL = false;

	public static void abrirJanela(String caminhoFXML, String caminhoCss, String titulo, String iconePath) {
		try {
			FXMLLoader loader = new FXMLLoader(Utils.class.getResource(caminhoFXML));
			Parent parent = loader.load();

			Scene scene = new Scene(parent);

			// Adiciona o CSS à cena, se o caminho for fornecido
			if (caminhoCss != null && !caminhoCss.isEmpty()) {
				scene.getStylesheets().add(Utils.class.getResource(caminhoCss).toExternalForm());
			}

			Stage stage = new Stage();
			stage.setScene(scene);

			// Configura o ícone, se fornecido
			if (iconePath != null && !iconePath.isEmpty()) {
				Image icone = new Image(Utils.class.getResourceAsStream(iconePath));
				stage.getIcons().add(icone);
			}

			stage.setTitle(titulo);
			stage.setResizable(REDIMENSIONAVEL);
			stage.show();
			
			MenuViewController controller = loader.getController();
			controller.setDados(stage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean mostrarAlertaConfirmacao(String titulo){
		Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmação");
        alerta.setHeaderText(titulo);
        alerta.setContentText("Escolha OK para continuar ou Cancelar para sair.");

        // Exibindo o alerta e aguardando a resposta
        Optional<ButtonType> result = alerta.showAndWait();

        // Verificando a resposta
        if (result.isPresent() && result.get() == ButtonType.OK)
			return true;
			
		return false;
	}
	
	public void mostrarAlertaErro(String texto) {
		Alert alertaErro = new Alert(AlertType.ERROR);
		alertaErro.setTitle("Erro");
		alertaErro.setHeaderText(texto);
		alertaErro.showAndWait();
	}
	
	public void mostrarAlertaMensagem(String titulo, String texto) {
		Alert alertaMensagem = new Alert(AlertType.INFORMATION);
		alertaMensagem.setTitle(titulo);
		alertaMensagem.setHeaderText(texto);
		alertaMensagem.showAndWait();
	}
    
    public String formatarEnum(String enumStr){
		String strFormatada = enumStr.toLowerCase();
		strFormatada = strFormatada.substring(0, 1).toUpperCase() + strFormatada.substring(1);
		return strFormatada;
	}

}

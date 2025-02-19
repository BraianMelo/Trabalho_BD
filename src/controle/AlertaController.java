package controle;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public abstract class AlertaController {
	
	public AlertaController() {}
	
    public boolean mostrarAlertaConfirmacao(String titulo){
		Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmação");
        alerta.setHeaderText(titulo);
        alerta.setContentText("Escolha OK para continuar ou Cancelar para sair.");

        // Exibindo o alerta e aguardando a resposta
        Optional<ButtonType> resposta = alerta.showAndWait();

        // Verificando a resposta
        if (resposta.isPresent() && resposta.get() == ButtonType.OK)
			return true;
			
		return false;
	}
	
    public void mostrarAlerta(AlertType tipo, String titulo, String texto) {
		Alert alerta = new Alert(tipo);
		alerta.setTitle(tipo.toString());
		alerta.setHeaderText(titulo);
		alerta.setContentText(texto);
		alerta.showAndWait();

	}

}

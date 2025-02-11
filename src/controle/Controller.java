package controle;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public abstract class Controller {
	
	protected String formatarEnum(String enumStr){
		String strFormatada = enumStr.toLowerCase();
		strFormatada = strFormatada.substring(0, 1).toUpperCase() + strFormatada.substring(1);
		return strFormatada;
	}
	
	protected boolean textFieldVazio(TextField txtf) {
		return txtf.getText().equals("");
	}
    
    protected String getTextField(TextField txtf) {
    	return(txtf.getText().equals("")? null: txtf.getText());
    }
    
    protected void setTextField(TextField txtf, String texto) {
    	txtf.setText(texto);
    }
	
    protected boolean mostrarAlertaConfirmacao(String titulo){
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
	
    protected void mostrarAlerta(AlertType tipo, String titulo, String texto) {
		Alert alerta = new Alert(tipo);
		alerta.setTitle(tipo.toString());
		alerta.setHeaderText(titulo);
		alerta.setContentText(texto);
		alerta.showAndWait();

	}
	
}

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Criptideo;

public class CryptidPaneController {

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTipo;

    @FXML
    private Label lblStatus;
    
    @FXML
    private Label lblDescricao;
    
    private String formatarEnum(String texto) {
		if (texto == null || texto.isEmpty()) {
			return texto; // Retorna o texto original se for nulo ou vazio
		}
		return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
	}


    public void setDados(Criptideo criptideo) {
        lblNome.setText(criptideo.getNome());
        lblTipo.setText(formatarEnum(criptideo.getTipo().toString()));
        lblStatus.setText(formatarEnum(criptideo.getStatusCr().toString()));
        lblDescricao.setText(criptideo.getDescricao());
    }
}

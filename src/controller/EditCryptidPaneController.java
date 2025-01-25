package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class EditCryptidPaneController {

    @FXML
    private TextField txtfNomeCriptideo;

    @FXML
    private MenuButton mbtnTipo;

    @FXML
    private MenuButton mtbnStatus;

    @FXML
    private TextField txtfCaminhoFoto;

    @FXML
    private Button btnSalvar;

    // Evento para alterar o texto do MenuButton de tipo do Criptídeo
    @FXML
    private void onMtbnTipoAction(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        mbtnTipo.setText(selectedItem.getText());
    }

    // Evento para alterar o texto do MenuButton de status do Criptídeo
    @FXML
    private void onMtbnStatusAction(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        mtbnStatus.setText(selectedItem.getText());
    }
    
    @FXML
    private void onBtnSalvarAction(ActionEvent event) {
        // Aqui você pode adicionar a lógica para salvar as informações
        String nome = txtfNomeCriptideo.getText();
        String tipo = mbtnTipo.getText();
        String status = mtbnStatus.getText();
        String caminhoFoto = txtfCaminhoFoto.getText();

        System.out.println("Nome do Criptídeo: " + nome);
        System.out.println("Tipo do Criptídeo: " + tipo);
        System.out.println("Status do Criptídeo: " + status);
        System.out.println("Caminho da Foto: " + caminhoFoto);

    }

	
}

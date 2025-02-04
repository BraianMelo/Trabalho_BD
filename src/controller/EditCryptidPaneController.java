package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.Criptideo;
import model.enums.StatusCriptideo;
import model.enums.Tipo;
import persistence.CriptideoDAO;

public class EditCryptidPaneController {
	
	private MenuViewController menuController;
	private Criptideo criptideo;

    @FXML
    private TextField txtfNomeCriptideo;

    @FXML
    private MenuButton mbtnTipo;

    @FXML
    private MenuButton mbtnStatus;

    @FXML
    private TextField txtfCaminhoFoto;

    @FXML
    private Button btnSalvar;
    
	public void setDados(Criptideo criptideo, MenuViewController menuController) {
		this.menuController = menuController;
		this.criptideo = criptideo;
		
		if (criptideo != null) {
			txtfNomeCriptideo.setText(criptideo.getNome());
			txtfCaminhoFoto.setText(criptideo.getImagemCaminho());
			selecionarMenuItem(mbtnStatus, criptideo.getStatusCr().ordinal());
			selecionarMenuItem(mbtnTipo, criptideo.getTipo().ordinal());
		}
	}
	
	private void selecionarMenuItem(MenuButton menuButton, int valor) {
		if (valor >= 0 && valor < menuButton.getItems().size()) {
			menuButton.setText(menuButton.getItems().get(valor).getText());
		}
	}

    // Evento para alterar o texto do MenuButton de tipo do Criptídeo
    @FXML
    private void onMbtnTipoAction(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        mbtnTipo.setText(selectedItem.getText());
    }

    // Evento para alterar o texto do MenuButton de status do Criptídeo
    @FXML
    private void onMbtnStatusAction(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        mbtnStatus.setText(selectedItem.getText());
    }
    
    @FXML
    private void onBtnSalvarAction(ActionEvent event) {
        criptideo.setNome(txtfNomeCriptideo.getText());
        criptideo.setTipo(Tipo.valueOf(mbtnTipo.getText().toUpperCase()));
        criptideo.setStatusCr(StatusCriptideo.valueOf(mbtnStatus.getText().toUpperCase()));
        criptideo.setImagemCaminho(txtfCaminhoFoto.getText());

        CriptideoDAO criptideoDAO = new CriptideoDAO();
        criptideoDAO.atualizar(criptideo);
        menuController.carregarGridCriptideos();
        menuController.fecharAba();

    }

	
}

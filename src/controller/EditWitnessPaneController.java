package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.Testemunha;
import model.enums.Genero;
import persistence.TestemunhaDAO;

public class EditWitnessPaneController {
	
	private MenuViewController menuViewController;
	private Testemunha testemunha;
	
	@FXML
	private TextField txtfNome;
	
	@FXML
	private TextField txtfSobrenome;
	
	@FXML
	private TextField txtfIdade;
	
    @FXML
    private MenuButton mbtnGenero;
	
	@FXML
	private TextField txtfEmail;
	
	@FXML
	private TextField txtfTelefone;
	
	public void setDados(Testemunha testemunha, MenuViewController menuViewController) {
		this.menuViewController = menuViewController;
		this.testemunha = testemunha;
		
		txtfNome.setText(testemunha.getNome());
		txtfSobrenome.setText(testemunha.getSobrenome());
		txtfIdade.setText(Integer.toString(testemunha.getIdade()));
		
		switch(testemunha.getGenero()) {
			case M:
				mbtnGenero.setText("Masculino");
				break;
			case F:
				mbtnGenero.setText("Feminino");
				break;
				
			default:
				mbtnGenero.setText("Outro");
				
		}
		
		selecionarMenuItem(mbtnGenero, testemunha.getGenero().ordinal());
		
		if(testemunha.getEmail() != null)
			txtfEmail.setText(testemunha.getEmail());
		
		if(testemunha.getTelefone() != null)
			txtfTelefone.setText(testemunha.getTelefone());
		
	}
	
	private void selecionarMenuItem(MenuButton menuButton, int valor) {
		if (valor >= 0 && valor < menuButton.getItems().size()) {
			menuButton.setText(menuButton.getItems().get(valor).getText());
		}
	}
	
	@FXML
	private void onMbtnGeneroAction(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        mbtnGenero.setText(selectedItem.getText());
	}
	
	@FXML
	private void onBtnSalvarAction() {
		testemunha.setNome(txtfNome.getText());
		testemunha.setSobrenome(txtfSobrenome.getText());
		testemunha.setIdade(Integer.parseInt(txtfIdade.getText()));
		
		switch(mbtnGenero.getText()) {
			case "Masculino":
					testemunha.setGenero(Genero.M);
					break;
					
			case "Feminino":
				testemunha.setGenero(Genero.F);
				break;
				
			default:
				testemunha.setGenero(Genero.O);
			
		}
		
		testemunha.setEmail(txtfEmail.getText());
		if(testemunha.getEmail().equals(""))
			testemunha.setEmail(null);
		
		testemunha.setTelefone(txtfTelefone.getText());
		if(testemunha.getTelefone().equals(""))
			testemunha.setTelefone(null);
		
		TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
		testemunhaDAO.atualizarTestemunha(testemunha);
		
		menuViewController.fecharAba();
	}

}

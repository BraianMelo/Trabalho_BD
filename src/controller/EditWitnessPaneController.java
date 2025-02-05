package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Testemunha;
import model.enums.Genero;
import model.enums.ModeloAba;
import persistence.AvistamentoTestemunhaDAO;
import persistence.TestemunhaDAO;
import util.Utils;

public class EditWitnessPaneController {
	
	private SightingPaneController sightingPaneController;
	private MenuViewController menuViewController;
	private Testemunha testemunha;
	private Integer idAvistamento;
	private ModeloAba modelo;
	
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
	
	@FXML
	private ImageView imgBotao;
	
	public void setDados(Testemunha testemunha, Integer idAvistamento,  ModeloAba modelo,  SightingPaneController sightingPaneController, MenuViewController menuViewController) {
		this.menuViewController = menuViewController;
		this.sightingPaneController = sightingPaneController;
		this.idAvistamento = idAvistamento;
		this.testemunha = testemunha;
		this.modelo = modelo;
		
		if(modelo.equals(ModeloAba.ADICIONAR)) {
			Image icone = new Image(Utils.class.getResourceAsStream("/view/images/Icone_Adicionar.png"));
			imgBotao.setImage(icone);
			return;
			
		}
		
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
		AvistamentoTestemunhaDAO atDAO = new AvistamentoTestemunhaDAO();
		
		if(modelo.equals(ModeloAba.ADICIONAR)) {
			testemunhaDAO.inserirTestemunha(testemunha);
			atDAO.inserirRelacao(idAvistamento, testemunha.getIdTestemunha());
			
		} else {
			testemunhaDAO.atualizarTestemunha(testemunha);
		}
		
		sightingPaneController.carregarGrid();
		sightingPaneController.reportarAlteracao();
		menuViewController.fecharAba();
	}

}

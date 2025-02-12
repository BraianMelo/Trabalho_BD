package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import modelo.Testemunha;
import modelo.enums.Genero;
import modelo.enums.ModeloAba;
import persistencia.AvistamentoTestemunhaDAO;
import persistencia.TestemunhaDAO;

public class EditarTestemunhaController extends Controller{
	
	private AvistamentoController avistamentoController;
	private MenuController menuController;
	private Testemunha testemunha;
	private Integer idAvistamento;
	
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
	
	public void setDados(Testemunha testemunha, Integer idAvistamento,  ModeloAba modeloAba,  AvistamentoController sightingPaneController, MenuController menuViewController) {
		this.menuController = menuViewController;
		this.avistamentoController = sightingPaneController;
		this.idAvistamento = idAvistamento;
		this.testemunha = testemunha;
		this.modeloAba = modeloAba;
		
		setImagemBotao(imgBotao);
		
		setTextField(txtfNome, testemunha.getNome());
		setTextField(txtfSobrenome, testemunha.getSobrenome());
		txtfIdade.setText(Integer.toString(testemunha.getIdade()));
		setTextField(txtfEmail, testemunha.getEmail());
		setTextField(txtfTelefone, testemunha.getTelefone());
		
		if(testemunha.getGenero() == null)
			testemunha.setGenero(Genero.M);
		
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
		if(textFieldVazio(txtfNome) || textFieldVazio(txtfSobrenome) || textFieldVazio(txtfIdade)) {
			
			mostrarAlerta(AlertType.ERROR, 
					"Há campos vazios!", 
					"'Nome', 'Sobrenome', 'Idade' não aceitma campos vazios.");
			
			return;
		}
		
		
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
		
		testemunha.setEmail(getTextField(txtfEmail));
		testemunha.setTelefone(getTextField(txtfTelefone));
		
		TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
		AvistamentoTestemunhaDAO atDAO = new AvistamentoTestemunhaDAO();
		
		if(modeloAba.equals(ModeloAba.ADICIONAR)) {
			testemunhaDAO.inserir(testemunha);
			atDAO.inserirRelacao(idAvistamento, testemunha.getIdTestemunha());
			
		} else {
			testemunhaDAO.atualizar(testemunha);
		}
		
		avistamentoController.carregarGrid();
		avistamentoController.reportarAlteracao();
		menuController.fecharAba();
	}

}

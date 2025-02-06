package controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.Testemunha;
import modelo.enums.Genero;
import modelo.enums.ModeloAba;
import persistencia.AvistamentoTestemunhaDAO;
import persistencia.TestemunhaDAO;
import utilitario.Utilitario;

public class TestemunhaController {
	
	 private MenuController menuController;
	 private AvistamentoController avistamentoController;
	 private Testemunha testemunha;
	 private Integer idAvistamento;
	
	
	@FXML
	private Label lblNome;
	
	@FXML
	private Label lblSobrenome;
	
	@FXML
	private Label lblGenero;
	
	@FXML
	private Label lblIdade;
	
	@FXML
	private Label lblTelefone;
	
	@FXML
	private Label lblEmail;
	
	@FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnEditar;
	
	public void setDados(Testemunha testemunha, Integer idAvistamento, AvistamentoController avistamentoController, MenuController menuController){
		this.testemunha = testemunha;
		this.menuController = menuController;
		this.avistamentoController = avistamentoController;
		this.idAvistamento = idAvistamento;
		
		lblNome.setText(testemunha.getNome());
		lblSobrenome.setText(testemunha.getSobrenome());
		
		Genero genero = testemunha.getGenero();

		switch(genero) {
			case M:
				lblGenero.setText("Masculino");
				break;
			case F:
				 lblGenero.setText("Feminino");
				break;
			default:
				 lblGenero.setText("Outro");
		}
		
		lblIdade.setText(Integer.toString(testemunha.getIdade()));
				
		if(testemunha.getTelefone() != null)
			lblTelefone.setText(testemunha.getTelefone());
			
		if(testemunha.getEmail() != null)
			lblEmail.setText(testemunha.getEmail());
	}
	
	@FXML
	private void onBtnExcluirAction() {
    	Utilitario windowsUtil = new Utilitario();
    	boolean resposta = windowsUtil.mostrarAlertaConfirmacao("Quer mesmo apagar esse avistamento?");
    	    	
    	if (!resposta)
    		return;
    	
		TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
		testemunhaDAO.excluirTestemunha(testemunha.getIdTestemunha());
		
		AvistamentoTestemunhaDAO atDAO = new AvistamentoTestemunhaDAO();
		atDAO.excluirRelacao(idAvistamento, testemunha.getIdTestemunha());
		
		avistamentoController.carregarGrid();
		avistamentoController.reportarAlteracao();
    }
    
    @FXML
    private void onBtnEditarAction() {
		FXMLLoader loader = menuController.adicionarAba("/visao/EditarTestemunhaPane.fxml", "Editar Testemunha");
		
		if( loader != null) {
			 EditarTestemunhaController controller = loader.getController();
	         controller.setDados(testemunha, null, ModeloAba.EDITAR, avistamentoController, menuController);
		}
    }
	
}

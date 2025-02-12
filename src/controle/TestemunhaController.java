package controle;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.Pesquisador;
import modelo.Testemunha;
import modelo.enums.Genero;
import modelo.enums.ModeloAba;
import persistencia.AvistamentoTestemunhaDAO;
import persistencia.PesquisadorDAO;
import persistencia.TestemunhaDAO;

public class TestemunhaController extends Controller {
	
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
	private Button btnAddPesquisador;
	
	@FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private VBox vboxTestemunha;
	
	public void setDados(Testemunha testemunha, Integer idAvistamento, AvistamentoController avistamentoController, MenuController menuController){
		this.testemunha = testemunha;
		this.menuController = menuController;
		this.avistamentoController = avistamentoController;
		this.idAvistamento = idAvistamento;
		
		preencherTestemunha(testemunha);
		carregarPesquisador();
	}
	
	private void preencherTestemunha(Testemunha testemunha) {
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
	
	public void carregarPesquisador() {
		PesquisadorDAO pesquisadorDAO = new PesquisadorDAO();
		Pesquisador pesquisador = pesquisadorDAO.consultarPorIdTestemunha(testemunha.getIdTestemunha());
		
		if(pesquisador != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/PesquisadorPane.fxml"));
				Pane pane = loader.load();;
			
				PesquisadorController controller = loader.getController();
				controller.setDados(pesquisador, this, menuController);

				removerUltimoDoVbox();
					
				vboxTestemunha.getChildren().addLast(pane);
				btnAddPesquisador.setDisable(true);
				btnAddPesquisador.requestLayout();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} 
		
		removerUltimoDoVbox();
		
		if(btnAddPesquisador.isDisable()) {
			btnAddPesquisador.setDisable(false);
			btnAddPesquisador.requestLayout();
		}

	}
	
	public void reportarAlteracao() {
		avistamentoController.reportarAlteracao();
	}
	
	private void removerUltimoDoVbox() {
		if(vboxTestemunha.getChildren().size() != 6) {
			vboxTestemunha.getChildren().removeLast();
		}
	}
	
	@FXML
	private void onBtnExcluirAction() {

    	boolean resposta = mostrarAlertaConfirmacao("Quer mesmo apagar essa testemunha?");
    	    	
    	if (!resposta)
    		return;
    	
		TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
		testemunhaDAO.excluir(testemunha.getIdTestemunha());
		
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
    
    @FXML
    private void onBtnAddPesquisadorAction() {
		FXMLLoader loader = menuController.adicionarAba("/visao/EditarPesquisadorPane.fxml", "Adicionar Pesquisador");
		
		if( loader != null) {
			 EditarPesquisadorController controller = loader.getController();

			 Pesquisador pesquisador = new Pesquisador();
			 pesquisador.setIdTestemunha(testemunha.getIdTestemunha());
			 
	         controller.setDados(pesquisador, ModeloAba.ADICIONAR, this, menuController);
		}
    }
	
}

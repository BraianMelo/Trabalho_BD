package controle;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.Avistamento;
import modelo.Pesquisador;
import modelo.Testemunha;
import modelo.enums.ModeloAba;
import persistencia.AvistamentoDAO;
import persistencia.AvistamentoTestemunhaDAO;
import persistencia.TestemunhaDAO;
import utilitario.Utilitario;

public class AvistamentoController {
    
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.of("pt", "BR"));
    
    private InformacoesCriptideoController infoCriptideoController;
    private MenuController menuController;
    private Avistamento avistamento;
    
    @FXML
    private Label lblAvistamento;

    @FXML
    private Label lblPais;

    @FXML
    private Label lblData;

    @FXML
    private Label lblLocal;
    
    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private Pane panePrincipal;
    
    @FXML
    private VBox vboxGrid;
    

    public void setDados(Avistamento avistamento, int numeroAvistamento, InformacoesCriptideoController cryptidInformationPaneController, MenuController menuViewController) {
        this.avistamento = avistamento;
    	this.menuController = menuViewController;
    	this.infoCriptideoController = cryptidInformationPaneController;
    	
    	if (avistamento == null) {
            System.err.println("Erro: Avistamento recebido é nulo.");
            return;
        }
	
		lblAvistamento.setText(numeroAvistamento + "° avistamento");
        lblPais.setText(avistamento.getPais());
        lblLocal.setText(avistamento.getLocal());

        if (avistamento.getData() != null) {
            lblData.setText(avistamento.getData().format(dtf));
        } else {
            lblData.setText("Data desconhecida");
        }
        
        carregarGrid();
    }
    
    public void reportarAlteracao() {
    	infoCriptideoController.reportarAlteracao();
    }
    
    public void carregarGrid(){
    	AvistamentoTestemunhaDAO atDAO = new AvistamentoTestemunhaDAO();
		TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
		Testemunha testemunha;

		List<Integer> idsTestemunhas = atDAO.buscarIdsTestemunhasPorAvistamento(avistamento.getIdAvistamento());
		
		vboxGrid.getChildren().clear();
		
		try {
			for(int idTestemunha: idsTestemunhas) {
				testemunha = testemunhaDAO.buscarTestemunhaPorId(idTestemunha);
						
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/TestemunhaPane.fxml"));
				Pane pane = loader.load();
						
				TestemunhaController controller = loader.getController();
				controller.setDados(testemunha, avistamento.getIdAvistamento(), this, menuController);
						
				vboxGrid.getChildren().add(pane);
			}
		} catch (IOException e) {
            System.err.println("Erro ao carregar WitnessPane.fxml: " + e.getMessage());
        }
    
		
	}
    
    @FXML
    private void onBtnExcluirAction() {
    	Utilitario windowsUtil = new Utilitario();
    	boolean resposta = windowsUtil.mostrarAlertaConfirmacao("Quer mesmo apagar esse avistamento?");
    	    	
    	if (!resposta)
    		return;
    	
        AvistamentoDAO avistamentoDAO = new AvistamentoDAO();
        AvistamentoTestemunhaDAO atDAO = new AvistamentoTestemunhaDAO();
        TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
        
        avistamentoDAO.excluir(avistamento.getIdAvistamento());
        
        List<Integer> idsTestemunhas = atDAO.buscarIdsTestemunhasPorAvistamento(avistamento.getIdAvistamento());
        
        for(Integer idTestemunha: idsTestemunhas) {
        	atDAO.excluirRelacao(avistamento.getIdAvistamento(), idTestemunha);
        	testemunhaDAO.excluirTestemunha(idTestemunha);
        }
        
        infoCriptideoController.carregarGridAvistamentos();
        infoCriptideoController.reportarAlteracao();
        
    }
    
    @FXML
    private void onBtnEditarAction() {
		FXMLLoader loader = menuController.adicionarAba("/visao/EditarAvistamentoPane.fxml", "Editar Avistamento");
		
		if( loader != null) {
			 EditarAvistamentoController controller = loader.getController();
	         controller.setDados(avistamento, null, ModeloAba.EDITAR, null, menuController);
		}
	}
    
    @FXML
    private void onBtnAdicionarTestemunhaAction() {
		FXMLLoader loader = menuController.adicionarAba("/visao/EditarTestemunhaPane.fxml", "Editar Testemunha");
		
		if( loader != null) {
			 EditarTestemunhaController controller = loader.getController();
	         controller.setDados(new Testemunha(), avistamento.getIdAvistamento(), ModeloAba.ADICIONAR, this, menuController);
		}
    }
    
}

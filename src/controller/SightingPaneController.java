package controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Avistamento;
import persistence.AvistamentoDAO;
import persistence.AvistamentoTestemunhaDAO;
import persistence.TestemunhaDAO;
import util.WindowsUtil;

public class SightingPaneController {
    
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("pt", "BR"));
    
    private MenuViewController menuViewController;
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
    

    public void setDados(Avistamento avistamento, int numeroAvistamento, MenuViewController menuViewController) {
        this.avistamento = avistamento;
    	this.menuViewController = menuViewController;
    	
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
    }
    
    @FXML
    void onBtnExcluirAction() {
    	WindowsUtil windowsUtil = new WindowsUtil();
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
        
        menuViewController.fecharAba();
    }
    
    @FXML
    void onBtnEditarAction() {
		FXMLLoader loader = menuViewController.adicionarAba("/view/EditSightingPane.fxml", "Editar Avistamento");
		
		if( loader != null) {
			 EditSightingPaneController controller = loader.getController();
	         controller.setDados(avistamento, menuViewController);
		}
	}
    
}

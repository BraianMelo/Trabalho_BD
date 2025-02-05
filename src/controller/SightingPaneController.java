package controller;

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
import model.Avistamento;
import model.Testemunha;
import model.enums.ModeloAba;
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
    
    @FXML
    private VBox vboxGrid;
    

    public void setDados(Avistamento avistamento, int numeroAvistamento, MenuViewController menuViewController, List<Integer> idsTestemunhas) {
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
        
        adiocionarTestemunhas(idsTestemunhas);
    }
    
    private void adiocionarTestemunhas(List<Integer> idsTestemunhas){
		TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
		Testemunha testemunha;
		
		try {
			for(int idTestemunha: idsTestemunhas) {
				testemunha = testemunhaDAO.buscarTestemunhaPorId(idTestemunha);
						
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WitnessPane.fxml"));
				Pane pane = loader.load();
						
				WitnessPaneController controller = loader.getController();
				controller.setDados(testemunha, avistamento.getIdAvistamento(), menuViewController);
						
				vboxGrid.getChildren().add(pane);
			}
		} catch (IOException e) {
            System.err.println("Erro ao carregar WitnessPane.fxml: " + e.getMessage());
        }
	}
    
    @FXML
    private void onBtnExcluirAction() {
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
    private void onBtnEditarAction() {
		FXMLLoader loader = menuViewController.adicionarAba("/view/EditSightingPane.fxml", "Editar Avistamento");
		
		if( loader != null) {
			 EditSightingPaneController controller = loader.getController();
	         controller.setDados(avistamento, menuViewController);
		}
	}
    
    @FXML
    private void onBtnAdicionarTestemunhaAction() {
		FXMLLoader loader = menuViewController.adicionarAba("/view/EditWitnessPane.fxml", "Editar Testemunha");
		
		if( loader != null) {
			 EditWitnessPaneController controller = loader.getController();
	         controller.setDados(new Testemunha(), avistamento.getIdAvistamento(), menuViewController, ModeloAba.ADICIONAR);
		}
    }
    
}

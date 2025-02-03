package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import model.Avistamento;

public class SightingPaneController {
    
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("pt", "BR"));
    
    private MenuViewController menuViewController;
    
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
        System.out.println("Excluir");
    }
    
    @FXML
    void onBtnEditarAction() {
		FXMLLoader loader = menuViewController.adicionarAba("/view/EditSightingPane.fxml", "Editar Avistamento");
		
		if( loader != null) {
			 //EditS controller = loader.getController();
	         //controller.setDados(criptideo, menuViewController);
		}
	}
    
}

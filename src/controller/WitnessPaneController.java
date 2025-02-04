package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import model.enums.Genero;
import persistence.TestemunhaDAO;
import util.WindowsUtil;
import model.Testemunha;

public class WitnessPaneController {
	
	 private MenuViewController menuViewController;
	 private Testemunha testemunha;
	
	
	@FXML
	private Label lblNome;
	
	@FXML
	private Label lblSobrenome;
	
	@FXML
	private Label lblGenero;
	
	@FXML
	private Label lblTelefone;
	
	@FXML
	private Label lblEmail;
	
	@FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnEditar;
	
	public void setDados(Testemunha testemunha, MenuViewController menuViewController){
		this.testemunha = testemunha;
		this.menuViewController = menuViewController;
		
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
				
		if(testemunha.getTelefone() != null)
			lblTelefone.setText(testemunha.getTelefone());
			
		if(testemunha.getEmail() != null)
			lblEmail.setText(testemunha.getEmail());
	}
	
	@FXML
    void onBtnExcluirAction() {
    	WindowsUtil windowsUtil = new WindowsUtil();
    	boolean resposta = windowsUtil.mostrarAlertaConfirmacao("Quer mesmo apagar esse avistamento?");
    	    	
    	if (!resposta)
    		return;
    	
		TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
		testemunhaDAO.excluirTestemunha(testemunha.getIdTestemunha());
    }
    
    @FXML
    void onBtnEditarAction() {
		FXMLLoader loader = menuViewController.adicionarAba("/view/EditWitnessPane.fxml", "Editar Testemunha");
		
		if( loader != null) {
			 EditWitnessPaneController controller = loader.getController();
	         controller.setDados(testemunha, menuViewController);
		}
	}
	
	
}

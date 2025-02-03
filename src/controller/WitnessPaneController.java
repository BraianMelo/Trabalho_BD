package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import model.enums.Genero;
import model.Testemunha;

public class WitnessPaneController {
	
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
	
	public void setDados(Testemunha testemunha){
		
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
        System.out.println("Excluir");
    }
    
    @FXML
    void onBtnEditarAction() {
		System.out.println("Editar");
		//menuViewController.adicionarAbaEdicao(criptideo);
	}
	
	
}

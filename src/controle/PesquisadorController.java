package controle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.Pesquisador;

public class PesquisadorController {
	
	
	 private MenuController menuController;
	 private TestemunhaController testemunhaController;
	 private Pesquisador pesquisador;
	
	
	@FXML
	private Label lblAreaAtuacao;
	
	@FXML
	private Label lblInstituicao;
	
	@FXML
	private Button btnExcluir;
	
	public void setDados(Pesquisador pesquisador, TestemunhaController testemunhaController, MenuController menuController) {
		this.menuController = menuController;
		this.testemunhaController = testemunhaController;
		this.pesquisador = pesquisador;
		
		lblAreaAtuacao.setText(pesquisador.getAreaAtuacao());
		lblInstituicao.setText(pesquisador.getInstituicao());
	}
	
	@FXML
	private void onBtnExcluirAction() {
		System.out.println("Excluir");
	}
	
	@FXML
	private void onBtnEditarAction() {
		System.out.println("Editar");
	}
	

}

package controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.Pesquisador;
import modelo.enums.ModeloAba;
import persistencia.PesquisadorDAO;

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
		PesquisadorDAO pesquisadorDAO = new PesquisadorDAO();
		pesquisadorDAO.excluir(pesquisador.getIdPesquisador());
		testemunhaController.carregarPesquisador();
	}
	
	@FXML
	private void onBtnEditarAction() {
		FXMLLoader loader = menuController.adicionarAba("/visao/EditarPesquisadorPane.fxml", "Editar Pesquisador");
		
		if( loader != null) {
			 EditarPesquisadorController controller = loader.getController();
	         controller.setDados(pesquisador, ModeloAba.EDITAR, testemunhaController, menuController);
		}
	}
	

}

package controle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import modelo.Pesquisador;
import modelo.enums.ModeloAba;
import persistencia.PesquisadorDAO;

public class EditarPesquisadorController extends EditarController{
	
	private TestemunhaController testemunhaController;
	private MenuController menuController;
	private Pesquisador pesquisador;
	
	@FXML
	private Label lblTitulo;
	
	@FXML
	private TextField txtfAreaAtuacao;
	
	@FXML
	private TextField txtfInstituicao;
	
	@FXML
	private ImageView imgBotao;
	
	public void setDados(Pesquisador pesquisador, ModeloAba modeloAba, TestemunhaController testemunhaController, MenuController menuViewController) {
		this.menuController = menuViewController;
		this.testemunhaController = testemunhaController;
		this.pesquisador = pesquisador;
		this.modeloAba = modeloAba;
		
		setImagemBotao(imgBotao);
		setLblTitulo(lblTitulo, "Pesquisador");
		
		setTextField(txtfAreaAtuacao, pesquisador.getAreaAtuacao());
		setTextField(txtfInstituicao, pesquisador.getInstituicao());
	}
	
	@FXML
	private void onBtnSalvarAction() {
		if(textFieldVazio(txtfAreaAtuacao) || textFieldVazio(txtfInstituicao)) {
			
			alertaController.mostrarAlerta(AlertType.ERROR, 
					"Há campos vazios!", 
					"'Area de Atuação' e 'Instituição' não aceitma campos vazios.");
			
			return;
		}
		
		pesquisador.setAreaAtuacao(txtfAreaAtuacao.getText());
		pesquisador.setInstituicao(txtfInstituicao.getText());
		
		PesquisadorDAO pesquisadorDAO = new PesquisadorDAO();
		
		if(modeloAba.equals(ModeloAba.ADICIONAR)) {
			pesquisadorDAO.inserir(pesquisador);
			
		} else {
			pesquisadorDAO.atualizar(pesquisador);
			
		}
		
		testemunhaController.carregarPesquisador();
		menuController.fecharAba();
	}
	
	
	

}

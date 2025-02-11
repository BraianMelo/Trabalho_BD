package controle;

import app.Aplicacao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Pesquisador;
import modelo.enums.ModeloAba;
import persistencia.PesquisadorDAO;

public class EditarPesquisadorController extends Controller{
	
	private TestemunhaController testemunhaController;
	private MenuController menuController;
	private Pesquisador pesquisador;
	private ModeloAba modelo;
	
	@FXML
	private TextField txtfAreaAtuacao;
	
	@FXML
	private TextField txtfInstituicao;
	
	@FXML
	private ImageView imgBotao;
	
	public void setDados(Pesquisador pesquisador, ModeloAba modelo, TestemunhaController testemunhaController, MenuController menuViewController) {
		this.menuController = menuViewController;
		this.testemunhaController = testemunhaController;
		this.pesquisador = pesquisador;

		this.modelo = modelo;
		
		if(modelo.equals(ModeloAba.ADICIONAR)) {
			Image icone = new Image(Aplicacao.class.getResourceAsStream("/visao/imagens/Icone_Adicionar.png"));
			imgBotao.setImage(icone);
			return;
			
		}
		
		setTextField(txtfAreaAtuacao, pesquisador.getAreaAtuacao());
		setTextField(txtfInstituicao, pesquisador.getInstituicao());
	}
	
	@FXML
	private void onBtnSalvarAction() {
		if(textFieldVazio(txtfAreaAtuacao) || textFieldVazio(txtfInstituicao)) {
			
			mostrarAlerta(AlertType.ERROR, 
					"Há campos vazios!", 
					"'Area de Atuação' e 'Instituição' não aceitma campos vazios.");
			
			return;
		}
		
		pesquisador.setAreaAtuacao(txtfAreaAtuacao.getText());
		pesquisador.setInstituicao(txtfInstituicao.getText());
		
		PesquisadorDAO pesquisadorDAO = new PesquisadorDAO();
		
		if(modelo.equals(ModeloAba.ADICIONAR)) {
			pesquisadorDAO.inserir(pesquisador);
			
		} else {
			pesquisadorDAO.atualizar(pesquisador);
			
		}
		
		testemunhaController.carregarPesquisador();
		menuController.fecharAba();
	}
	
	
	

}

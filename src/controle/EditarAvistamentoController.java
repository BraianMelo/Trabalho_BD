package controle;

import app.Aplicacao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Avistamento;
import modelo.enums.ModeloAba;
import persistencia.AvistamentoDAO;
import persistencia.CriptideoAvistamentoDAO;

public class EditarAvistamentoController extends Controller{
	
	private MenuController menuController;
	private InformacoesCriptideoController infoCriptideoController;
	private Avistamento avistamento;
	private Integer idCriptideo;
	private ModeloAba modelo;
	
	@FXML
	private TextField txtfLocal;
	
	@FXML
	private DatePicker dtpData;
	
	@FXML
	private TextField txtfPais;
	
	@FXML
	private ImageView imgBotao;
	
	public void setDados(Avistamento avistamento, Integer idCriptideo, ModeloAba modelo, InformacoesCriptideoController cryptidInformationPaneController, MenuController menuViewController) {
		this.infoCriptideoController = cryptidInformationPaneController;
		this.menuController = menuViewController;
		this.avistamento = avistamento;
		this.idCriptideo = idCriptideo;
		this.modelo = modelo;
		
		if(modelo.equals(ModeloAba.ADICIONAR)) {
			Image icone = new Image(Aplicacao.class.getResourceAsStream("/visao/imagens/Icone_Adicionar.png"));
			imgBotao.setImage(icone);
			return;
		}
		
		setTextField(txtfLocal, avistamento.getLocal());
		setTextField(txtfPais, avistamento.getPais());
		dtpData.setValue(avistamento.getData());
	}
	
	@FXML
	private void onBtnSalvarAction() {
		if(textFieldVazio(txtfLocal) || textFieldVazio(txtfPais) || dtpData.getValue().toString().equals("")) {
			
			mostrarAlerta(AlertType.ERROR, 
					"Há campos vazios!", 
					"'Avistamento' não aceita campos vazios.");
			
			return;
		}
		
		
		avistamento.setLocal(txtfLocal.getText());
		avistamento.setData(dtpData.getValue());
		avistamento.setPais(txtfPais.getText());
		
		AvistamentoDAO avistamentoDAO = new AvistamentoDAO();
		CriptideoAvistamentoDAO caDAO = new CriptideoAvistamentoDAO();
		
		if(modelo.equals(ModeloAba.ADICIONAR)) {
			avistamentoDAO.inserir(avistamento);
			caDAO.inserirRelacao(idCriptideo, avistamento.getIdAvistamento());
		
		} else {
			avistamentoDAO.atualizar(avistamento);
		}
		
		infoCriptideoController.carregarGridAvistamentos();
		infoCriptideoController.reportarAlteracao();
		menuController.fecharAba();
		
		
	}

}

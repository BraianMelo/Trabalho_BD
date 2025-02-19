package controle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import modelo.Avistamento;
import modelo.enums.ModeloAba;
import persistencia.AvistamentoDAO;
import persistencia.CriptideoAvistamentoDAO;

public class EditarAvistamentoController extends EditarController{
	
	private MenuController menuController;
	private InformacoesCriptideoController infoCriptideoController;
	private Avistamento avistamento;
	private Integer idCriptideo;
	
	@FXML
	private Label lblTitulo;
	
	@FXML
	private TextField txtfLocal;
	
	@FXML
	private DatePicker dtpData;
	
	@FXML
	private TextField txtfPais;
	
	@FXML
	private ImageView imgBotao;
	
	public void setDados(Avistamento avistamento, Integer idCriptideo, ModeloAba modeloAba, InformacoesCriptideoController cryptidInformationPaneController, MenuController menuViewController) {
		this.infoCriptideoController = cryptidInformationPaneController;
		this.menuController = menuViewController;
		this.avistamento = avistamento;
		this.idCriptideo = idCriptideo;
		this.modeloAba = modeloAba;
		
		setImagemBotao(imgBotao);
		setLblTitulo(lblTitulo, "Avistamento");
		
		setTextField(txtfLocal, avistamento.getLocal());
		setTextField(txtfPais, avistamento.getPais());
		dtpData.setValue(avistamento.getData());
	}
	
	@FXML
	private void onBtnSalvarAction() {
		if(textFieldVazio(txtfLocal) || textFieldVazio(txtfPais) || dtpData.getValue() == null) {
			
			alertaController.mostrarAlerta(AlertType.ERROR, 
					"Há campos vazios!", 
					"'Avistamento' não aceita campos vazios.");
			
			return;
		}
		
		avistamento.setLocal(txtfLocal.getText());
		avistamento.setData(dtpData.getValue());
		avistamento.setPais(txtfPais.getText());
		
		AvistamentoDAO avistamentoDAO = new AvistamentoDAO();
		CriptideoAvistamentoDAO caDAO = new CriptideoAvistamentoDAO();
		
		if(modeloAba.equals(ModeloAba.ADICIONAR)) {
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

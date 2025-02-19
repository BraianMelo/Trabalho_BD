package controle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import modelo.Criptideo;
import modelo.CriptideoConfirmado;
import modelo.enums.ModeloAba;
import modelo.enums.StatusCriptideo;
import persistencia.CriptideoConfirmadoDAO;
import persistencia.CriptideoDAO;

public class EditarCriptideoRealController extends EditarController{
	
	private MenuController menuController;
	private InformacoesCriptideoController infoCripController;
	private CriptideoConfirmado cripConfirmado;
	
	@FXML
	private Label lblTitulo;
	
	@FXML
	private TextField txtfNomeCientifico;
	
	@FXML
	private DatePicker dtpDataConfirmacao;
	
	@FXML
	private TextField txtfFonte;
	
	@FXML
	private TextField txtfObservacoes;
	
	@FXML
	private ImageView imgBotao;
	
	public void setDados(CriptideoConfirmado cripConfirmado, ModeloAba modeloAba,  InformacoesCriptideoController infoCripController, MenuController menuController) {
		this.menuController = menuController;
		this.infoCripController = infoCripController;
		this.cripConfirmado = cripConfirmado;
		this.modeloAba = modeloAba;
		
		setImagemBotao(imgBotao);
		setLblTitulo(lblTitulo, "Criptídeo Real");
		
		setTextField(txtfNomeCientifico, cripConfirmado.getNomeCientifico());
		setTextField(txtfFonte, cripConfirmado.getFonte());
		setTextField(txtfObservacoes, cripConfirmado.getObservacoes());
		dtpDataConfirmacao.setValue(cripConfirmado.getDataConfirmacao());
	}
	
	@FXML
	private void onBtnSalvarAction() {
		if(textFieldVazio(txtfNomeCientifico) || textFieldVazio(txtfFonte) ||  dtpDataConfirmacao.getValue().toString() == null) {
			
			alertaController.mostrarAlerta(AlertType.ERROR, 
					"Há campos vazios!", 
					"'Nome cientídico', 'Fonte' e 'Data confirmação' não aceitma campos vazios.");
			
			return;
		}
		
		cripConfirmado.setNomeCientifico(txtfNomeCientifico.getText());
		cripConfirmado.setFonte(txtfFonte.getText());
		cripConfirmado.setObservacoes(txtfObservacoes.getText());
		cripConfirmado.setDataConfirmacao(dtpDataConfirmacao.getValue());
		
		CriptideoConfirmadoDAO cripConfirmadoDAO = new CriptideoConfirmadoDAO();
		
		if(modeloAba.equals(ModeloAba.ADICIONAR)) {
			CriptideoDAO criptideoDAO = new CriptideoDAO();
			
			Criptideo criptideo = criptideoDAO.consultarPorId(cripConfirmado.getIdCriptideo());
			criptideo.setStatusCr(StatusCriptideo.CONFIRMADO);
			
			criptideoDAO.atualizar(criptideo);
			cripConfirmadoDAO.inserir(cripConfirmado);
		
			infoCripController.carregarCriptideoConfirmadoPane(StatusCriptideo.CONFIRMADO);
			infoCripController.reportarAlteracao();
			menuController.carregarGridCriptideos();
			

			alertaController.mostrarAlerta(AlertType.INFORMATION,
					"Você precisa adicionar um pesquisador ao criptídeo confirmado!",
					"Caso contrário, o criptídeo confirmado será apagado!");
			
		} else {
			cripConfirmadoDAO.atualizar(cripConfirmado);
			infoCripController.carregarCriptideoConfirmadoPane(StatusCriptideo.CONFIRMADO);
			menuController.carregarGridCriptideos();
			
		}
		
		menuController.fecharAba();
	}

}

package controle;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Criptideo;
import modelo.CriptideoConfirmado;
import modelo.enums.ModeloAba;
import modelo.enums.StatusCriptideo;
import persistencia.CriptideoConfirmadoDAO;
import persistencia.CriptideoDAO;
import utilitario.Utilitario;

public class EditarCriptideoRealController {
	
	private MenuController menuController;
	private InformacoesCriptideoController infoCripController;
	private CriptideoConfirmado cripConfirmado;
	private ModeloAba modelo;
	
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
	
	public void setDados(CriptideoConfirmado cripConfirmado, ModeloAba modelo,  InformacoesCriptideoController infoCripController, MenuController menuController) {
		this.menuController = menuController;
		this.infoCripController = infoCripController;
		this.cripConfirmado = cripConfirmado;
		this.modelo = modelo;
		
		if(modelo.equals(ModeloAba.ADICIONAR)) {
			Image icone = new Image(Utilitario.class.getResourceAsStream("/visao/imagens/Icone_Adicionar.png"));
			imgBotao.setImage(icone);
			return;
		}
		
		txtfNomeCientifico.setText(cripConfirmado.getNomeCientifico());
		txtfFonte.setText(cripConfirmado.getFonte());
		txtfObservacoes.setText(cripConfirmado.getObservacoes());
		dtpDataConfirmacao.setValue(cripConfirmado.getDataConfirmacao());
	}
	
	@FXML
	private void onBtnSalvarAction() {
		cripConfirmado.setNomeCientifico(txtfNomeCientifico.getText());
		cripConfirmado.setFonte(txtfFonte.getText());
		cripConfirmado.setObservacoes(txtfObservacoes.getText());
		cripConfirmado.setDataConfirmacao(dtpDataConfirmacao.getValue());
		
		CriptideoConfirmadoDAO cripConfirmadoDAO = new CriptideoConfirmadoDAO();
		
		if(modelo.equals(ModeloAba.ADICIONAR)) {
			CriptideoDAO criptideoDAO = new CriptideoDAO();
			
			Criptideo criptideo = criptideoDAO.consultarPorId(cripConfirmado.getIdCriptideo());
			criptideo.setStatusCr(StatusCriptideo.CONFIRMADO);
			
			System.out.println(criptideo);
			
			criptideoDAO.atualizar(criptideo);
			cripConfirmadoDAO.inserir(cripConfirmado);
		
			infoCripController.carregarCriptideoConfirmadoPane(StatusCriptideo.CONFIRMADO);
			infoCripController.reportarAlteracao();
			menuController.carregarGridCriptideos();
			
			Utilitario utils = new Utilitario();
			utils.mostrarAlertaAviso("Você precisa adicionar um pesquisador ao criptídeo confirmado!"
					+ "\nCaso contrário, o criptídeo confirmado será apagado!");
			
		} else {
			cripConfirmadoDAO.atualizar(cripConfirmado);
			infoCripController.carregarCriptideoConfirmadoPane(StatusCriptideo.CONFIRMADO);
			menuController.carregarGridCriptideos();
			
		}
		
		menuController.fecharAba();
	}

}

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Avistamento;
import model.enums.ModeloAba;
import persistence.AvistamentoDAO;
import persistence.CriptideoAvistamentoDAO;

public class EditSightingPaneController {
	
	private MenuViewController menuViewController;
	private CryptidInformationPaneController cryptidInformationPaneController;
	private Avistamento avistamento;
	private Integer idCriptideo;
	private ModeloAba modelo;
	
	@FXML
	private TextField txtfLocal;
	
	@FXML
	private DatePicker dtpData;
	
	@FXML
	private TextField txtfPais;
	
	public void setDados(Avistamento avistamento, Integer idCriptideo, ModeloAba modelo, CryptidInformationPaneController cryptidInformationPaneController, MenuViewController menuViewController) {
		this.cryptidInformationPaneController = cryptidInformationPaneController;
		this.menuViewController = menuViewController;
		this.avistamento = avistamento;
		this.idCriptideo = idCriptideo;
		this.modelo = modelo;
		
		if(modelo.equals(ModeloAba.ADICIONAR))
			return;
		
		txtfLocal.setText(avistamento.getLocal());
		dtpData.setValue(avistamento.getData());
		txtfPais.setText(avistamento.getPais());
	}
	
	@FXML
	private void onBtnSalvarAction() {
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
		
		cryptidInformationPaneController.carregarGridAvistamentos();
		cryptidInformationPaneController.reportarAlteracao();
		menuViewController.fecharAba();
		
		
	}

}

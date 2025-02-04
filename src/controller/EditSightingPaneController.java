package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Avistamento;
import persistence.AvistamentoDAO;

public class EditSightingPaneController {
	
	private MenuViewController menuViewController;
	private Avistamento avistamento;
	
	@FXML
	private TextField txtfLocal;
	
	@FXML
	private DatePicker dtpData;
	
	@FXML
	private TextField txtfPais;
	
	public void setDados(Avistamento avistamento, MenuViewController menuViewController) {
		this.menuViewController = menuViewController;
		this.avistamento = avistamento;
		
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
		avistamentoDAO.atualizar(avistamento);
		
		menuViewController.fecharAba();
		
		
	}

}

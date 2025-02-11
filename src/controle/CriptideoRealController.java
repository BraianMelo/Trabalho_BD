package controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import modelo.Criptideo;
import modelo.CriptideoConfirmado;
import modelo.enums.ModeloAba;
import modelo.enums.StatusCriptideo;
import persistencia.CriptideoConfirmadoDAO;
import persistencia.CriptideoDAO;

public class CriptideoRealController extends Controller{
	
	private MenuController menuController;
	private InformacoesCriptideoController infoCripController;
	private CriptideoConfirmado cripConfirmado;
	
    @FXML
    private Label lblNomeCientifico;

    @FXML
    private Label lblFonte;

    @FXML
    private Label lblDataDescoberta;

    @FXML
    private Text txtObservacoes;
    
    public void setDados(CriptideoConfirmado cripConfirmado, InformacoesCriptideoController infoCripController,  MenuController menuController) {
    	this.cripConfirmado = cripConfirmado;
    	this.infoCripController = infoCripController;
    	this.menuController = menuController;
    	
    	lblNomeCientifico.setText(cripConfirmado.getNomeCientifico());
    	lblFonte.setText(cripConfirmado.getFonte());
    	lblDataDescoberta.setText(cripConfirmado.getDataConfirmacao().toString());
    	txtObservacoes.setText(cripConfirmado.getObservacoes());
    }
    
    @FXML
    private void onBtnExcluirAction() {
    	boolean resposta = mostrarAlertaConfirmacao("Quer mesmo apagar os dados de confirmação?");
    	
    	if (!resposta)
    		return;
    	
    	CriptideoConfirmadoDAO cripConfirmadoDAO = new CriptideoConfirmadoDAO();
    	CriptideoDAO criptideoDAO = new CriptideoDAO();
    	
    	Criptideo criptideo = criptideoDAO.consultarPorId(cripConfirmado.getIdCriptideo());
    	criptideo.setStatusCr(StatusCriptideo.AVISTADO);
    	criptideoDAO.atualizar(criptideo);
    	
    	System.out.println(criptideo);
    	
    	cripConfirmadoDAO.excluir(cripConfirmado.getIdConfirmado());
    	infoCripController.carregarCriptideoConfirmadoPane(StatusCriptideo.AVISTADO);
    	menuController.carregarGridCriptideos();
    }
    
    @FXML
    private void onBtnEditarAction() {
		FXMLLoader loader = menuController.adicionarAba("/visao/EditarCriptideoRealPane.fxml", "Editar Criptideo Confirmado");
		
		if( loader != null) {
			 EditarCriptideoRealController controller = loader.getController();
	         controller.setDados(cripConfirmado, ModeloAba.EDITAR, infoCripController, menuController);
		}
	}
}

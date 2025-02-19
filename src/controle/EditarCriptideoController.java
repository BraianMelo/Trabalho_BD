package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import modelo.Criptideo;
import modelo.enums.ModeloAba;
import modelo.enums.StatusCriptideo;
import modelo.enums.Tipo;
import persistencia.CriptideoDAO;

public class EditarCriptideoController extends EditarController {
	
	private MenuController menuController;
	private Criptideo criptideo;
	
	@FXML
	private Label lblTitulo;

    @FXML
    private TextField txtfNomeCriptideo;

    @FXML
    private MenuButton mbtnTipo;

    @FXML
    private MenuButton mbtnStatus;

    @FXML
    private TextField txtfCaminhoFoto;
    
    @FXML 
    private TextField txtfDescricao; 
	
    @FXML
    private Button btnSalvar;
    
	@FXML
	private ImageView imgBotao;
    
	public void setDados(Criptideo criptideo, ModeloAba modeloAba, MenuController menuController) {
		this.menuController = menuController;
		this.criptideo = criptideo;
		this.modeloAba = modeloAba;
		
		setImagemBotao(imgBotao);
		setLblTitulo(lblTitulo, "Criptídeo");
		
		setTextField(txtfNomeCriptideo, criptideo.getNome());
		setTextField(txtfDescricao, criptideo.getDescricao());
		setTextField(txtfCaminhoFoto, criptideo.getImagemCaminho());
		
		if(criptideo.getStatusCr() == null) {
			criptideo.setStatusCr(StatusCriptideo.AVISTADO);
		}
		selecionarMenuItem(mbtnStatus, criptideo.getStatusCr().ordinal());
		
		if(criptideo.getTipo() == null) {
			criptideo.setTipo(Tipo.TERRESTRE);
		}
		selecionarMenuItem(mbtnTipo, criptideo.getTipo().ordinal());
	}
	
	private void selecionarMenuItem(MenuButton menuButton, int valor) {
		if (valor >= 0 && valor < menuButton.getItems().size()) {
			menuButton.setText(menuButton.getItems().get(valor).getText());
		}
	}

    // Evento para alterar o texto do MenuButton de tipo do Criptídeo
    @FXML
    private void onMbtnTipoAction(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        mbtnTipo.setText(selectedItem.getText());
    }

    // Evento para alterar o texto do MenuButton de status do Criptídeo
    @FXML
    private void onMbtnStatusAction(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        mbtnStatus.setText(selectedItem.getText());
    }
    
    @FXML
    private void onBtnSalvarAction(ActionEvent event) {
    	
        if(textFieldVazio(txtfNomeCriptideo)) {
        	alertaController.mostrarAlerta(AlertType.ERROR, "Campo vazio!",
        			"O campo 'nome' não pode ficar vazio");
        	return;
        }
        
    	criptideo.setNome(getTextField(txtfNomeCriptideo));
        criptideo.setTipo(Tipo.valueOf(mbtnTipo.getText().toUpperCase()));
        criptideo.setStatusCr(StatusCriptideo.valueOf(mbtnStatus.getText().toUpperCase()));
        criptideo.setImagemCaminho(getTextField(txtfCaminhoFoto));
        criptideo.setDescricao(getTextField(txtfDescricao));
        
        CriptideoDAO criptideoDAO = new CriptideoDAO();
        
        if(modeloAba.equals(ModeloAba.ADICIONAR)) {
        	criptideoDAO.inserir(criptideo);
        	menuController.addCriptideoAlterado(criptideo.getIdCriptideo());
        	
        	alertaController.mostrarAlerta(AlertType.WARNING,
        			"Criptideo adicionado!", 
        			"Caso você não adicione nenhum avistamento e nenhuma\n"
        			+ "testemunha ao criptídeo, ele será apagado!");
        	
        } else {
            criptideoDAO.atualizar(criptideo);
        }

        menuController.carregarGridCriptideos();
        menuController.fecharAba();

    }
}

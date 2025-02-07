package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Criptideo;
import modelo.enums.ModeloAba;
import modelo.enums.StatusCriptideo;
import modelo.enums.Tipo;
import persistencia.CriptideoDAO;
import utilitario.Utilitario;

public class EditarCriptideoController {
	
	private MenuController menuController;
	private Criptideo criptideo;
	private ModeloAba modelo;

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
    
	public void setDados(Criptideo criptideo, ModeloAba modelo, MenuController menuController) {
		this.menuController = menuController;
		this.criptideo = criptideo;
		this.modelo = modelo;
		
		if(modelo.equals(ModeloAba.ADICIONAR)) {
			Image icone = new Image(Utilitario.class.getResourceAsStream("/visao/imagens/Icone_Adicionar.png"));
			imgBotao.setImage(icone);
			return;
		}
		
		txtfNomeCriptideo.setText(criptideo.getNome());
		txtfCaminhoFoto.setText(criptideo.getImagemCaminho());
		txtfDescricao.setText(criptideo.getDescricao());
		selecionarMenuItem(mbtnStatus, criptideo.getStatusCr().ordinal());
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
        criptideo.setNome(txtfNomeCriptideo.getText());
        criptideo.setTipo(Tipo.valueOf(mbtnTipo.getText().toUpperCase()));
        criptideo.setStatusCr(StatusCriptideo.valueOf(mbtnStatus.getText().toUpperCase()));
        
        if(txtfCaminhoFoto.getText() != null)
        	criptideo.setImagemCaminho(txtfCaminhoFoto.getText());
        else
        	criptideo.setImagemCaminho(null);
        
        criptideo.setDescricao(txtfDescricao.getText());
        if(criptideo.getDescricao() == "" ) {
        	criptideo.setDescricao(null);
        }
        
        CriptideoDAO criptideoDAO = new CriptideoDAO();
        
        if(modelo.equals(ModeloAba.ADICIONAR)) {
        	criptideoDAO.inserir(criptideo);
        	menuController.addCriptideoAlterado(criptideo.getIdCriptideo());
        	
        } else {
            criptideoDAO.atualizar(criptideo);
        }

        menuController.carregarGridCriptideos();
        menuController.fecharAba();

    }

	
}

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

import model.Criptideo;
import persistence.CriptideoDAO;
import controller.MenuViewController;

public class CryptidPaneController {
	
	private Criptideo criptideo;
	private MenuViewController menuViewController = null;

    // Labels para exibir informações sobre o criptídeo
    @FXML
    private Label lblNome;

    @FXML
    private Label lblTipo;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblDescricao;

    // Pane e ImageView para a imagem do criptídeo
    @FXML
    private StackPane paneImagem;

    @FXML
    private ImageView imagemRedonda;
    
    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnEditar;

    // Inicialização do controlador
    public void initialize() {
        configurarImagemRedonda();
    }

    // Configura a borda arredondada da imagem
    private void configurarImagemRedonda() {
        Rectangle clip = new Rectangle(120, 120);
        clip.setArcWidth(25);  // Raio das bordas arredondadas
        clip.setArcHeight(25); // Raio das bordas arredondadas
        imagemRedonda.setClip(clip); // Aplica o recorte arredondado na imagem
    }
    
    @FXML
    void onBtnEditarAction() {
		menuViewController.adicionarAba(criptideo);
	}
    
    //TODO: Excluir Criptideo
    @FXML
    void onBtnExcluirAction() {
        // Criando um alerta de confirmação
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Deseja excluir " + lblNome.getText() + "?");
        alert.setContentText("Escolha OK para continuar ou Cancelar para sair.");

        // Exibindo o alerta e aguardando a resposta
        Optional<ButtonType> result = alert.showAndWait();

        // Verificando a resposta
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
				CriptideoDAO criptideoDAO = new CriptideoDAO();
				criptideoDAO.excluir(criptideo.getIdCriptideo());
				System.out.println("Exclusão realizada com sucesso.");
				
			} catch (Exception e) {
				System.err.println("Erro ao excluir: " + e.getMessage());
				
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setTitle("Erro");
				errorAlert.setHeaderText("Não foi possível excluir o " + lblNome.getText());
				errorAlert.setContentText(e.getMessage());
				errorAlert.showAndWait();
			}
        } 
    }
    
    
    //TODO: Editar Criptideo

    // Método auxiliar para formatar o texto do Enum
    private String formatarEnum(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    // Método para definir os dados do criptídeo
    public void setDados(Criptideo criptideo, MenuViewController menuViewController) {
		this.menuViewController = menuViewController;
        this.criptideo = criptideo;
        
        lblNome.setText(criptideo.getNome());
        lblTipo.setText(formatarEnum(criptideo.getTipo().toString()));
        lblStatus.setText(formatarEnum(criptideo.getStatusCr().toString()));
        lblDescricao.setText(criptideo.getDescricao());
        
        // Adiciona a imagem ao ImageView
        if (criptideo.getImagemCaminho() != null && !criptideo.getImagemCaminho().isEmpty()) {
            Image imagem = new Image("file:" + criptideo.getImagemCaminho());
            imagemRedonda.setImage(imagem); // Define a imagem no ImageView
        }
    }
}

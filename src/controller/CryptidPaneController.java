package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;

import java.io.File;
import java.util.List; 
import java.util.ArrayList;   

import util.WindowsUtil;
import model.Criptideo;
import model.Avistamento;
import model.Testemunha;
import persistence.TestemunhaDAO;
import persistence.AvistamentoTestemunhaDAO;
import persistence.CriptideoDAO;
import persistence.AvistamentoDAO;
import persistence.CriptideoAvistamentoDAO;
import controller.MenuViewController;

public class CryptidPaneController {
	
	private Criptideo criptideo;
	private MenuViewController menuViewController = null;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTipo;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblDescricao;

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
			File arquivoImagem = new File(criptideo.getImagemCaminho());

			if (arquivoImagem.exists()) { // Verifica se o arquivo realmente existe
				Image imagem = new Image(arquivoImagem.toURI().toString());
				imagemRedonda.setImage(imagem); // Define a imagem no ImageView
			} else {
				imagemRedonda.setImage(new Image("/view/images/Icone_Sem_Imagem.png"));
			}
		}
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
		menuViewController.adicionarAbaEdicao(criptideo);
	}
    
    //TODO: Excluir Criptideo
    @FXML
    void onBtnExcluirAction() {
		WindowsUtil windows = new WindowsUtil();
		boolean confirmacao = windows.mostrarAlertaConfirmacao("Excluir "+criptideo.getNome()); 
        if (confirmacao) {
            try {
				CriptideoDAO criptideoDAO = new CriptideoDAO();
				criptideoDAO.excluir(criptideo.getIdCriptideo());
				System.out.println("Exclusão realizada com sucesso.");
				
			} catch (Exception e) {
				System.err.println("Erro ao excluir: " + e.getMessage());
				windows.mostrarAlertaErro("Não foi possível excluir o "+ criptideo.getNome());
			}
        } 
    }
    
	@FXML
	private void onBtnInformacaoAction() {
		CriptideoAvistamentoDAO caDAO = new CriptideoAvistamentoDAO();

		List<Integer> idsAvistamentos = caDAO.buscarIdsAvistamentosPorCriptideo(criptideo.getIdCriptideo());
		menuViewController.adicionarAbaInformacao(criptideo, idsAvistamentos);
		
	}



    // Método auxiliar para formatar o texto do Enum
    private String formatarEnum(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}

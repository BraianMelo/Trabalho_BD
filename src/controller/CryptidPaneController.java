package controller;

import java.io.File;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import model.Criptideo;
import persistence.AvistamentoDAO;
import persistence.AvistamentoTestemunhaDAO;
import persistence.CriptideoAvistamentoDAO;
import persistence.CriptideoDAO;
import persistence.TestemunhaDAO;
import util.WindowsUtil;

public class CryptidPaneController {
	
	private Criptideo criptideo;
	private MenuViewController menuViewController;

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
    private void onBtnEditarAction() {
		FXMLLoader loader = menuViewController.adicionarAba("/view/EditCryptidPane.fxml", "Editar "+ criptideo.getNome());
		
		if( loader != null) {
			 EditCryptidPaneController controller = loader.getController();
	         controller.setDados(criptideo, menuViewController);
		}
		
	}
    
    //TODO: Excluir Criptideo
    @FXML
    private void onBtnExcluirAction() {
		WindowsUtil windows = new WindowsUtil();
		boolean resposta = windows.mostrarAlertaConfirmacao("Excluir "+criptideo.getNome()); 
        
		if (resposta) {
				CriptideoDAO criptideoDAO = new CriptideoDAO();
				CriptideoAvistamentoDAO caDAO = new CriptideoAvistamentoDAO();
				AvistamentoDAO avistamentoDAO = new AvistamentoDAO();
		        AvistamentoTestemunhaDAO atDAO = new AvistamentoTestemunhaDAO();
		        TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
				
				List<Integer> idsAvistamento = caDAO.buscarIdsAvistamentosPorCriptideo(criptideo.getIdCriptideo());
				
				for(Integer idAvistamento: idsAvistamento) {
			        
			        List<Integer> idsTestemunhas = atDAO.buscarIdsTestemunhasPorAvistamento(idAvistamento);
			        
			        for(Integer idTestemunha: idsTestemunhas) {
			        	atDAO.excluirRelacao(idAvistamento, idTestemunha);
			        	testemunhaDAO.excluirTestemunha(idTestemunha);
			        }
			        
			        avistamentoDAO.excluir(idAvistamento);
				}
				
				criptideoDAO.excluir(criptideo.getIdCriptideo());
				
				
				menuViewController.carregarGridCriptideos();
				new WindowsUtil().mostrarAlertaMensagem("Criptídeo apagado", "O criptídeo foi apagado");
        } 
    }
    
	@FXML
	private void onBtnInformacaoAction() {
		CriptideoAvistamentoDAO caDAO = new CriptideoAvistamentoDAO();

		List<Integer> idsAvistamentos = caDAO.buscarIdsAvistamentosPorCriptideo(criptideo.getIdCriptideo());
		FXMLLoader loader = menuViewController.adicionarAba("/view/CryptidInformationsPane.fxml", criptideo.getNome());
		
        CryptidInformationPaneController controller = loader.getController();
        controller.setDados(criptideo, idsAvistamentos, menuViewController);
	}



    // Método auxiliar para formatar o texto do Enum
    private String formatarEnum(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}

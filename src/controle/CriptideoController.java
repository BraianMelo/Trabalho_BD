package controle;

import java.io.File;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import modelo.Criptideo;
import modelo.enums.ModeloAba;
import persistencia.AvistamentoDAO;
import persistencia.AvistamentoTestemunhaDAO;
import persistencia.CriptideoAvistamentoDAO;
import persistencia.CriptideoDAO;
import persistencia.TestemunhaDAO;

public class CriptideoController extends Controller{
	
	private Criptideo criptideo;
	private MenuController menuController;

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

    public void initialize() {
        configurarImagemRedonda();
    }
	
    public void setDados(Criptideo criptideo, MenuController menuViewController) {
		this.menuController = menuViewController;
        this.criptideo = criptideo;
        
        lblNome.setText(criptideo.getNome());
        lblTipo.setText(formatarEnum(criptideo.getTipo().toString()));
        lblStatus.setText(formatarEnum(criptideo.getStatusCr().toString()));
        lblDescricao.setText(criptideo.getDescricao());
        
		// Adiciona a imagem ao ImageView
		if (criptideo.getImagemCaminho() != null && !criptideo.getImagemCaminho().isEmpty()) {
			File arquivoImagem = new File(criptideo.getImagemCaminho());

			if (arquivoImagem.exists()) {
				Image imagem = new Image(arquivoImagem.toURI().toString());
				imagemRedonda.setImage(imagem);
				
			} else {
				imagemRedonda.setImage(new Image("/visao/imagens/Icone_Sem_Imagem.png"));
			
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
		FXMLLoader loader = menuController.adicionarAba("/visao/EditarCriptideoPane.fxml", "Editar "+ criptideo.getNome());
		
		if( loader != null) {
			 EditarCriptideoController controller = loader.getController();
	         controller.setDados(criptideo, ModeloAba.EDITAR, menuController);
		}
		
	}
    
    @FXML
    private void onBtnExcluirAction() {
		boolean resposta =  mostrarAlertaConfirmacao("Excluir "+criptideo.getNome()); 
        
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
			        	testemunhaDAO.excluir(idTestemunha);
			        }
			        
			        avistamentoDAO.excluir(idAvistamento);
				}
				
				criptideoDAO.excluir(criptideo.getIdCriptideo());
				
				
				menuController.carregarGridCriptideos();
				mostrarAlerta(AlertType.INFORMATION, 
						"Criptídeo apagado", 
						"O criptídeo foi apagado");
        } 
    }
    
	@FXML
	private void onBtnInformacaoAction() {
		FXMLLoader loader = menuController.adicionarAba("/visao/InformacoesCriptideoPane.fxml", criptideo.getNome());
		
        InformacoesCriptideoController controller = loader.getController();
        controller.setDados(criptideo, menuController);
	}
}

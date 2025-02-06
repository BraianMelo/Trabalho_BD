package controle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import modelo.Avistamento;
import modelo.Criptideo;
import modelo.enums.ModeloAba;
import persistencia.AvistamentoDAO;
import persistencia.CriptideoAvistamentoDAO;

public class InformacoesCriptideoController {
	
	private MenuController menuController;
	private Criptideo criptideo;

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
    private VBox vboxGrid;

    public void initialize() {
        configurarImagemRedonda();
    }

    public void setDados(Criptideo criptideo, MenuController menuViewController) {
		this.menuController = menuViewController;
		this.criptideo = criptideo;
		
    	adiocionarCriptideo(criptideo);
		carregarGridAvistamentos();
    }

    private void configurarImagemRedonda() {
        Rectangle clip = new Rectangle(120, 120);
        clip.setArcWidth(25);  // Raio das bordas arredondadas
        clip.setArcHeight(25); // Raio das bordas arredondadas
        imagemRedonda.setClip(clip); // Aplica o recorte arredondado na imagem
    }
    
    // Método auxiliar para formatar o texto do Enum
    private String formatarEnum(String texto) {
        if (texto == null || texto.isEmpty())
            return texto;
        
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
    
    private void adiocionarCriptideo(Criptideo criptideo){
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
    
    public void carregarGridAvistamentos(){
    	CriptideoAvistamentoDAO caDAO = new CriptideoAvistamentoDAO();
		AvistamentoDAO avistamentoDAO = new AvistamentoDAO();
		Avistamento avistamento;
		
		vboxGrid.getChildren().clear();
		
		List<Integer> idsAvistamentos = caDAO.buscarIdsAvistamentosPorCriptideo(criptideo.getIdCriptideo());
		
        int numeroAvistamento = 1;
        
        try {
			for (int idAvistamento : idsAvistamentos) {
				avistamento = avistamentoDAO.consultarPorId(idAvistamento);
				
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/AvistamentoPane.fxml"));
                Pane pane = loader.load();
                

                AvistamentoController controller = loader.getController();
                controller.setDados(avistamento, numeroAvistamento, this, menuController);

                vboxGrid.getChildren().add(pane);
                
                ++numeroAvistamento;
			}
 
        } catch (IOException e) {
            System.err.println("Erro ao carregar SightingPane.fxml: " + e.getMessage());
        }
    }
    
    @FXML
    private void onBtnAdicionarAvistamentoAction() {
		FXMLLoader loader = menuController.adicionarAba("/visao/EditarAvistamentoPane.fxml", "Editar Avistamento");
		
		if( loader != null) {
			 EditarAvistamentoController controller = loader.getController();
	         controller.setDados(new Avistamento(), criptideo.getIdCriptideo(), ModeloAba.ADICIONAR, this, menuController);
		}
    }

	public void reportarAlteracao() {
		menuController.addCriptideoAlterado(criptideo.getIdCriptideo());
		
	}

}

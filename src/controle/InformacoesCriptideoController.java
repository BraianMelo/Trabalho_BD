package controle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import modelo.Avistamento;
import modelo.Criptideo;
import modelo.CriptideoConfirmado;
import modelo.enums.ModeloAba;
import modelo.enums.StatusCriptideo;
import persistencia.AvistamentoDAO;
import persistencia.CriptideoAvistamentoDAO;
import persistencia.CriptideoConfirmadoDAO;

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
    private Button btnConfirmar;

    @FXML
    private VBox vboxGrid;
    
    @FXML
    private VBox vboxCriptideoReal;

    public void initialize() {
        configurarImagemRedonda();
    }

    public void setDados(Criptideo criptideo, MenuController menuViewController) {
		this.menuController = menuViewController;
		this.criptideo = criptideo;
		
    	adiocionarCriptideo(criptideo);
    	
    	carregarCriptideoConfirmadoPane(criptideo.getStatusCr());
    	
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
    
    public void carregarCriptideoConfirmadoPane(StatusCriptideo status) {
    	
    	if(status.equals(StatusCriptideo.AVISTADO)) {
    		if(vboxCriptideoReal.getChildren().size() == 1)
    			vboxCriptideoReal.getChildren().clear();
    		
    		lblStatus.setText(status.toString());
    		lblStatus.requestLayout();
    		
    		btnConfirmar.setDisable(false);
    		btnConfirmar.requestLayout();
    		
    		return;
    	}
    	
    	
    	try { 
    		CriptideoConfirmadoDAO cripConfimadoDAO = new CriptideoConfirmadoDAO();
    		CriptideoConfirmado cripConfirmado = cripConfimadoDAO.consultarPorIdCriptideo(criptideo.getIdCriptideo());
    	    		
    		vboxCriptideoReal.getChildren().clear();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/CriptideoRealPane.fxml"));
    		Pane pane = loader.load();
    		
    		CriptideoRealController cripRealController = loader.getController();
    		cripRealController.setDados(cripConfirmado, this, menuController);
    		
    		vboxCriptideoReal.getChildren().add(pane);
    		
    		btnConfirmar.setDisable(true);
    		btnConfirmar.requestLayout();
    		
    		lblStatus.setText(formatarEnum(status.toString()));
    		lblStatus.requestLayout();
    		
    		
    	} catch(Exception e) {
    		 System.err.println("Erro ao carregar CriptideoRealPane.fxml: " + e.getMessage());
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
            System.err.println("Erro ao abrir AvistamanentoPane.fxml: " + e.getMessage());
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
	
	@FXML
	private void onBtnConfirmarCriptideoAction() {
		FXMLLoader loader = menuController.adicionarAba("/visao/EditarCriptideoRealPane.fxml", "Confirmar Criptídeo");
		
		CriptideoConfirmado cripConfirmado = new CriptideoConfirmado();
		cripConfirmado.setIdCriptideo(criptideo.getIdCriptideo());
		
		if( loader != null) {
			 EditarCriptideoRealController controller = loader.getController();
	         controller.setDados(cripConfirmado, ModeloAba.ADICIONAR, this, menuController);
		}
	}

}

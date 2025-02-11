package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Criptideo;
import modelo.CriptideoConfirmado;
import modelo.enums.ModeloAba;
import modelo.enums.StatusCriptideo;
import persistencia.AvistamentoDAO;
import persistencia.AvistamentoTestemunhaDAO;
import persistencia.CriptideoAvistamentoDAO;
import persistencia.CriptideoConfirmadoDAO;
import persistencia.CriptideoDAO;
import persistencia.PesquisadorDAO;

public class MenuController extends Controller{
	
	private boolean modoDark = true;
	private Stage stage;
	private List<Integer> idsCriptideosAlterados = new ArrayList<>();

	@FXML
	private AnchorPane apMenuView;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private VBox vboxGrid;

    @FXML
    private Hyperlink hpBraianGithub;

    @FXML
    private Hyperlink hpGustavoGithub;

    @FXML
    private Hyperlink hpYuriGithub;

    @FXML
    private Hyperlink hpProjetoGithub;
    
    @FXML
    private ToggleButton tbtnDarkMode;
    
    @FXML
    private Slider sldTamanhoFont;
    
    @FXML
    public void initialize() {
        carregarGridCriptideos();
    }
    
    public void setDados(Stage stage) {
    	this.stage = stage;
    	
    	// sempre rodará esse método antes de fechar a janela:
    	 if (this.stage != null) {
             this.stage.setOnCloseRequest(event -> {
                 apagarEntidadeSemRelacoes();
             });
         }
    }

	public void carregarGridCriptideos() {
		CriptideoDAO criptideoDAO = new CriptideoDAO();
		List<Criptideo> listaCriptideos = criptideoDAO.listarTodos();

		vboxGrid.getChildren().clear(); 

		try {
			for (Criptideo criptideo : listaCriptideos) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/CriptideoPane.fxml"));
				Pane pane = loader.load();

				CriptideoController controller = loader.getController();
				controller.setDados(criptideo, this);

				vboxGrid.getChildren().add(pane);
			}
		} catch (IOException e) {
			System.err.println("Erro ao carregar os Criptídeos: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public FXMLLoader adicionarAba(String caminho, String titulo) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
			Node conteudo = loader.load();
			
			Tab novaAba = new Tab(titulo);
			
			novaAba.setContent(conteudo);
			
			tabPane.getTabs().add(novaAba);
			
			tabPane.getSelectionModel().select(novaAba);
			
			return loader;
			
		} catch (Exception e) {
			e.printStackTrace();
            System.out.println("Erro ao carregar o arquivo editar.fxml");
            
		}
		return null;
	}
	
	public void fecharAba() {
		tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedIndex());
	}
    
    private void abrirLink(String url) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("linux")) {
                Runtime.getRuntime().exec(new String[]{"xdg-open", url});
                
            } else if (os.contains("windows")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", url});
                
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"open", url});
                
            } else {
            	mostrarAlerta(AlertType.ERROR,
            			"Não é possível abrir o link!", 
            			"Sistema operacional não suportado.");
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addCriptideoAlterado(Integer idCriptideo) {
    	if(!idsCriptideosAlterados.contains(idCriptideo))
    		idsCriptideosAlterados.add(idCriptideo);
    }
    
    
    /* Um criptídeo só pode existir caso exista um avistamento.
     * Este só pode existir caso tenha testemunhas.
     */
    private void apagarEntidadeSemRelacoes() {
        CriptideoAvistamentoDAO caDAO = new CriptideoAvistamentoDAO();
        AvistamentoTestemunhaDAO atDAO = new AvistamentoTestemunhaDAO();
        AvistamentoDAO avistamentoDAO = new AvistamentoDAO();
        CriptideoDAO criptideoDAO = new CriptideoDAO();
        CriptideoConfirmadoDAO cripConfirmadoDAO = new CriptideoConfirmadoDAO();
        PesquisadorDAO pesquisadorDAO = new PesquisadorDAO();

        for (Integer idCriptideo : idsCriptideosAlterados) {
            excluirAvistamentosSemTestemunhas(idCriptideo, caDAO, atDAO, avistamentoDAO);
            excluirCriptideoSemAvistamentos(idCriptideo, caDAO, criptideoDAO);
            verificarCriptideoConfirmado(idCriptideo, caDAO, atDAO, pesquisadorDAO, criptideoDAO, cripConfirmadoDAO);
        }
    }

    private void excluirAvistamentosSemTestemunhas(Integer idCriptideo, CriptideoAvistamentoDAO caDAO, 
                                                   AvistamentoTestemunhaDAO atDAO, AvistamentoDAO avistamentoDAO) {
        List<Integer> idsAvistamentos = caDAO.buscarIdsAvistamentosPorCriptideo(idCriptideo);
        
        idsAvistamentos.stream()
            .filter(idAvistamento -> atDAO.buscarIdsTestemunhasPorAvistamento(idAvistamento).isEmpty())
            .forEach(idAvistamento -> {
                avistamentoDAO.excluir(idAvistamento);
                caDAO.excluirRelacao(idCriptideo, idAvistamento);
                
                mostrarAlerta(AlertType.INFORMATION,
                		"Avistamento Excluído",
                		"Você adicionou um avistamento sem nenhuma testemunha. \n"
                		+ "O avistamento n. "+ idAvistamento +"° do criptídeo n. "+ idCriptideo +"° "
                		+ "foi excluído!");
            });
    }

    private void excluirCriptideoSemAvistamentos(Integer idCriptideo, CriptideoAvistamentoDAO caDAO, 
                                                 CriptideoDAO criptideoDAO) {
        if (caDAO.buscarIdsAvistamentosPorCriptideo(idCriptideo).isEmpty()) {
            criptideoDAO.excluir(idCriptideo);
            
            mostrarAlerta(AlertType.INFORMATION,
            		"Criptídeo Excluído",
            		"Você adicionou um criptídeo sem nenhum avistamento. \n"
                    + "O criptídeo n. "+ idCriptideo +"° foi excluído!");
            
        }
    }

    private void verificarCriptideoConfirmado(Integer idCriptideo, CriptideoAvistamentoDAO caDAO, 
    		AvistamentoTestemunhaDAO atDAO, PesquisadorDAO pesquisadorDAO,
            CriptideoDAO criptideoDAO, CriptideoConfirmadoDAO cripConfirmadoDAO) {
			Criptideo criptideo = criptideoDAO.consultarPorId(idCriptideo);
			
			if (criptideo == null) {;
				return;
			}
			
			if (criptideo.getStatusCr() != StatusCriptideo.CONFIRMADO) return;
				CriptideoConfirmado cripConfirmado = cripConfirmadoDAO.consultarPorIdCriptideo(idCriptideo);
				
			if (cripConfirmado == null) {
				criptideo.setStatusCr(StatusCriptideo.AVISTADO);
				criptideoDAO.atualizar(criptideo);
				return;
			}
			
			boolean achouUmPesquisador = caDAO.buscarIdsAvistamentosPorCriptideo(idCriptideo)
				.stream()
				.flatMap(idAvistamento -> atDAO.buscarIdsTestemunhasPorAvistamento(idAvistamento).stream())
				.anyMatch(idTestemunha -> pesquisadorDAO.consultarPorIdTestemunha(idTestemunha) != null);
			
			if (!achouUmPesquisador) {
				cripConfirmadoDAO.excluir(cripConfirmado.getIdConfirmado());
				criptideo.setStatusCr(StatusCriptideo.AVISTADO);
				criptideoDAO.atualizar(criptideo);
				
				mostrarAlerta(AlertType.INFORMATION,
	            		"Criptídeo Confirmado Excluído",
	            		"Você confirmou um criptídeo sem nenhum pesquisador envolvido. \n"
	            		+ "O criptídeo confirmado n. "+ cripConfirmado.getIdConfirmado() +"°"
	            		+ " foi excluído!");
			}
    }
    
    @FXML
    private void onBtnAdicionarCriptideoAction() {
		FXMLLoader loader = adicionarAba("/visao/EditarCriptideoPane.fxml", "Edicionar Criptideo");
		
		if( loader != null) {
			 EditarCriptideoController controller = loader.getController();
	         controller.setDados(new Criptideo(), ModeloAba.ADICIONAR, this);
		}
    }
    
    @FXML
    private void onHpBraianGithubAction() {
        abrirLink("https://github.com/BraianMelo"); 
    }
    
    @FXML
    private void onHpGustavoGithubAction() {
		abrirLink("https://github.com/GustavoH-C");
    }
    
    @FXML
    private void onHpYuriGithubAction() {
		abrirLink("https://github.com/YuriDrumond");
    }
    
    @FXML
    private void onHpProjectoGithubAction() {
		abrirLink("https://github.com/BraianMelo/Trabalho_BD");
    }
    
	@FXML
	private void onTbtnDarkModeAction() {
		if(modoDark) {
			apMenuView.getScene().getStylesheets().clear();
			apMenuView.getScene().getStylesheets().add(getClass().getResource("/visao/estilos/MenuVisaoModoClaro.css").toExternalForm());
			tbtnDarkMode.setText("Desabilitado");
		} else {
			apMenuView.getScene().getStylesheets().add(getClass().getResource("/visao/estilos/MenuVisaoModoEscuro.css").toExternalForm());
			tbtnDarkMode.setText("Habilitado");
		}
		
		modoDark = !modoDark;
	}
	
	@FXML
	private void onSldTamanhoFontMouseRelease() {
		int tamanhoFonte = (int) sldTamanhoFont.getValue();
		
		apMenuView.lookupAll(".text-id").forEach(node -> {
			node.setStyle("-fx-font-size: " + tamanhoFonte + "px;");
		});
		
		
		apMenuView.lookupAll(".lbl-titulo").forEach(node -> {
			node.setStyle("-fx-font-size: " + (tamanhoFonte + 9) + "px; -fx-font-weight: bold;");
		});
	}

}

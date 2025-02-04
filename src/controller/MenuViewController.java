package controller;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Criptideo;
import persistence.CriptideoDAO;

public class MenuViewController {
	
	private boolean modoDark = true;

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
        // Carrega os dados da lista de Criptídeos e mostra no grid
        carregarGridCriptideos();
        
    }

	public void carregarGridCriptideos() {
		// Cria o DAO e obtém a lista de Criptídeos
		CriptideoDAO criptideoDAO = new CriptideoDAO();
		List<Criptideo> listaCriptideos = criptideoDAO.listarTodos();

		// Limpa o grid antes de recarregar
		vboxGrid.getChildren().clear(); 

		try {
			for (Criptideo criptideo : listaCriptideos) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CryptidPane.fxml"));
				Pane pane = loader.load();

				CryptidPaneController controller = loader.getController();
				controller.setDados(criptideo, this);

				// Adiciona o novo item ao grid
				vboxGrid.getChildren().add(pane);
			}
		} catch (IOException e) {
			System.err.println("Erro ao carregar os Criptídeos: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// TODO: Add tab mais genérico
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
    
    public void adicionarAbaEdicao(Criptideo criptideo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditCryptidPane.fxml"));
            Node conteudo = loader.load();

            Tab novaAba = new Tab("Editar "+ criptideo.getNome());

            novaAba.setContent(conteudo);

            tabPane.getTabs().add(novaAba);

            tabPane.getSelectionModel().select(novaAba);
            
            EditCryptidPaneController controller = loader.getController();
            controller.setDados(criptideo, this);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar o arquivo editar.fxml");
        }
	}
	
	public void adicionarAbaInformacao(Criptideo criptideo, List<Integer> idsAvistamentos) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CryptidInformationsPane.fxml"));
            Node conteudo = loader.load();

            Tab novaAba = new Tab("Informações do "+ criptideo.getNome());

            novaAba.setContent(conteudo);

            tabPane.getTabs().add(novaAba);

            tabPane.getSelectionModel().select(novaAba);
            
            CryptidInformationPaneController controller = loader.getController();
            controller.setDados(criptideo, idsAvistamentos, this);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar o arquivo editar.fxml");
        }
	}
    
    public void abrirLink(String url) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("linux")) {
                Runtime.getRuntime().exec(new String[]{"xdg-open", url});
            } else if (os.contains("windows")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", url});
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"open", url});
            } else {
                System.out.println("Sistema operacional não suportado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
    }
}
    
    @FXML
    void onHpBraianGithubAction() {
        abrirLink("https://github.com/BraianMelo"); 
    }
    
    @FXML
    void onHpGustavoGithubAction() {
		abrirLink("https://github.com/GustavoH-C");
    }
    
    @FXML
    void onHpYuriGithubAction() {
		abrirLink("https://github.com/YuriDrumond");
    }
    
    @FXML
    void onHpProjectoGithubAction() {
		abrirLink("https://github.com/BraianMelo/Trabalho_BD");
    }
    
	@FXML
    void onTbtnDarkModeAction() {
		if(modoDark) {
			apMenuView.getScene().getStylesheets().clear();
			apMenuView.getScene().getStylesheets().add(getClass().getResource("/view/styles/MenuViewLightMode.css").toExternalForm());
			tbtnDarkMode.setText("Desabilitado");
		} else {
			apMenuView.getScene().getStylesheets().add(getClass().getResource("/view/styles/MenuViewDarkMode.css").toExternalForm());
			tbtnDarkMode.setText("Habilitado");
		}
		
		modoDark = !modoDark;
	}
	
	@FXML
	void onSldTamanhoFontMouseRelease() {
		// Obtém o valor do slider
		int tamanhoFonte = (int) sldTamanhoFont.getValue();
		

		// Define o estilo para todos os elementos com a classe "text-id"
		apMenuView.lookupAll(".text-id").forEach(node -> {
			node.setStyle("-fx-font-size: " + tamanhoFonte + "px;");
		});
		
		
		apMenuView.lookupAll(".lbl-titulo").forEach(node -> {
			node.setStyle("-fx-font-size: " + (tamanhoFonte + 9) + "px; -fx-font-weight: bold;");
		});
	}

}

package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Slider;

import javafx.scene.input.MouseEvent;

import controller.CryptidPaneController;
import controller.CryptidViewController;
import model.Criptideo;
import persistence.CriptideoDAO;
import util.WindowsUtil;

import java.util.List;

public class MenuViewController {
	
	private boolean modoDark = true;

	@FXML
	private AnchorPane apMenuView;
	
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
        carregarCriptideos();
        
    }

    private void carregarCriptideos() {
        // Cria o DAO e obtém a lista de Criptídeos
        CriptideoDAO criptideoDAO = new CriptideoDAO();
        List<Criptideo> listaCriptideos = criptideoDAO.listarTodos();
        
        try {
			for (Criptideo criptideo : listaCriptideos) {
				// Carrega o FXML do layout de cada Criptídeo
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CryptidPane.fxml"));
				Pane pane = loader.load();

				// Obtém o controlador associado ao FXML
				CryptidPaneController controller = loader.getController();

				// Passa os dados do Criptídeo para o controlador
				controller.setDados(criptideo);

				// Adiciona o Pane ao VBox
				vboxGrid.getChildren().add(pane);
			}
		} catch (IOException e) {
			e.printStackTrace();
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
			tbtnDarkMode.setText("Desabilitado");
			tbtnDarkMode.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: #E0E0E0;");
		} else {
			apMenuView.getScene().getStylesheets().add(getClass().getResource("/gui/styles/MenuView.css").toExternalForm());
			tbtnDarkMode.setText("Habilitado");
			tbtnDarkMode.setStyle("-fx-background-color: #388E3C; -fx-text-fill: #ffffff;");
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

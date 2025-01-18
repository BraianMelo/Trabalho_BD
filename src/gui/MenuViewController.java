package gui;

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

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Slider;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import gui.CryptidViewController;
import model.Criptideo;
import model.dao.CriptideoDAO;
import util.WindowsUtil;

import java.util.List;

public class MenuViewController {
	
	private boolean modoDark = true;

	@FXML
	private AnchorPane apMenuView;

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
    private TableView<Criptideo> tbvCriptideos; // Define a TableView para Criptideo

    @FXML
    private TableColumn<Criptideo, Integer> tbcIdCriptideo; // Coluna para ID

    @FXML
    private TableColumn<Criptideo, String> tbcNomeCriptideo; // Coluna para Nome

    @FXML
    private TableColumn<Criptideo, String> tbcDescricaoCriptideo; // Coluna para Descrição

    @FXML
    private TableColumn<Criptideo, String> tbcTipoCriptideo; // Coluna para Tipo

    @FXML
    private TableColumn<Criptideo, String> tbcTipoStatus; // Coluna para Status

    @FXML
    public void initialize() {
        // Inicializa as colunas com os valores da classe Criptideo
        tbcIdCriptideo.setCellValueFactory(new PropertyValueFactory<>("idCriptideo"));
        tbcNomeCriptideo.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcDescricaoCriptideo.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tbcTipoCriptideo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tbcTipoStatus.setCellValueFactory(new PropertyValueFactory<>("statusCr"));

        // Carrega os dados da lista de Criptídeos e exibe na TableView
        carregarCriptideos();
    }

    private void carregarCriptideos() {
        // Cria o DAO e obtém a lista de Criptídeos
        CriptideoDAO criptideoDAO = new CriptideoDAO();
        List<Criptideo> listaCriptideos = criptideoDAO.listarTodos();

        // Adiciona a lista à TableView
        tbvCriptideos.getItems().setAll(listaCriptideos);
    }
    
    @FXML
    private void onTbvCriptideosMouseClick(MouseEvent event){
		if (event.getClickCount() == 2) {
			Criptideo criptideoSelecionado = tbvCriptideos.getSelectionModel().getSelectedItem();
			
			if (criptideoSelecionado != null) {
				String titulo = "Criptídeo: " + criptideoSelecionado.getNome();
				String caminhoFXML = "/gui/CryptidView.fxml";
				String iconePath = "/gui/images/Icon.png";
				String caminhoCss = "/gui/styles/CrytidView.css";
				
				WindowsUtil.abrirJanelaComCriptideo(caminhoFXML, caminhoCss, titulo, iconePath, criptideoSelecionado);
			}
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
	}

}

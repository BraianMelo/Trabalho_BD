package gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Criptideo;
import model.dao.CriptideoDAO;

import java.util.List;

public class MenuViewController {

    @FXML
    private Hyperlink hpBraianGithub;

    @FXML
    private Hyperlink hpGustavoGithub;

    @FXML
    private Hyperlink hpYuriGithub;

    @FXML
    private Hyperlink hpProjetoGithub;

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
    private void abrirLink(String link) {
        try {

            URI uri = new URI(link);

            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                // Tem que criar uma nova Thread senão dá pau no Linux.
                new Thread(() -> {
                    try {
                        desktop.browse(uri);
                        System.out.println("Link aberto no navegador.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                System.out.println("Desktop não suportado.");
            }
        } catch (Exception e) {
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

}

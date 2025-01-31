package controller;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Criptideo;
import model.Avistamento;
import persistence.AvistamentoDAO;
import persistence.AvistamentoTestemunhaDAO;

public class CryptidInformationPaneController {

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

    // Inicialização do controlador
    public void initialize() {
        configurarImagemRedonda();
    }

    // Método para definir os dados do criptídeo
    public void setDados(Criptideo criptideo, List<Integer> idsAvistamentos) {
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

        AvistamentoDAO avistamentoDAO = new AvistamentoDAO();
        
        int numeroAvistamento = 1;

        for (int idAvistamento : idsAvistamentos) {
            Avistamento avistamento = avistamentoDAO.consultarPorId(idAvistamento);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SightingPane.fxml"));
                Pane pane = loader.load();

                // Obtém o controlador correto para o FXML
                SightingPaneController controller = loader.getController();
                controller.setDados(avistamento, numeroAvistamento);

                // Adiciona o novo item ao grid
                vboxGrid.getChildren().add(pane);
                
                ++numeroAvistamento;
            } catch (IOException e) {
                System.err.println("Erro ao carregar SightingPane.fxml: " + e.getMessage());
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
    
        // Método auxiliar para formatar o texto do Enum
    private String formatarEnum(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}

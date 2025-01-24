package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.Criptideo;

import util.WindowsUtil;

public class CryptidViewController {

    private Criptideo criptideo;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblTipo;

    @FXML
    private Text txtDescricao;

    // Construtor para receber o objeto Criptideo
    public CryptidViewController(Criptideo criptideo) {
        this.criptideo = criptideo;
    }

    @FXML
    public void initialize() {
		WindowsUtil windowsUtil = new WindowsUtil();
		
        // Certifique-se de que o objeto Criptideo não seja nulo antes de usá-lo
        if (criptideo != null) {
            lblNome.setText(criptideo.getNome());
            lblStatus.setText(windowsUtil.formatarEnum(criptideo.getStatusCr().toString()));
            lblTipo.setText(windowsUtil.formatarEnum(criptideo.getTipo().toString()));
            txtDescricao.setText(criptideo.getDescricao());
        } else {
            System.err.println("Objeto Criptideo não foi inicializado!");
        }
    }
}

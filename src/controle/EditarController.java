package controle;

import app.Aplicacao;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.enums.ModeloAba;

public abstract class EditarController extends Controller{
	
	protected ModeloAba modeloAba = null;
	
    protected String getTextField(TextField txtf) {
    	return(txtf.getText() == null || txtf.getText().equals("")? null: txtf.getText());
    }
    
    protected void setTextField(TextField txtf, String texto) {
    	txtf.setText(texto);
    }
	
	protected boolean textFieldVazio(TextField txtf) {
		return (txtf.getText() == null || txtf.getText().equals(""));
	}
	
	protected void setImagemBotao(ImageView imgBotao) {
		if(modeloAba.equals(ModeloAba.EDITAR))
			return;
		
		Image icone = new Image(Aplicacao.class.getResourceAsStream("/visao/imagens/Icone_Adicionar.png"));
		imgBotao.setImage(icone);
		return;
		
	}
	
	protected void setLblTitulo(Label lbl, String complemento) {
		if(modeloAba.equals(ModeloAba.EDITAR)) {
			lbl.setText("Editar "+ complemento);
			return;
		}
		
		lbl.setText("Adicionar "+ complemento);
	}

}

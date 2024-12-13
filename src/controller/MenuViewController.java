package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class MenuViewController {
	
	@FXML
	private Hyperlink hpBraianGithub;
	
	@FXML
	private Hyperlink hpGustavonGithub;
	
	@FXML
	private Hyperlink hpYuriGithub;
	
	@FXML
	void onHpBraianGithubAction() {
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/BraianMelo"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
	

}

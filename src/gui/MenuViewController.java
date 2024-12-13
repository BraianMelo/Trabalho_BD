package gui;

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
	private Hyperlink hpGustavoGithub;
	
	@FXML
	private Hyperlink hpYuriGithub;
	
	@FXML
	private Hyperlink hpProjetoGithub;
	
	@FXML
	void onHpBraianGithubAction() {
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/BraianMelo"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void onHpGustavoGithubAction() {
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/GustavoH-C"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void onHpYuriGithubAction() {
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/YuriDrumond"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void onHpProjectoGithubAction() {
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/BraianMelo/Trabalho_BD"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	

}

package fr.fjdhj.ECDesktop.view;

import fr.fjdhj.ECDesktop.ECDesktop;
import fr.fjdhj.ECDesktop.com.CodeException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginMapping {

	@FXML
	TextField login;
	@FXML
	PasswordField password;
	@FXML
	Button connect;
	
	
	private Stage s;
	
	public LoginMapping() {}
	public void setStageDialog(Stage s) {this.s = s;}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	public void login(){
		ECDesktop.student.setLogin(login.getText());
		ECDesktop.student.setPassword(password.getText());
		try {
			ECDesktop.student.login();
			s.close();
			Alert info = new Alert(AlertType.INFORMATION);
			info.setTitle("Connexion");
			info.setHeaderText("Connexion a été effectué avec le serveur");
			info.showAndWait();
		} catch (CodeException e) {
			ECDesktop.errorMessage("Erreur dans la réponse du serveur", e.getMessage(), "");
		}
	}

}

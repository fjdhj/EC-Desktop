package fr.fjdhj.ECDesktop;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import fr.fjdhj.ECDesktop.data.Student;
import fr.fjdhj.ECDesktop.exception.OpenDefaultBrowserException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ECDesktop extends Application {

	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;
	
	public static Student student = new Student();
	
	@Override
	public void start(Stage primaryStage) {
		stagePrincipal = primaryStage;
		stagePrincipal.setTitle("EC-Desktop");
		
		FXMLLoader loader = new FXMLLoader();
		//On lui spécifie le chemin relatif à notre classe
		//du fichier FXML a charger : dans le sous-dossier view
		loader.setLocation(ECDesktop.class.getResource("view/mainMenu.fxml"));
		try {
			//Le chargement nous donne notre conteneur
			conteneurPrincipal = (BorderPane) loader.load();
			//On définit une scène principale avec notre conteneur
			Scene scene = new Scene(conteneurPrincipal);
			//Que nous affectons à notre Stage
			stagePrincipal.setScene(scene);
			//Pour l'afficher
			stagePrincipal.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		launch(args);
	}
	
	public static void openDefaultWebBowser(String url) {
		try {
			String myOS = System.getProperty("os.name").toLowerCase();
            if(Desktop.isDesktopSupported()) { //Peut être Windows
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));
            } else { //Definitivement pas windows
                Runtime runtime = Runtime.getRuntime();
                if(myOS.contains("mac")) { // Apples
                    runtime.exec("open " + url);
                } 
                else if(myOS.contains("nix") || myOS.contains("nux")) { // Linux 
                    runtime.exec("xdg-open " + url);
                }
                else {
                	throw new OpenDefaultBrowserException("Impossible d'ouvrir le navigateur. Il est probable que votre system d'exploitation ne soit pas compatible");
                }
                    
            }
		}
        catch(IOException | OpenDefaultBrowserException | URISyntaxException e) {
            errorMessage("Erreur application", e.getMessage().toString(), e.toString());
        }
	}
	
	public static void errorMessage(String Title, String headerText, String contentText) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle(Title);
		error.setHeaderText(headerText);
		error.setContentText(contentText);
		error.showAndWait();
	}
	
}

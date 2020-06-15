package fr.fjdhj.ECDesktop;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import fr.fjdhj.ECDesktop.data.CSV;
import fr.fjdhj.ECDesktop.data.Student;
import fr.fjdhj.ECDesktop.exception.OpenDefaultBrowserException;
import fr.fjdhj.ECDesktop.view.MainMenuMapping;
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
	
	private final String encode = "adef";
	
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
			
			//On récupère le controleur
			MainMenuMapping controller = loader.getController();
			//On lui fournie nos student déjà stocké
			List<String> content = CSV.readCSV(CSV.ACCOUNT_CSV_LOCAT, CSV.ACCOUNT_CSV_SEPARATOR);
			for(String str : content) {
				controller.addToListAcount(CSV.extract(str, "nom"));
			}
			controller.updateMenuAcount();
			
			//Pour l'afficher
			stagePrincipal.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		List<String> content = CSV.readCSV(CSV.ACCOUNT_CSV_LOCAT, CSV.ACCOUNT_CSV_SEPARATOR);
		if(!content.isEmpty()) {
			student.setId(CSV.extract(content.get(0), "id"));
			student.setNom(CSV.extract(content.get(0), "nom"));
			student.setPrenom(CSV.extract(content.get(0), "prenom"));
			System.out.println(student.getNom());
		}
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

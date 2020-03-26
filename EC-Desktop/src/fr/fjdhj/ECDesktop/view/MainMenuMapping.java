package fr.fjdhj.ECDesktop.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.fjdhj.ECDesktop.ECDesktop;
import fr.fjdhj.ECDesktop.data.CSV;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuMapping {
	
	@FXML
	Menu menuAcount;
	@FXML
	Accordion workList;
	
	private List<RadioMenuItem> acount = new ArrayList<RadioMenuItem>();
	private ToggleGroup toogleGroup = new ToggleGroup();
	
	public MainMenuMapping() {}
	
	
	public void updateMenuAcount() {
		//On retire tous les comptes de la liste
		menuAcount.getItems().remove(3, menuAcount.getItems().size());
		
		//Puis on rajoute les comptes restants
		for(RadioMenuItem item : acount) {
			menuAcount.getItems().add(item);
		}
	}
	
	public void addToListAcount(String name) {
		RadioMenuItem a=new RadioMenuItem(name);
		a.setToggleGroup(toogleGroup);
		a.setUserData(name);
		acount.add(a);
	}
	
	public void removeToListAcount(RadioMenuItem item) {acount.remove(item);}
	public Toggle getSelectedAcount() {return toogleGroup.getSelectedToggle();};
	

	@FXML
	private void initialize() {}
	
	
	@FXML
	private void addAcount() {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(ECDesktop.class.getResource("view/Login.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        
	        // Création d'un nouveau Stage qui sera dépendant du Stage principal
	        Stage stageDialogue = new Stage();
	        stageDialogue.setTitle("EC Desktop");
	        stageDialogue.initModality(Modality.WINDOW_MODAL);
	        
	        //Avec cette instruction, notre fenêtre modifiée sera modale
	        //par rapport à notre stage principal
	        Scene scene = new Scene(page);
	        stageDialogue.setScene(scene);
	        
	        // initialisation du contrôleur
	        LoginMapping controller = loader.getController();
	        controller.setStageDialog(stageDialogue);
	        
	        // Show the dialog and wait until the user closes it
	        stageDialogue.showAndWait();
	        //return controller.isOkClicked();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@FXML
	private void delAcount() {
		CSV.addCSVLine(new File("account.csv"), "J'y sais pas3");
		CSV.removeCSVLine(new File("account.csv"), "J'y sais pas");
		
	}
}

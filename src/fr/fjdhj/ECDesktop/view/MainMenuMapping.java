package fr.fjdhj.ECDesktop.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import fr.fjdhj.ECDesktop.ECDesktop;
import fr.fjdhj.ECDesktop.data.Calendar;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuMapping {
	
	@FXML
	Menu menuAcount;
	@FXML
	Accordion workList;
	@FXML
	GridPane calendarGrid;
	@FXML
	Text textMonth;
	
	private List<RadioMenuItem> acount = new ArrayList<RadioMenuItem>();
	private ToggleGroup toogleGroup = new ToggleGroup();
	
	private int year = LocalDate.now().getYear(); //L'annee courante
	private int month = LocalDate.now().getMonthValue(); //Le moi actuel
	private int day = LocalDate.now().getDayOfMonth(); //Le jour du moi actuel
	
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
	
	public void updateCalendar() {
		//On mets a jour le text du moi
		textMonth.setText(Calendar.nameOfMonth(month)+" "+year);
		
		//On récupère le nombre de jour
		int nbrDay = Calendar.numberOfDayInMonth(month, year);
		int firstDay = LocalDate.of(year, month, 1).getDayOfWeek().getValue();
		
		//On récupère les boutons
		ObservableList<Node> child = calendarGrid.getChildren();
		
		//limite  - nombre de jour dans le moi précédant = Jour du mois
		//A chaque tour, limite perd 1, jusqu'a avancer au premier jour du mois suivant
		int limite = firstDay-2;
		//Pas de problème si month-1 == 0
		int nbrDayPreviouse = Calendar.numberOfDayInMonth(month-1, year);
		
		int i = 0;
		Node c;
		while((c=child.get(i)) instanceof Button) {
			if(limite>-1) {
				((Button) c).setText(getString(nbrDayPreviouse-limite));
				((Button) c).setDisable(true);
			}else if(-limite<=nbrDay) {
				((Button) c).setText(getString(-limite));
				((Button) c).setDisable(false);
			}else {
				((Button) c).setText(getString(-limite-nbrDay));
				((Button) c).setDisable(true);
			}
			limite--;
			i++;
		}
	}
	
	private static String getString(int num) {
		if(num==1) {return "01";}
		if(num==2) {return "02";}
		if(num==3) {return "03";}
		if(num==4) {return "04";}
		if(num==5) {return "05";}
		if(num==6) {return "06";}
		if(num==7) {return "07";}
		if(num==8) {return "08";}
		if(num==9) {return "09";}
		return new Integer(num).toString();
	}
	
	private void setDay(int day) {
		this.day=day;
		System.out.println("Chargement du "+day+" "+Calendar.nameOfMonth(month)+" "+year);
		//On récupère les boutons
		ObservableList<Node> child = calendarGrid.getChildren();
		int i = 0;
		Node c;
		while((c=child.get(i)) instanceof Button) {
			//On met les boutons en mode pas défaut
			((Button) c).setDefaultButton(false);
			i++;
		}
	}

	@FXML
	private void initialize() {
		updateCalendar();
		
		ObservableList<Node> child = calendarGrid.getChildren();
		int i = 0;
		Node c;
		while((c=child.get(i)) instanceof Button) {
			//On ajoute nos observeur a nos boutons
			((Button) c).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					setDay(Integer.parseInt(((Button) arg0.getSource()).getText()));
					((Button) arg0.getSource()).setDefaultButton(true);
				}
				
			});
			
			//Si c'est le jour actuel
			if(Integer.parseInt(((Button) c).getText())==LocalDate.now().getDayOfMonth() && !((Button) c).isDisable())
				((Button) c).setDefaultButton(true);
			i++;
		}
		
		
		
	}
	
	@FXML
	private void nextMonth() {
		if(month==12) {
			month=1;
			year++;
		}else {
			month++;
		}
		updateCalendar();
	}
	
	@FXML
	private void previousMonth() {
		if(month==1) {
			month=12;
			year--;
		}else {
			month--;
		}
		updateCalendar();
	}
	
	
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
	private void delAcount() {}
}

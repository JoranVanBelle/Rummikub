package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.FoutiefAantalSpelers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class BepaalAantalController extends BorderPane implements Initializable{
	private LoginController loginController;
	
	@FXML
	private Label titelLabel;
	@FXML
	private Label lblFoutmelding;
	@FXML
	private ImageView logoGroenImg;
	@FXML
	private Label welkomLabel;
	@FXML
	private Label errorAantalSpelersLabel;
	@FXML
	private ChoiceBox cbAantalSpelers;
	@FXML
	private Button btnContinue;
	@FXML
	private Button btnCancel;
	@FXML
	private Button buttonEnglish;
	@FXML
	private Button buttonDutch;
	
	private DomeinController dc;
	
	ObservableList<Integer> availableChoices = FXCollections.observableArrayList(2,3,4);
	
	
	public BepaalAantalController() {
		buildGui();
	} 
	
	public void continueButtonOnAction(ActionEvent event) throws FoutiefAantalSpelers {
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(new BorderPane()));

	}
	//button english pressed
	public void englishButtonOnAction(ActionEvent event) {

	}
	//button dutch pressed
	public void dutchButtonOnAction(ActionEvent event) {
    	
	}
	
	public void cancelButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow(); 
		stage.close(); 
	}
	
	

	public void buildGui() {
		
		

		try {
						
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("bepaalAantal.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			Platform.exit();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		cbAantalSpelers.setItems(availableChoices);
	}
	
	public Button getBtnContinue() {
		return btnContinue;
	}
	
	public Button getBtnCancel() {
		return btnCancel;
	}
	
	public Button getBtnEnglish() {
		return buttonEnglish;
	}
	
	public Button getBtnDutch() {
		return buttonDutch;
	}
	
	public Label getWelkomLabel() {
		return welkomLabel;
	}
	
	public Object getChoiceBoxChoice() {
		return cbAantalSpelers.getValue();
	}
	
	public Label getFoutmelding() {
		return lblFoutmelding;
	}
//	
//	public void aantalSpelersKiezen() throws FoutiefAantalSpelers {
//		boolean opnieuw = true;
//
//		int aantalSpelers; 
//
//		do {
//			try {
//				aantalSpelers = Integer.parseInt(aantalSpelersTxf.getText().toString());
//				dc.bepaalAantalSpelers(aantalSpelers);
//				opnieuw = false;
//			} catch (NumberFormatException e) {
//				lblFoutmelding.setText(e.getMessage()); // aan dc vragen
//			} catch (Exception e) {
//				lblFoutmelding.setText(e.getMessage());
//			}
//		} while (opnieuw);
//	}
	
//	public void aantalSpelersKiezen() {
//		int aantalSpelersG;
//		aantalSpelersG = Integer.parseInt(aantalSpelersTxf.getText().toString()); 
//		if(aantalSpelersG > 4 || aantalSpelersG < 2) {
//			lblFoutmelding.setText("Dit is een foutmelding");
//			
//		}
//	}
//		boolean opnieuw = true;
//
//		String aantalSpelers = ""; 
//
//		int aantalSpelersG;
//
//
//		do {
//				
//			try {
//				aantalSpelersG = Integer.parseInt(aantalSpelersTxf.getText().toString()); 
//				dc.bepaalAantalSpelers(aantalSpelersG);
//				opnieuw = false;
//				
//			} catch (NumberFormatException e) {
//				System.err.println(); // aan dc vragen
//			} catch (FoutiefAantalSpelers e) {
//				System.err.println(e.getMessage() + "test");
//			} 
//			catch (Exception e) {
//				System.err.println("Gelieve u bij de programmeurs te wenden!");
//			} finally {
//				aantalSpelersTxf.clear();
//			}
//
//		} while (opnieuw);
//	}
//	
//	public Label getLblFoutmelding() {
//		return lblFoutmelding;
//	}


}

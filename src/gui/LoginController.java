package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.control.PasswordField;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class LoginController extends BorderPane implements Initializable{
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnBack;
	@FXML
	private Label loginMessageLabel;
	@FXML
	private Label lblUsername;
	@FXML
	private Label lblPassword;
	@FXML
	private ImageView RKImageView;
	@FXML
	private ImageView RKImageViewGreen;
	
	
	public ImageView getRKImageViewGreen() {
		return RKImageViewGreen;
	}

	public LoginController() {
//		BorderPane borderPane = new BorderPane(usernameTextField, PasswordTextField, btnLogin, btnCancel, loginMessageLabel);
		buildGui();
	}
	
	public void backButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow(); 
		stage.close(); //NOG AANPASSEN => TERUG GAAN NAAR VORIG SCHERM
	}
	
	public void loginButtonOnAction(ActionEvent event) {
		loginMessageLabel.setText("You try to login");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}
	
	public void buildGui() {

		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("login.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			Platform.exit();
		}
	}
	
	public Button getbtnBack() {
		return btnBack;
	}
	
	public Button getbtnLogin() {
		return btnLogin;
	}
	
	public TextField getUsernameTextField() {
		return usernameTextField;
	}
	
	public TextField getPasswordTextField() {
		return passwordTextField;
	}
	
	public Label getLblUsername() {
		return lblUsername;
	}
	
	public Label getLblPassword() {
		return lblPassword;
	}
	
	public Label getLoginMessageLabel() {
		return loginMessageLabel;
	}

	public ImageView getRKImageView() {
		return RKImageView;
	}

	
	
	

}

package main;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import domein.DomeinController;
import domein.Kleur;
import domein.Steen;
import domein.VerzamelingSteen;
import exceptions.FoutiefAantalSpelers;
import exceptions.FoutiefInloggegevensException;
import exceptions.FoutieveKeuzeException;
import exceptions.GeenSteenGeselecteerd;
import exceptions.SpelerReedsAanwezigException;
import gui.BepaalAantalController;
import gui.LoginController;
import gui.SpelController;
import gui.MenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartUp extends Application {

	private DomeinController dc = new DomeinController();

	private BepaalAantalController root;
	private LoginController root2;
	private MenuController root3;
	private SpelController root4;
	private Steen steen;
	
	String language = "nl";
	String country = "NL";
	Locale locale;
	ResourceBundle messages;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		System.out.println("problemen");
		System.out.println("verschillende fouten bij splitsen");
		System.out.println("\tsplitsen op laatste steen en hij verdwijnt");
		System.out.println("\tals je een langere serie/rij hebt en klikt op joker om te splitsen zakt joker 1 rij, dit is geen probleem, want kan hem volgende keer splitsen. (zie filmpje)");

		// ALL CONTROLLERS & ROOTS
		// scherm aantalSpelers
		root = new BepaalAantalController();
		Scene scene = new Scene(root, 587, 400);
		// scherm login
		root2 = new LoginController();
		Scene scene2 = new Scene(root2, 587, 400);
		// scherm menu
		root3 = new MenuController();
		Scene scene3 = new Scene(root3);
		// scherm spel
		root4 = new SpelController();
		Scene scene4 = new Scene(root4);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Rummikub");
		primaryStage.show();
		primaryStage.setResizable(false);
		
		// MEERTALIGHEID
		
		root.getBtnDutch().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				language = "nl";
				country = "NL";
				Font font = Font.font("System", FontPosture.ITALIC, 16);
				root.getBtnContinue().setText(maakTekst().getString("knopContinue").toUpperCase());
				root.getBtnCancel().setText(maakTekst().getString("knopCancel").toUpperCase());
				root.getWelkomLabel().setFont(font);
				root.getWelkomLabel().setText(maakTekst().getString("labelWelkom"));
				root2.getbtnLogin().setText(maakTekst().getString("knopLogin").toUpperCase());
				root2.getbtnBack().setText(maakTekst().getString("knopBack").toUpperCase());
				root2.getUsernameTextField().setPromptText(maakTekst().getString("username"));
				root2.getPasswordTextField().setPromptText(maakTekst().getString("password"));
				root2.getLblUsername().setPadding(new Insets(0, 0, 0, 0));
				root2.getLblUsername().setText(maakTekst().getString("username"));
				root2.getLblPassword().setPadding(new Insets(0, 0, 0, 0));
				root2.getLblPassword().setText(maakTekst().getString("password"));
				root3.getBtnToonOverzicht().setText(maakTekst().getString("toonOverzicht").toUpperCase());
				root3.getBtnSpeel().setText(maakTekst().getString("speel").toUpperCase());
				root3.getBtnExit().setText(maakTekst().getString("exit").toUpperCase());
				root4.getBtnNeemSteen().setText(maakTekst().getString("neemSteen").toUpperCase());
				root4.getBtnConfirm().setText(maakTekst().getString("confirm").toUpperCase());
				
			}
			
		});
		
		root.getBtnEnglish().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				language = "en";
				country = "US";
				Font font = Font.font("System", FontPosture.ITALIC, 16);
				root.getBtnContinue().setText(maakTekst().getString("knopContinue").toUpperCase());
				root.getBtnCancel().setText(maakTekst().getString("knopCancel").toUpperCase());
				root.getWelkomLabel().setFont(font);
				root.getWelkomLabel().setText(maakTekst().getString("labelWelkom"));
				root2.getbtnLogin().setText(maakTekst().getString("knopLogin").toUpperCase());
				root2.getbtnBack().setText(maakTekst().getString("knopBack").toUpperCase());
				root2.getUsernameTextField().setPromptText(maakTekst().getString("username"));
				root2.getPasswordTextField().setPromptText(maakTekst().getString("password"));
				root2.getLblUsername().setPadding(new Insets(0, 0, 0, 35));
				root2.getLblUsername().setText(maakTekst().getString("username"));
				root2.getLblPassword().setPadding(new Insets(0, 0, 0, 19));
				root2.getLblPassword().setText(maakTekst().getString("password"));
				root3.getBtnToonOverzicht().setText(maakTekst().getString("toonOverzicht").toUpperCase());
				root3.getBtnSpeel().setText(maakTekst().getString("speel").toUpperCase());
				root3.getBtnExit().setText(maakTekst().getString("exit").toUpperCase());
				root4.getBtnNeemSteen().setText(maakTekst().getString("neemSteen").toUpperCase());
				root4.getBtnConfirm().setText(maakTekst().getString("confirm").toUpperCase());
				
			}
			
		});
		
		// BUTTON CONTINUE PRESSED
		root.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ev) {
				try {
					int number = Integer.parseInt(root.getChoiceBoxChoice().toString());
					try {
						try {
							dc.bepaalAantalSpelers(number);
						} catch (InputMismatchException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FoutieveKeuzeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FoutiefAantalSpelers e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					primaryStage.setScene(scene2);
					root.getFoutmelding().setText("");
				} catch (NumberFormatException e) {
					root.getFoutmelding().setText(maakTekst().getString("foutmeldingAantalSpelers"));
				}
			}
		});

		// BUTTON BACK PRESSED
		root2.getbtnBack().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ev) {
				primaryStage.setScene(scene);
			}
		});

		// BUTTON LOGIN PRESSED
		root2.getbtnLogin().setOnAction(e -> {
			int aantalSpelers = 0;
			try {
				root2.getLoginMessageLabel().setText("");
				if (!isLogin(root2.getUsernameTextField(), root2.getUsernameTextField().getText(),
						root2.getPasswordTextField(), root2.getPasswordTextField().getText(),
						root2.getLoginMessageLabel())) {
					root2.getRKImageViewGreen().setVisible(true);
				}
				root2.getUsernameTextField().clear();
				root2.getPasswordTextField().clear();
				aantalSpelers++;
//					isAantalSpelers(aantalSpelers);	
				if (dc.isVolzet()) {
					primaryStage.setScene(scene3);
					dc.startNieuwSpel();
				}
			} catch (FoutiefInloggegevensException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SpelerReedsAanwezigException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e3) {
				System.err.println(e3.getMessage());
			}
		});

		// TEXTFIELD CLICKED: RUMMIFACE RED->GREEN
		root2.getUsernameTextField().setOnMouseClicked(e -> {
			root2.getRKImageViewGreen().setVisible(false);
		});
		
		// BUTTON EXIT PRESSED
		root3.getBtnExit().setOnAction(e -> Platform.exit());
		
		// BUTTON OVERVIEW PRESSED
		root3.getBtnToonOverzicht().setOnAction(e -> {
			ButtonType exit = new ButtonType(maakTekst().getString("exit"));
			Alert alert = new Alert(AlertType.NONE, dc.toonVolgordeSpelers(), exit);
			alert.setHeaderText(maakTekst().getString("overzichtSpeler"));
			alert.setTitle(maakTekst().getString("overzichtSpeler"));
			alert.showAndWait();
		});

		// BUTTON SPEEL PRESSED
		root3.getBtnSpeel().setOnAction(e -> {
			primaryStage.setScene(scene4);
			primaryStage.setResizable(true);
			primaryStage.setFullScreen(true);
			root4.maakLabelsAan();
			dc.startBeurt();
			root4.getLblSpeler().setText(dc.geefSpelerAanDeBeurt());
			dc.backup();
			toonPersoonlijkBezit();
		});

		// GRIDPANE PERSOONLIJKBEZIT CLICKED
		root4.getGpPersoonlijkBezit().setOnMouseClicked(e -> {
			dc.selecteerSteen(root4.clickPersoonlijkVeld(e));
			root4.getLblMelding().setText("");
		});

		// GRIDPANE TAFEL CLICKED
		root4.getTafel().setOnMouseClicked(e -> {
//			try {
				steen = dc.legSteenAan();

				// ?
				if(steen != null) {
					List<Integer> indexen = root4.clickTafelIsJoker(e);
					List<VerzamelingSteen> verz = dc.toonTafel();
					Steen joker = verz.get(indexen.get(0)).geefSteen(indexen.get(1));
					if(joker.getKleur().equals(Kleur.JOKER)) {
						dc.clickTafelIsJoker(joker, indexen, steen);
					}
					else {
						// leg de geselecteerde steen aan serie/rij
						dc.steenAanSerieRij(steen, root4.clickTafel(e, steen));
						wisEnToonVelden();
					}
					// verwijderen uit persoonlijkbezit
					dc.verwijderSteen(steen);
					
				} else {
//					System.out.println("row: " + root4.clickVerzamelingRow(e) + "col" + root4.clickVerzamelingCol(e));
					dc.splits(root4.clickVerzamelingRow(e), root4.clickVerzamelingCol(e));
					

				}

				// resetten van de velden in de gui
				wisEnToonVelden();
				
//			} catch (NullPointerException npe) {
//				dc.splits(root4.clickVerzamelingRow(e), root4.clickVerzamelingCol(e));
//				// resetten van de velden in de gui
//				wisEnToonVelden();
//			}
		});

		// GRIDPANE WERKVELD CLICKED
		root4.getGpWerkveld().setOnMouseClicked(e -> {

			try {
				
				
				root4.clearPersoonlijkBezit();
				toonPersoonlijkBezit();
				
				steen = dc.legSteenAan();

				// werkveld clicked
				root4.clickWerkveld(e, steen);

				// steen toevoegen aan werkveld
				dc.voegSteenToeAanWerkVeld(steen);

				// steen verwijderen uit persoonlijkveld
				dc.verwijderSteen(steen);

				// resetten van de velden in de gui
				wisEnToonVelden();

			} catch (NullPointerException npe) {
				root4.getLblMelding().setText(maakTekst().getString("foutmeldingGeenSteenGeselecteerd"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		// GRIDPANE LOSSE STENEN CLICKED
		root4.getLosseStenen().setOnMouseClicked(e -> {
			dc.selecteerSteenLosseStenen(root4.clickLosseStenen(e));
			root4.getLblMelding().setText("");
		});

		// BUTTON RESET CLICKED
		root4.getBtnReset().setOnAction(e -> {

			// resetten van de velden in het domein
			dc.reset();
			// resetten van de velden in de gui
			wisEnToonVelden();
			// melding
			root4.getLblMelding().setText(String.format("%s %s", dc.getSpeler(), maakTekst().getString("resetGedrukt")));
			
		});

		// BUTTON CONFIRM PRESSED
		root4.getBtnConfirm().setOnAction(e -> {
			root4.clearPersoonlijkBezit();
			toonPersoonlijkBezit();
			
			if (!dc.valideerSpel()) {
				root4.getLblMelding().setText(maakTekst().getString("teKort"));

			} else if (dc.dertigStenenGelegd() && dc.zetIsGezet()) {
				System.out.println("zetIsGezet output: " + dc.zetIsGezet());
				dc.speelBeurt();
				dc.backup();

				// speler label
				root4.getLblSpeler().setText(dc.geefSpelerAanDeBeurt());

				wisEnToonVelden();
				root4.getLblMelding().setText(maakTekst().getString("confirmGedrukt"));
			} else {
				root4.getLblMelding().setText(maakTekst().getString("NietVoldoendePunten"));
			}
		});  

		// BUTTON PAK STEEN PRESSED
		root4.getBtnNeemSteen().setOnAction(e -> {
			
			root4.getLblSpeler().setText(dc.geefSpelerAanDeBeurt());
			dc.maakBackupPersoonlijkBezit();
			dc.reset();
		
			dc.neemSteen();

			wisEnToonVelden();

			dc.speelBeurt();
			root4.getLblSpeler().setText(dc.geefSpelerAanDeBeurt());
			wisEnToonVelden();

			// speler label

			// melding
			root4.getLblMelding().setText(String.format("%s %s", dc.getSpeler(), maakTekst().getString("steenGenomen")));
		});
		
		// BUTTON WIN
		root4.getBtnWin().setOnAction(e -> {
			dc.beeindigSpel();
			dc.speelBeurt();
			wisEnToonVelden();
			ButtonType exit = new ButtonType(maakTekst().getString("exit"));
			Alert alert = new Alert(AlertType.NONE, String.format("\n%s", dc.geefScores().stream().map(n -> n.toString()).collect(Collectors.joining(""))), exit);
			alert.setHeaderText(String.format("%s %s", dc.getSpeler(), maakTekst().getString("geforceerdEinde")));
			alert.setTitle(maakTekst().getString("winaarGekend"));
			alert.showAndWait();
		});
	}

	// ----------------------------------------------------------METHODES--------------------------------------------------------------

	private boolean isLogin(TextField usernameInput, String usernameMessage, TextField passwordInput,
			String passwordMessage, Label error) throws FoutiefInloggegevensException, SpelerReedsAanwezigException {

		boolean opnieuw = true;

		try {
			String username = usernameMessage.toString();
			String password = passwordMessage.toString();
			dc.meldAan(username, password);
			opnieuw = false;
		} catch (Exception e) {
			opnieuw = true;
			error.setText(new FoutiefInloggegevensException(maakTekst().getString("foutiefInloggegevens")).toString());
		}
		return opnieuw;
	}

	private void wisEnToonVelden() {
		root4.clearPersoonlijkBezit();
		root4.clearWerkveld();
		root4.clearTafel();
		root4.clearLosseStenen();
		toonPersoonlijkBezit();
		toonWerkveld();
		toonTafel();
		toonLosseStenen();
	}

	// TOON METHODES
	public void toonTafel() {
		int col;
		int row = 0;
		String getalString = "";
		List<VerzamelingSteen> verzameling = dc.toonTafel();
		for (VerzamelingSteen stenen : verzameling) {
			col = 0;
			List<Steen> stenen1 = stenen.getStenen();
			for (Steen s : stenen1) {
				getalString = String.format("%s%s", s.getGetal(), s.getKleur());
				root4.tafelWeergeven(getalString, row, col);
				col++;
			}
			row++;
		}
	}

	public void toonPersoonlijkBezit() {

		int col = 0;
		int row = 0;
		String getalString = "";
		List<Steen> bezit = dc.toonPersoonlijkBezit();

		for (Steen steen : bezit) {
			getalString = String.format("%s%s", steen.getGetal(), steen.getKleur());
			root4.persoonlijkBezitWeergeven(getalString, col, row);
			col++;
			if (col == 12) {
				col = 0;
				row++;
			}
		}
	}

	public void toonWerkveld() {
		int col = 0;
		String getalString = "";
		List<Steen> bezit = dc.toonWerkveld();

		for (Steen steen : bezit) {
			getalString = String.format("%s%s", steen.getGetal(), steen.getKleur());
			root4.werkveldWeergeven(steen, col);
			col++;
		}
	}

	public void toonLosseStenen() {
		int col = 0;
		String getalString = "";
		List<Steen> bezit = dc.toonLosseStenen();

		for (Steen steen : bezit) {
			getalString = String.format("%s%s", steen.getGetal(), steen.getKleur());
			root4.losseStenenWeergeven(steen, col);
			col++;
		}
	}
	
	private ResourceBundle maakTekst() {
		locale = new Locale(language, country);
		return messages = ResourceBundle.getBundle("messages", locale);
	}
}

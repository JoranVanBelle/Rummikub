package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domein.Steen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SpelController extends BorderPane implements Initializable {

	// attributen
	@FXML
	private GridPane gpSpelers;
	@FXML
	private Label lblSpeler;
	@FXML
	private Label lblMelding;
	@FXML
	private GridPane gpTafel;
	@FXML
	private GridPane gpWerkveld;
	@FXML
	private GridPane gpLosseStenen;
	@FXML
	private Button btnConfirm;
	@FXML
	private Button btnReset;
	@FXML
	private Button btnNeemSteen;
	@FXML
	private Button btnWin;
	@FXML
	private GridPane gpPersoonlijkBezit;
	@FXML
	private GridPane gpVerzameling;
	@FXML
	private ImageView imgSpeler;

	private Node clickedNode;

	private static final int PERSOONLIJK_BEZIT_LENGTE_KOLOM = 10;

	// scherm opstarten
	public SpelController() {
		buildGui();
	}

	private void buildGui() {
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("spel.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			Platform.exit();
		}

	}

	public Label getLblMelding() {
		return lblMelding;
	}
	
	public Label getLblSpeler() {
		return lblSpeler;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	// methodes
	public Button getBtnConfirm() {
		return btnConfirm;
	}

	public Button getBtnReset() {
		return btnReset;
	}

	public Button getBtnNeemSteen() {
		return btnNeemSteen;
	}
	
	public Button getBtnWin() {
		return btnWin;
	}
	
	public GridPane getLosseStenen() {
		return gpLosseStenen;
	}

	public GridPane getGpPersoonlijkBezit() {
		return gpPersoonlijkBezit;
	}

	public GridPane getGpWerkveld() {
		return gpWerkveld;
	}

	public GridPane getVerzameling() {
		return gpVerzameling;
	}

	public GridPane getTafel() {
		return gpTafel;
	}
	
	public ImageView getImgSpeler() {
		return imgSpeler;
	}

	public void persoonlijkBezitWeergeven(String steen, int col, int row) {
		File file = new File(String.format("Small/%S.png", steen));
		Image image = new Image(file.toURI().toString());
		gpPersoonlijkBezit.add(new ImageView(image), col, row);
	}

	public void clearPersoonlijkBezit() {
		gpPersoonlijkBezit.getChildren().clear();
	}

	public void reset() {
		gpLosseStenen.getChildren().clear();
		gpWerkveld.getChildren().clear();
		gpPersoonlijkBezit.getChildren().clear();
		gpTafel.getChildren().clear();
	}

	// klik events
	public int clickVerzamelingRow(MouseEvent event) {
		Integer rowIndex = 0;
		Node clickedNode = event.getPickResult().getIntersectedNode();
		if (clickedNode != gpTafel) {
			// click on descendant node
			rowIndex = GridPane.getRowIndex(clickedNode);
		}
		return rowIndex;
	}
	
	public int clickVerzamelingCol(MouseEvent event) {
		Integer rowIndex = 0;
		Node clickedNode = event.getPickResult().getIntersectedNode();
		if (clickedNode != gpTafel) {
			// click on descendant node
			rowIndex = GridPane.getColumnIndex(clickedNode);
		}
		return rowIndex;
	}

	public int clickPersoonlijkVeld(MouseEvent event) {
		Integer rowIndex = 0;
		Integer colIndex = 0;
		clickedNode = event.getPickResult().getIntersectedNode();
		if (clickedNode != gpPersoonlijkBezit) {
			// click on descendant node
			colIndex = GridPane.getColumnIndex(clickedNode);
			rowIndex = GridPane.getRowIndex(clickedNode);
		}
		if (rowIndex == 0)
			return colIndex;

		return gpPersoonlijkBezit.getColumnConstraints().size() - 2 + colIndex;
	}

	public int clickTafel(MouseEvent event, Steen s) {
		// WERKT
		Node target = (Node) event.getTarget();
		// traverse towards root until gpTafel is the parent node
		if (target != gpTafel) {
			Node parent;
			while ((parent = target.getParent()) != gpTafel) {
				target = parent;
			}
		}
		Integer colInd = gpTafel.getColumnIndex(target);
		Integer rowInd = gpTafel.getRowIndex(target);

		if (rowInd == null) {
		} else {
			String steen = s.toString().toUpperCase();
			File file = new File(String.format("Small/%S.png", steen));
			Image image = new Image(file.toURI().toString());
			gpTafel.add(new ImageView(image), colInd, rowInd);
			steen = "";
		}
		return rowInd;
	}
	
	public List<Integer> clickTafelIsJoker(MouseEvent event) {
		// WERKT
		Node target = (Node) event.getTarget();
		// traverse towards root until gpTafel is the parent node
		if (target != gpTafel) {
			Node parent;
			while ((parent = target.getParent()) != gpTafel) {
				target = parent;
			}
		}
		Integer colInd = gpTafel.getColumnIndex(target);
		Integer rowInd = gpTafel.getRowIndex(target);

		if (rowInd == null) {
		} else {
//			System.out.printf("Mouse entered cell [%d, %d]%n", rowInd.intValue());
//			String tekst = steen.getGetal() + " " + steen.getKleur();
//			Text steenText = new Text(tekst);
//				    gpTafel.add(steenText, colInd, rowInd);
//			    tekst = "";
		}
		List<Integer> indexen = new ArrayList<>();
		indexen.add(rowInd);
		indexen.add(colInd);
		return indexen;
	}

	public void maakLabelsAan() {
		// labels in gpTafel toevoegen !!!!!!!!!!!
		for (int rij = 0; rij < 14; rij++) {
			for (int kolom = 0; kolom < 11; kolom++) {
				gpTafel.add(new ImageView(), rij, kolom);
			}
		}
		// labels in gpWerkveld
		for (int kolom = 0; kolom < 3; kolom++) {
			gpWerkveld.add(new ImageView(), kolom, 0);
		}
		
		for (int kolom = 0; kolom < 14; kolom++) {
			gpLosseStenen.add(new ImageView(), kolom, 0);
		}
	}

	public void clickWerkveld(MouseEvent event, Steen s) {

		Node target = (Node) event.getTarget();
		// traverse towards root until gpTafel is the parent node
		if (target != gpWerkveld) {
			Node parent;
			while ((parent = target.getParent()) != gpWerkveld) {
				target = parent;
			}
		}
		Integer colInd = gpWerkveld.getColumnIndex(target);
		Integer rowInd = gpWerkveld.getRowIndex(target);

		if (colInd == null || rowInd == null) {
		} else {
			String steen = s.toString().toUpperCase();
			File file = new File(String.format("Small/%S.png", steen));
			Image image = new Image(file.toURI().toString());
			gpTafel.add(new ImageView(image), colInd, rowInd);
			steen = "";

		}

	}

	public void tafelWeergeven(String s, int rijIndex, int kolIndex) {
		String steen = s.toString().toUpperCase();
		File file = new File(String.format("Small/%S.png", steen));
		Image image = new Image(file.toURI().toString());
		gpTafel.add(new ImageView(image), kolIndex, rijIndex);
	}

	public void werkveldWeergeven(Steen s, int kolIndex) {
		String steen = s.toString().toUpperCase();
		File file = new File(String.format("Small/%S.png", steen));
		Image image = new Image(file.toURI().toString());
		gpWerkveld.add(new ImageView(image), kolIndex, 0);
	}

	public void clearWerkveld() {
		gpWerkveld.getChildren().clear();
	}

	public void clearTafel() {
		gpTafel.getChildren().clear();
		maakLabelsAan();
	}


	public void losseStenenWeergeven(Steen s, int col) {
		String steen = s.toString().toUpperCase();
		File file = new File(String.format("Small/%S.png", steen));
		Image image = new Image(file.toURI().toString());
		gpLosseStenen.add(new ImageView(image), col, 0);
		
	}

	public void clearLosseStenen() {
		gpLosseStenen.getChildren().clear();
		maakLabelsAan();
		
	}

	public int clickLosseStenen(MouseEvent e) {
		
		Integer colIndex = 0;
		clickedNode = e.getPickResult().getIntersectedNode();
		if (clickedNode != gpPersoonlijkBezit) {
			// click on descendant node
			colIndex = GridPane.getColumnIndex(clickedNode);
			
		}
//		if (rowIndex == 0)
//			return colIndex;

		return colIndex;
		
	}

}

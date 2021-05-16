package domein;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import exceptions.FoutiefAantalSpelers;
import exceptions.FoutiefInloggegevensException;
import exceptions.FoutieveKeuzeException;
import exceptions.SpelerReedsAanwezigException;
import persistentie.SpelerMapper;

public class DomeinController{
	
	//attributen
	private Spel spel; 
	private SpelerRepository spelerRepo = new SpelerRepository();
	
	//methodes
	public void bepaalAantalSpelers(int aantalSpelers) throws FoutiefAantalSpelers, InputMismatchException, FoutieveKeuzeException 
	{
		spel = new Spel(aantalSpelers);
	}
	
	public void meldAan(String gebruikersnaam, String wachtwoord) throws Exception, FoutiefInloggegevensException, SpelerReedsAanwezigException 
	{
		Speler speler = spelerRepo.meldAan(gebruikersnaam, wachtwoord);
		if(speler != null) spel.voegSpelerToe(speler);
	}
	
	public int getAantalSpelers() {
		return spel.getAantalSpelers();
	}
	
	
	public List<String> geefGebruikersnamen() 
	{		
		return spel.gebruikersnamenOpvragen();	
	}
	
	public List<String> geefKeuzemogelijkheid() 
	{
		List<String> km = new ArrayList<>();
		
		km.add("Speel spel");
		km.add("Toon overzicht");
		
		return km;
	}
	
	public boolean isVolzet() {
		return spel.isVolzet();
	}

	//START ITERATIE 2
	//================================================================================================================================================================

	public void startNieuwSpel() { 
		spel.startNieuwSpel();
	}
	
	public String geefSpelerAanDeBeurt() {
		return spel.geefSpelerAanDeBeurt();
	}
	
	public void speelBeurt() {
		//ITERATIE 3
		if(!spel.eindeIsBereikt())
			startBeurt();
	}
	
	public List<SpelerDisplay> geefScores() {
		//ITERATIE 4
		return spel.geefSpelersDisplay();
	}
	
	//START ITERATIE 3
	//================================================================================================================================================================
	
	public void startBeurt() {
		spel.startBeurt();	// aan te passen?
	}
	
	public void backup() {
		spel.backup();
	}
	
//	public ArrayList<ArrayList<Steen>> toonSpelstituatie() {
//		return spel.toonSpelsituatie();	//aan te passen?
//	}
	
	public void selecteerSteen(int index) {
		spel.selecteerSteen(index);
	}
	
	public Steen legSteenAan() {
		return spel.legSteenAan();
	}
	
	public List<String> toonMogelijkeActies(){
		List<String> mogelijkeActies = new ArrayList<String>();
		
		mogelijkeActies.add("Aanleggen van een steen");
		mogelijkeActies.add("Serie of rij splitsen");
		mogelijkeActies.add("Joker vervangen");
		mogelijkeActies.add("Beurt beëndigen");
		mogelijkeActies.add("Reset van de beurt");
		
		return mogelijkeActies;
	}
	
	// weergeven in gui
	public List<Steen> toonPersoonlijkBezit() {
		return spel.toonPersoonlijkBezit();
		
	}
	
	public void verwijderSteen(Steen steen) {
		try {
			spel.verwijderSteen(steen);
		}catch(NullPointerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void reset() {
		spel.reset();
	}

	public List<VerzamelingSteen> toonTafel() {
		return spel.toonTafel();
	}
	
	public List<Steen> toonWerkveld() {
		return spel.toonWerkveld();
	}

	public void voegSteenToeAanWerkVeld(Steen steen) {
		spel.voegSteenToeAanWerkVeld(steen);
		
	}

	public void controleBestaandeSerieRij(int index) {
		spel.controleBestaandeSerieRij(index);
	}

	public void neemSteen() {
		
		spel.steenUitSpelPot();
	}

//	public boolean controleerBeurt() {
//		return spel.controleerBeurt();
//	}
	
	public boolean dertigStenenGelegd() {
		return spel.dertigStenenGelegd();
	}

	public void steenAanSerieRij(Steen steen, int rijIndex) {
		spel.steenAanSerieRij(steen, rijIndex);
		
	}

	public String getSpeler() {
		return spel.spelerAanBeurt();
	}

	public boolean valideerSpel() {
		return spel.valideerSpel();
		
	}

	public void splits(int row, int col) {
		spel.splitsSerieofRij(row, col);
		
	}

	public List<Steen> toonLosseStenen() {
		return spel.toonLosseStenen();
	}

	public void clickTafelIsJoker(Steen joker, List<Integer> indexen, Steen steen) {
		spel.clickTafelIsJoker(joker, indexen, steen);
		
	}

	public void selecteerSteenLosseStenen(int clickLosseStenen) {
		spel.selecteerSteenLosseStenen(clickLosseStenen);
		
	}
	
	public void beeindigSpel() {
		spel.beeindigSpel();
	}

	public void maakBackupPersoonlijkBezit() {
		spel.maakBackupPersoonlijkBezit();
	}
	
	public String toonVolgordeSpelers() {
		return spel.toonVolgordeSpelers();
	}
	
	//-------------------------------------------------------------------------------TESTKLASSE------------------------------------------------------------------------------------------------
	public void setGeselecteerdeStenenSom() {
		spel.setGeselecteerdeStenenSom();
	}
	public Steen legSteenAanTesKlasse(Steen steen) {
		return spel.legSteenAanTesKlasse(steen);
	}

	public boolean zetIsGezet() {
		return spel.zetIsGezet();
	}
	
	public Spel getSpel() {
		return spel;
	}


}


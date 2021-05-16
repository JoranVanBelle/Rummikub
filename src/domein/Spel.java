package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import exceptions.FoutiefAantalSpelers;
import exceptions.SpelerReedsAanwezigException;

public class Spel {

	public List<Speler> spelers = new ArrayList<>();
	private int aantalSpelers;
	private final int MIN = 2, MAX = 4;
	private static final int TOTAALAANTALBLOKJES = 106;
	private static final int TOTAALAANTALKLEUREN = 4;
	private List<Steen> stenen = new ArrayList<>();
	private int spelerAanBeurt;
	private SpelPot spelPot;
	private Tafel tafel;
	private Steen geselecteerdeSteen;
	private int geselecteerdeStenenSom = 0;
	private ArrayList<ArrayList<Steen>> spelsituatie;
	private static final int minAantalStenenEersteZet = 10;

	// constructor
	public Spel(int aantalSpelers) throws FoutiefAantalSpelers {
		setAantalSpelers(aantalSpelers);

	}

	// getters en setters
	private void setAantalSpelers(int aantalSpelers) throws FoutiefAantalSpelers {

		if (aantalSpelers < MIN || aantalSpelers > MAX)
			throw new FoutiefAantalSpelers(
					String.format("Er moeten minimum %d en maximum %d spelers het spel spelen!", MIN, MAX));
		this.aantalSpelers = aantalSpelers;
	}

	public int getAantalSpelers() {
		return aantalSpelers;
	}

	private boolean spelerReedsAanwezig(Speler speler) {
		// SPELER MAG NIET AL IN DB ZITTEN WANT ANDERS = DUBBEL
		// via het attribuut speler kunnen we kijken als deze al reeds aanwezig is in de
		// DB
		// ALS speler.getGebruikersnaam in DB zit dan return true
		// MAAR ALS dit niet zo is return false

		for (Speler sp : spelers) {
			if (sp.getGebruikersnaam().equals(speler.getGebruikersnaam()))
				return true;
		}

		return false;
	}

	public boolean isVolzet() {
		return spelers.size() == aantalSpelers;
	}

	public void voegSpelerToe(Speler speler) throws IllegalArgumentException, SpelerReedsAanwezigException {
		// voeg speler toe aan een lijst van spelers
		if (isVolzet())
			throw new IllegalArgumentException("Aantal spelers is bereikt");
		if (spelerReedsAanwezig(speler)) {
			throw new SpelerReedsAanwezigException();
		}
		speler.resetWachtwoord();
		spelers.add(speler);
	}

	public List<String> gebruikersnamenOpvragen() {
		return spelers.stream().map(Speler::getGebruikersnaam).collect(Collectors.toList()); // list van strings maken;
																								// .stream maakt
																								// werklijst van
																								// dynamische lijst door
	}

	public List<SpelerDisplay> geefSpelersDisplay() {
		return spelers.stream().map(speler -> (SpelerDisplay) speler).collect(Collectors.toList());
	}

	// START ITERATIE 2
	// ================================================================================================================================================================
	public void startNieuwSpel() {

		if (!isVolzet()) {
			throw new IllegalArgumentException();
		}
		spelerAanBeurt = 0;
		spelPot = new SpelPot();
		spelPot.maakStenenAan();
		verdeelStenenAanSpeler();
		bepaalVolgorde();
		tafel = new Tafel();
	}

	private void verdeelStenenAanSpeler() {

		for (Speler speler : spelers) {
			speler.setStenenInBezit(spelPot.geefStenen());
		}
	}

	public void bepaalVolgorde() {
		Collections.shuffle(spelers);

	}

	public boolean eindeIsBereikt() {
		for (Speler speler : spelers) {
			if (speler.bepaalWinnaar()) {
				verdeelScores();
				return true;
			}
		}
		return false;
	}

	public void bepaalScoreWinnaar(int punten) {
		for (Speler speler : spelers)
			if (speler.bepaalWinnaar())
				speler.setScore(punten);
	}

	public String geefSpelerAanDeBeurt() {
		String s = spelers.get(spelerAanBeurt).getGebruikersnaam();
		return s;
	}

	private void bepaalVolgendeSpeler() {
		spelerAanBeurt = (spelerAanBeurt + 1) % spelers.size();
	}

	private void verdeelScores() {
		int totaal = 0;
		for (Speler speler : spelers) {
			int scoreSpeler = speler.somStenenInBezit();
			totaal += scoreSpeler;
		}

		bepaalScoreWinnaar(totaal * -1);
	}

// START ITERATIE 3
// ================================================================================================================================================================

	public void startBeurt() {
		geselecteerdeStenenSom = 0;
		backup();
		bepaalVolgendeSpeler();
	}

	public void backup() {
		tafel.tijdelijkeOpslag();
		spelers.get(spelerAanBeurt).setPersoonlijkBezitBackup();
	}

//	public ArrayList<ArrayList<Steen>> toonSpelsituatie() {	// stenen op tafel en stenen in bezit in 1 arraylist
//		
//		spelsituatie.add((ArrayList<Steen>) spelers.get(spelerAanBeurt).getStenenInBezit());
//		
//		valideerSpelSituatie();
//		
//		
//		return spelsituatie;
//	}

	// nog af te werken!
//	private void valideerSpelSituatie() {
//		
//		valideerBeurt();
//	}
//	
//	private void valideerBeurt() {
//		
//	}

	public Steen legSteenAan() {
//		tafel.legSteenAan(serieOfRij, steen);
		Steen steen = geselecteerdeSteen;
		try {
//			setGeselecteerdeStenenSom();
			return steen;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void splitsSerieofRij(int rijIndex, int kolIndex) {
		tafel.splitsSerieofRij(rijIndex, kolIndex); // nog aante vullen
	}

//	private void steenNaarWerkveld() {
//		
//	}

	public void selecteerSteen(int index) {
		geselecteerdeSteen = spelers.get(spelerAanBeurt).getStenenInBezit().get(index);
	}

	public List<String> toonMogelijkeActies() {
		return null;

	}

	public List<Steen> toonPersoonlijkBezit() {
		return spelers.get(spelerAanBeurt).getStenenInBezit();
	}

	public void verwijderSteen(Steen steen) {
		geselecteerdeSteen = null;
		spelers.get(spelerAanBeurt).getStenenInBezit().remove(steen);
	}

	public void VoegSteenToeAanPersoonlijkBezit(Steen steen) {
		spelers.get(spelerAanBeurt).getStenenInBezit().add(steen);
	}

	public void reset() {
		System.out.println("Dit is de backup voor het resetten: " + tafel.getVerzamelingBackup());
		geselecteerdeStenenSom = 0;
		spelers.get(spelerAanBeurt).reset();
		tafel.reset();
	}

	public List<VerzamelingSteen> toonTafel() {
		return tafel.toonTafel();
	}

	public List<Steen> toonWerkveld() {
		return tafel.toonWerkveld();
	}

	public void controleBestaandeSerieRij(int index) {
		tafel.controleBestaandeSerieRij(index);

	}

	public void steenUitSpelPot() {
		spelers.get(spelerAanBeurt).getStenenInBezit().add(spelPot.steenUitSpelpot());
	}

	public void voegSteenToeAanWerkVeld(Steen steen) {
		boolean isJoker = false;
		List<Steen> tijdelijkWerkveld = new ArrayList<>(tafel.getTijdelijkWerkveld());
		List<VerzamelingSteen> verzameling = new ArrayList<>(tafel.getVerzameling());

		tijdelijkWerkveld.add(steen);

		if (tijdelijkWerkveld.size() == 2) {
			for (Steen s : tijdelijkWerkveld) {

				if (s.getKleur() == Kleur.JOKER)
					isJoker = true;
			}
		}

		if (tijdelijkWerkveld.size() == 3 || (!isJoker && tijdelijkWerkveld.size() == 2)) {
			if (Rij.isRij(tijdelijkWerkveld)) {
				verzameling.add(new Rij(tijdelijkWerkveld));
			} else if (Serie.isSerie(tijdelijkWerkveld)) {
				verzameling.add(new Serie(tijdelijkWerkveld));
			} else {
				for (Steen steen1 : tijdelijkWerkveld) {
					VoegSteenToeAanPersoonlijkBezit(steen1);
					geselecteerdeStenenSom -= steen1.getGetal();
				}
					
			}
			tijdelijkWerkveld.removeAll(tijdelijkWerkveld);

		}
		tafel.setTijdelijkWerkveld(tijdelijkWerkveld);
		tafel.setVerzameling(verzameling);
	}

	public void setGeselecteerdeStenenSom() {
		this.geselecteerdeStenenSom += geselecteerdeSteen.getGetal();
		geselecteerdeSteen = null;
	}

	public boolean dertigStenenGelegd() {

		geselecteerdeStenenSom = 0;
		List<VerzamelingSteen> verschil = new ArrayList<>(tafel.getVerzameling());
		
		verschil.removeAll(tafel.getVerzamelingBackup());
//		System.out.println(verschil);
		
		for(int rij = 0; rij < verschil.size(); rij++) {
			for(int kol = 0; kol < verschil.get(rij).getSize(); kol++) {
				if(verschil.get(rij).geefSteen(kol).getKleur().equals(Kleur.JOKER)) {
					if(verschil.get(rij) instanceof Rij) {
						if(kol != 0)
							geselecteerdeStenenSom += verschil.get(rij).geefSteen(kol - 1).getGetal();
						else {
							geselecteerdeStenenSom += verschil.get(rij).geefSteen(kol + 1).getGetal();
						}
					} else {
						if(kol != 0)
							geselecteerdeStenenSom += verschil.get(rij).geefSteen(kol - 1).getGetal() + 1;
						else {
							geselecteerdeStenenSom += verschil.get(rij).geefSteen(kol + 1).getGetal() - 1;
						}
					}
				} else {
					geselecteerdeStenenSom += verschil.get(rij).geefSteen(kol).getGetal();
				}
			}
		}
		
		System.out.println("geselecteerde som" + geselecteerdeStenenSom);
		if(spelers.get(spelerAanBeurt).isEersteZet()) {
			if(geselecteerdeStenenSom >= minAantalStenenEersteZet) {
				spelers.get(spelerAanBeurt).setEersteZet();
				return true;
			}
			return false;
		}
		return true;
	}

	public void steenAanSerieRij(Steen steen, int rijIndex) {
			if(!tafel.getVerzameling().get(rijIndex).steenAanleggen(steen)) {
				spelers.get(spelerAanBeurt).getStenenInBezit().add(steen);
			}
	}

	public String spelerAanBeurt() {
		return spelers.get(spelerAanBeurt).getGebruikersnaam();
	}

	public boolean valideerSpel() {
		for(int i = 0; i < tafel.getVerzameling().size(); i++) {
			if (tafel.getVerzameling().get(i).getSize() < 3) {
				return false;
			}
		}

		return true;
		
	}

	public List<Steen> toonLosseStenen() {
		return tafel.getLosseStenen();
	}

	public void clickTafelIsJoker(Steen joker, List<Integer> indexen, Steen steen) {
		tafel.clickTafelIsJoker(joker, indexen, steen);
		
	}

	public void selecteerSteenLosseStenen(int clickLosseStenen) {
		geselecteerdeSteen = tafel.selecteerSteenLosseStenen(clickLosseStenen);
		
	}
	
	//Als een speler niets heeft gelegd moet hij een steen nemen
	public boolean zetIsGezet() {
		return tafel.zetIsGezet();
	}
	
	public void beeindigSpel() {
		spelers.get(spelerAanBeurt).getStenenInBezit().clear();
	}
	
	public void maakBackupPersoonlijkBezit() {
		spelers.get(spelerAanBeurt).setPersoonlijkBezitBackup();
	}
	
	public String toonVolgordeSpelers() {
		return spelers.stream().map(e -> e.getGebruikersnaam()).collect(Collectors.joining("\n"));
	}
	
	//--------------------------------------------------------------------------TESTKLASSE----------------------------------------------------------------------------------------------
	//aanleggen van een steen in de testklasse
	public Steen legSteenAanTesKlasse(Steen steen) {
		geselecteerdeSteen = steen;
			return steen;
	}
	
	public Tafel getTafel() {
		return tafel;
	}

}
package testen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DomeinController;
import domein.Kleur;
import domein.Rij;
import domein.Serie;
import domein.Spel;
import domein.Steen;
import domein.Tafel;
import domein.VerzamelingSteen;
import exceptions.FoutiefInloggegevensException;
import exceptions.SpelerReedsAanwezigException;

class TestKlasse_SteenAanleggen {
	private List<Steen> stenenRij = new ArrayList<>();
	private List<Steen> stenenSerie = new ArrayList<>();
	private Rij rij;
	private Serie serie;
	DomeinController dc = new DomeinController();
	private Spel spel;
	private Tafel tafel;
	
	@BeforeEach
	public void setUpBeforeClass() throws FoutiefInloggegevensException, SpelerReedsAanwezigException, Exception  {
		//spel aanmaken
		dc.bepaalAantalSpelers(2);
		dc.meldAan("z", "z");
		dc.meldAan("a", "a");
		dc.startNieuwSpel();
		dc.startBeurt();
		dc.dertigStenenGelegd();
		//serie en rij aanmaken

		rij = new Rij(new ArrayList<>(stenenRij));
		serie = new Serie(new ArrayList<>(stenenSerie));
		//verzameling aanmaken
		
		spel = dc.getSpel();
		tafel = spel.getTafel();
	}

	//methodes isSerieOfRij
	@Test
	public void isSerie_isJuisteSerie_retourneertTrue() {
		stenenRij.add(new Steen(Kleur.BLAUW, 5));
		stenenRij.add(new Steen(Kleur.ROOD, 5));
		stenenRij.add(new Steen(Kleur.GEEL, 5));
		//Assert
		assertTrue(Serie.isSerie(stenenSerie));
	}
	@Test
	public void isSerie_isFouteSerie_retourneertFalse() {
		stenenRij.add(new Steen(Kleur.BLAUW, 5));
		stenenRij.add(new Steen(Kleur.ROOD, 5));
		stenenRij.add(new Steen(Kleur.GEEL, 5));
		//Assert
		assertFalse(Serie.isSerie(stenenRij));
	}
	@Test
	public void isRij_isJuisteRij_retourneertTrue() {
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		//Assert
		assertTrue(Serie.isSerie(stenenRij));
	}
	@Test
	public void isRij_isFouteRij_retourneertFalse() {
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		//Assert
		assertFalse(Serie.isSerie(stenenSerie));
	}
	
	
	//methodes steenAanleggen
	@Test
	public void steenAanleggenSerie_7achteraanaanleggen_retourneertTrue() {
		//arrange
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		serie = new Serie(new ArrayList<>(stenenSerie));
		
		//act
		//steen in geselecteerde steen 
		dc.legSteenAanTesKlasse(new Steen(Kleur.GEEL, 31));
		//we zetten eersteZet op false zodat we stenen aan tafel kunnen toevoegen...
		dc.setGeselecteerdeStenenSom();
		Steen steen = new Steen(Kleur.BLAUW, 7);
		dc.voegSteenToeAanWerkVeld(new Steen(Kleur.BLAUW, 5));
		dc.voegSteenToeAanWerkVeld(new Steen(Kleur.BLAUW, 6));
		dc.steenAanSerieRij(steen, 0);
		
		//Assert	
		assertTrue(serie.toString().equals(dc.toonTafel().get(0).toString()));
		
	}
	
	@Test
	public void steenAanleggenSerie_4voorraan_retourneertTrue() {
		stenenSerie.add(new Steen(Kleur.BLAUW, 4));
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		serie = new Serie(new ArrayList<>(stenenSerie));
		
		//act
		//steen in geselecteerde steen 
		dc.legSteenAanTesKlasse(new Steen(Kleur.GEEL, 31));
		//we zetten eersteZet op false zodat we stenen aan tafel kunnen toevoegen...
		dc.setGeselecteerdeStenenSom();
		Steen steen = new Steen(Kleur.BLAUW, 4);
		dc.voegSteenToeAanWerkVeld(new Steen(Kleur.BLAUW, 5));
		dc.voegSteenToeAanWerkVeld(new Steen(Kleur.BLAUW, 6));
		dc.steenAanSerieRij(steen, 0);
		
		//Assert	
		assertTrue(serie.toString().equals(dc.toonTafel().get(0).toString()));
		
	}
	
	@Test
	public void steenAanleggenSerie_FoutiefKleur_retourneertFalse() {
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		serie = new Serie(new ArrayList<>(stenenSerie));
		
		//act
		//steen in geselecteerde steen 
		dc.legSteenAanTesKlasse(new Steen(Kleur.GEEL, 31));
		//we zetten eersteZet op false zodat we stenen aan tafel kunnen toevoegen...
		dc.setGeselecteerdeStenenSom();
		Steen steen = new Steen(Kleur.GEEL, 4);
		dc.voegSteenToeAanWerkVeld(new Steen(Kleur.BLAUW, 5));
		dc.voegSteenToeAanWerkVeld(new Steen(Kleur.BLAUW, 6));
		//steen proberen toevoegen
		dc.steenAanSerieRij(steen, 0);
		
		
		//Assert	
		assertTrue(serie.toString().equals(dc.toonTafel().get(0).toString()));
		
		
	}	
	
	

}

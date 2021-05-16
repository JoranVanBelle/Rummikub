package testen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

class TestKlasse_JokerVervangen {

	private List<Steen> stenenRij = new ArrayList<>();
	private List<Steen> stenenSerie = new ArrayList<>();
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

		//verzameling aanmaken
		
		spel = dc.getSpel();
		tafel = spel.getTafel();
	}

	//==========================================
	//SERIES
	//==========================================
	//JUIST
	@Test
	public void jokerVervangen_Vooraan_metSerie_retourneertTrue() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(0);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(joker);
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.BLAUW, 5);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(5, stenen.get(0).getGetal());
		Assertions.assertEquals(Kleur.BLAUW, stenen.get(0).getKleur());
	}
	
	@Test
	public void jokerVervangen_achter_metSerie_retourneertFalse() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(2);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		stenenSerie.add(joker);
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.BLAUW, 8);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(8, stenen.get(2).getGetal());
		Assertions.assertEquals(Kleur.BLAUW, stenen.get(2).getKleur());
	}
	
	@Test
	public void jokerVervangen_midden_metSerie_retourneertFalse() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(1);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(joker);
		stenenSerie.add(new Steen(Kleur.BLAUW, 8));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.BLAUW, 7);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(7, stenen.get(1).getGetal());
		Assertions.assertEquals(Kleur.BLAUW, stenen.get(1).getKleur());
	}
	
	//NIET JUIST
	@Test
	public void jokerVervangen_Vooraan_metSerie_WijzigtNiet_retourneertTrue() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(0);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(joker);
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.BLAUW, 8);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(25, stenen.get(0).getGetal());
		Assertions.assertEquals(Kleur.JOKER, stenen.get(0).getKleur());
	}
	
	@Test
	public void jokerVervangen_achter_metSerie_WijzigtNiet_retourneertFalse() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(2);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		stenenSerie.add(joker);
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.BLAUW, 5);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(25, stenen.get(2).getGetal());
		Assertions.assertEquals(Kleur.JOKER, stenen.get(2).getKleur());
	}
	
	@Test
	public void jokerVervangen_midden_metSerie_WijzigtNiet_retourneertFalse() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(1);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(joker);
		stenenSerie.add(new Steen(Kleur.BLAUW, 8));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.BLAUW, 10);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(25, stenen.get(1).getGetal());
		Assertions.assertEquals(Kleur.JOKER, stenen.get(1).getKleur());
	}
	
	// MET KLEUREN - NIET JUIST
	@Test
	public void jokerVervangen_Vooraan_KLEUR_metSerie_WijzigtNiet_retourneertTrue() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(0);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(joker);
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.GEEL, 5);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(25, stenen.get(0).getGetal());
		Assertions.assertEquals(Kleur.JOKER, stenen.get(0).getKleur());
	}
	
	@Test
	public void jokerVervangen_achter_KLEUR_metSerie_WijzigtNiet_retourneertFalse() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(2);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		stenenSerie.add(joker);
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.GEEL, 8);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(25, stenen.get(2).getGetal());
		Assertions.assertEquals(Kleur.JOKER, stenen.get(2).getKleur());
	}
	
	@Test
	public void jokerVervangen_midden_KLEUR_metSerie_WijzigtNiet_retourneertFalse() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(1);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(joker);
		stenenSerie.add(new Steen(Kleur.BLAUW, 8));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.GEEL, 7);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(25, stenen.get(1).getGetal());
		Assertions.assertEquals(Kleur.JOKER, stenen.get(1).getKleur());
	}
	
	
	//==========================================
	//RIJEN
	//==========================================
	//JUIST
	
	@Test
	public void jokerVervangen_metRij_retourneertFalse() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(1);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenRij.add(new Steen(Kleur.BLAUW, 6));
		stenenRij.add(joker);
		stenenRij.add(new Steen(Kleur.ROOD, 6));
		
		Rij rij = new Rij(stenenRij);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(rij);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.GEEL,6);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(6, stenen.get(1).getGetal());
		Assertions.assertEquals(Kleur.GEEL, stenen.get(1).getKleur());
	}
	
	//NIET JUIST
	@Test
	public void jokerVervangen_metRij_WijzigtNiet_retourneertTrue() {
		//arrange
		List<Integer> indexen = new ArrayList<>();
		indexen.add(0);
		indexen.add(0);
		Steen joker = new Steen(Kleur.JOKER, 25);
		
		stenenSerie.add(joker);
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		Steen steen = new Steen(Kleur.BLAUW, 8);
		
		dc.clickTafelIsJoker(joker, indexen, steen);
		List<VerzamelingSteen> verzameling2 = tafel.getVerzameling();
		VerzamelingSteen vz= verzameling2.get(0);
		List<Steen> stenen = vz.getStenen();
		Assertions.assertEquals(25, stenen.get(0).getGetal());
		Assertions.assertEquals(Kleur.JOKER, stenen.get(0).getKleur());
	}
	
}

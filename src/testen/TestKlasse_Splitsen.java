package testen;

import static org.junit.jupiter.api.Assertions.*;

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

class TestKlasse_Splitsen {

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
	
	@Test
	public void SerieSplitsen_tweeLang_komtInLosseStenen() {
		// arrange
		int rijIndex = 0;
		int kolIndex = 1;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(2, tafel.getLosseStenen().size());
	}
	
	@Test
	public void SerieSplitsen_drieLang_komtInLosseStenen() {
		// arrange
		int rijIndex = 0;
		int kolIndex = 1;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(1, tafel.getLosseStenen().size());
	}
	
	@Test
	public void SerieSplitsen_vierLang_tweeAparteSeriesOpTafel(){
		// arrange
		int rijIndex = 0;
		int kolIndex = 2;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		stenenSerie.add(new Steen(Kleur.BLAUW, 8));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(0, tafel.getLosseStenen().size());
	}
	
	@Test
	public void SerieSplitsen_vierLangEindeSplitsen_eenSteenInLosseStenen(){
		// arrange
		int rijIndex = 0;
		int kolIndex = 3;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		stenenSerie.add(new Steen(Kleur.BLAUW, 8));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(1, tafel.getLosseStenen().size());
	}
	
	@Test
	public void SerieSplitsen_vierLangBeginSplitsen_eenSteenInLosseStenen(){
		// arrange
		int rijIndex = 0;
		int kolIndex = 0;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.BLAUW, 7));
		stenenSerie.add(new Steen(Kleur.BLAUW, 8));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(1, tafel.getLosseStenen().size());
	}
	
	//-------------------------------------------------------------------------------------------
	// Met rijen
	//-------------------------------------------------------------------------------------------

	@Test
	public void RijSplitsen_tweeLang_komtInLosseStenen() {
		// arrange
		int rijIndex = 0;
		int kolIndex = 1;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 6));
		stenenSerie.add(new Steen(Kleur.GEEL, 6));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(2, tafel.getLosseStenen().size());
	}
	
	@Test
	public void RijSplitsen_drieLang_komtInLosseStenen() {
		// arrange
		int rijIndex = 0;
		int kolIndex = 1;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.GEEL, 5));
		stenenSerie.add(new Steen(Kleur.ROOD, 5));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(1, tafel.getLosseStenen().size());
	}
	
	@Test
	public void RijSplitsen_vierLang_tweeAparteSeriesOpTafel(){
		// arrange
		int rijIndex = 0;
		int kolIndex = 2;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.GEEL, 5));
		stenenSerie.add(new Steen(Kleur.ROOD, 5));
		stenenSerie.add(new Steen(Kleur.ZWART, 5));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(0, tafel.getLosseStenen().size());
	}
	
	@Test
	public void RijSplitsen_vierLangEindeSplitsen_eenSteenInLosseStenen(){
		// arrange
		int rijIndex = 0;
		int kolIndex = 3;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.GEEL, 5));
		stenenSerie.add(new Steen(Kleur.ROOD, 5));
		stenenSerie.add(new Steen(Kleur.ZWART, 5));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(1, tafel.getLosseStenen().size());
	}
	
	@Test
	public void RijSplitsen_vierLangBeginSplitsen_eenSteenInLosseStenen(){
		// arrange
		int rijIndex = 0;
		int kolIndex = 0;
		
		stenenSerie.add(new Steen(Kleur.BLAUW, 5));
		stenenSerie.add(new Steen(Kleur.GEEL, 5));
		stenenSerie.add(new Steen(Kleur.ROOD, 5));
		stenenSerie.add(new Steen(Kleur.ZWART, 5));
		
		Serie serie = new Serie(stenenSerie);
		List<VerzamelingSteen> verzameling = new ArrayList<>();
		verzameling.add(serie);
		
		tafel.setVerzameling(verzameling);
		
		dc.splits(rijIndex, kolIndex);
		
		Assertions.assertEquals(1, tafel.getLosseStenen().size());
	}

}

package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Tafel implements TafelDisplay {

	private static final int MIN_AANWEZIG = 3;
//	private List<List<Steen>> stenenOpTafel;
	private List<Steen> tijdelijkWerkveld; // Serie of rij aanmaken, na validatie serie/rij-object van maken; leeg op
											// einde beurt
	private List<Steen> losseStenen; // bv. bij splitsen serie in 3 en 1 wordt het 1 blokje naar hier, voor jokers,
										// ... (niet in valideren); leeg op einde beurt; keuze in welk werkveld
										// aanleggen
	private List<VerzamelingSteen> verzameling; // ligt op tafel -> List van een List 

	// BACKUPS
	private List<VerzamelingSteen> verzamelingBackup;
	private List<VerzamelingSteen> verzamelingExtraBackup;
	private List<Steen> losseStenenBackup = new ArrayList<>();

	Tafel() {
		tijdelijkWerkveld = new ArrayList<>();
		losseStenen = new ArrayList<>();
		verzameling = new ArrayList<>();
	}

	@Override
	public List<VerzamelingSteen> getStenenOpTafel() {
		return verzameling;
	}

	public List<VerzamelingSteen> getVerzamelingBackup() {
		return verzamelingBackup;
	}

	public void startBeurt() {
		tijdelijkeOpslag();
	}

	public void tijdelijkeOpslag() {
		verzamelingBackup = new ArrayList<>(verzameling);
		System.out.println("inhoud verzamelingBackup: " + verzamelingBackup + "\ninhoud verzamelig" + verzameling);
		losseStenenBackup = new ArrayList<>(losseStenen);
	}

	public void legSteenAan(Steen steen, int rijIndex) { // dubbelchecken: if mss niet nodig

		if (verzameling.get(rijIndex) instanceof Rij)
			verzameling.get(rijIndex).steenAanleggen(steen);
		else
			verzameling.get(rijIndex).steenAanleggen(steen);
	}

	public void splitsSerieofRij(int rijIndex, int kolIndex) {
		boolean naarLosseStenen = false;
		// 2 verzamelingen aanmaken
		List<Steen> stenen1 = new ArrayList<>();
		List<Steen> stenen2 = new ArrayList<>();
//		List<VerzamelingSteen> verzamelingSteen = new ArrayList<>();
//		verzamelingSteen.add(geefVerzamelingSteen(rijIndex));
		VerzamelingSteen verzamelingSteen = geefVerzamelingSteen(rijIndex);


		if(kolIndex != 0) {
			
			//lijstjes aanmaken
			for (int index = 0; index < kolIndex; index++) {
				stenen1.add(verzamelingSteen.geefSteen(index));
			}
			for (int index = kolIndex ; index < verzamelingSteen.getSize(); index++) {
				stenen2.add(verzamelingSteen.geefSteen(index));
			}

			System.out.println("STenen 1: " + stenen1 );
			System.out.println("STenen 2: " + stenen2 );
			//eerste lijst van stenen
			if (stenen1.size() == 1 || (stenen1.size() == 2 && verzamelingSteen.bevatJoker(stenen1))) {
				naarLosseStenen = true;
				losseStenen.addAll(stenen1);
			} else {
				verzamelingSteen.nieuweStenen(stenen1);
			}

			//tweede lijst van stenen
			if (stenen2.size() == 1 || (stenen2.size() == 2 && verzamelingSteen.bevatJoker(stenen2))) {
//				System.out.println("VOOR losse stenen toevoegen"+ verzamelingSteen);
				losseStenen.addAll(stenen2);
//				System.out.println("NA losse stenen toevoegen"+ verzamelingSteen);
			} else {
				nieuweSerieRij(verzamelingSteen, stenen2);
			}

			//controle
			if (naarLosseStenen) {
				verzameling.remove(rijIndex);
			}
		} else { //kolIndex is 0
			losseStenen.add(verzamelingSteen.geefSteen(0));
			for (int index = 1 ; index < verzamelingSteen.getSize(); index++) {
				stenen2.add(verzamelingSteen.geefSteen(index));
			}
			if(stenen2.size() == 2 && verzamelingSteen.bevatJoker(stenen2))
				losseStenen.addAll(stenen2);
			else
				nieuweSerieRij(verzamelingSteen, stenen2);
			verzameling.remove(rijIndex);
		}
//		System.out.println("VERZAMELING backup" + verzamelingBackup);
//		System.out.println("VERZAMELING Steen " + verzamelingSteen);
	}

	private void nieuweSerieRij(VerzamelingSteen verzamelingStenen, List<Steen> stenen) {
		if (verzamelingStenen instanceof Rij) {
			verzameling.add(new Rij(stenen));
		} else {
			verzameling.add(new Serie(stenen));
		}
	}
	

	private VerzamelingSteen geefVerzamelingSteen(int rijIndex) {
		return verzameling.get(rijIndex);
	}

	public void reset() {
		tijdelijkWerkveld.clear();
		losseStenen.clear();
		verzameling = new ArrayList<>(verzamelingBackup);
//		System.out.println("VERZAMELING backup IN TAFEL" + verzameling);
	}

	public List<VerzamelingSteen> toonTafel() {
		return verzameling;
	}

	public List<Steen> toonWerkveld() {
		return tijdelijkWerkveld;
	}

	public void controleBestaandeSerieRij(int index) {
		verzameling.size();
		
	}

	public List<Steen> getTijdelijkWerkveld() {
		return tijdelijkWerkveld;
	}

	public void setTijdelijkWerkveld(List<Steen> tijdelijkWerkveld) {
		this.tijdelijkWerkveld = new ArrayList<>(tijdelijkWerkveld);
	}

	public List<VerzamelingSteen> getVerzameling() {
		return verzameling;
	}

	public void setVerzameling(List<VerzamelingSteen> verzameling) {
		this.verzameling = new ArrayList<>(verzameling);
	}

	public List<Steen> getLosseStenen() {
		return losseStenen;
	}

	public void clickTafelIsJoker(Steen joker, List<Integer> indexen, Steen steen) {
		int rij = indexen.get(0);
		int kol = indexen.get(1);
		
		if(joker.getKleur() == Kleur.JOKER) {
			Steen jokerNieuw = verzameling.get(rij).jokerVervangen(joker, kol, steen);
			if(jokerNieuw.getKleur().equals(Kleur.JOKER))
				losseStenen.add(jokerNieuw);
		}
	}

	public Steen selecteerSteenLosseStenen(int clickLosseStenen) {
		return losseStenen.remove(clickLosseStenen);
		
	}
	
	//Als de speler geen steen legt dan moet hij een steen nemen
	public boolean zetIsGezet() {
//		System.out.println("Zijn verzameling en verzamelingBackUp verschillend: " + (verzameling.equals(verzamelingBackup)));
		return !verzameling.equals(verzamelingBackup);
	}


}

//	public ArrayList<ArrayList<Steen>> getStenenOpTafel() {
//		return stenenOpTafel;
//	}

// methodes
//	public void serieOfRijOpTafelLeggen(ArrayList<Steen> serieOfRij) {
//		stenenOpTafel.add(serieOfRij);
//
//	}

//	public void legSteenAan(ArrayList<Steen> serieOfRij, Steen steen) {
////		ArrayList<Steen> oud = serieOfRij;
////		stenenOpTafel.remove(serieOfRij);
////		oud.add(steen);
////		stenenOpTafel.add(oud);
//
//		serieOfRij.add(steen);
//
////		stenenOpTafel.add(stenenOpTafel.remove(stenenOpTafel.indexOf(serieOfRij)).add(steen));
//	}

//	public void steenUitSRVerwijderen(ArrayList<Steen> serieOfRij, Steen steen) {
//		serieOfRij.remove(steen);
//	}

//	public void splitsSerie(ArrayList<Steen> serie, int startSteen, int stopSteen) {	//  stenen blijven op tafel liggen
//		ArrayList<Steen> gewenste = new ArrayList<>();
//		ArrayList<Steen> subSR1 = new ArrayList<>();
//		ArrayList<Steen> subSR2 = new ArrayList<>();
//
//		// controles
//
//		// splitsen
//		gewenste = (ArrayList<Steen>) serie.subList(startSteen, stopSteen + 1);
//		if (startSteen != 0)
//			subSR1 = (ArrayList<Steen>) serie.subList(0, startSteen);
//		subSR2 = (ArrayList<Steen>) serie.subList(stopSteen + 1, serie.size());
//		if (subSR1.size() < MIN_AANWEZIG || subSR2.size() < MIN_AANWEZIG)
//
//		stenenOpTafel.add(gewenste);
//		stenenOpTafel.add(subSR1);
//		stenenOpTafel.add(subSR2);
//		stenenOpTafel.remove(serie);
//	}

//	private void serieOfRij(Steen steen) {	// 1 serie of rij per keer maken, vanaf 2 zichtbaar serie/rij en nieuwe maken mogelijk
//		tijdelijkWerkveld.add(steen);
//		tijdelijkWerkveld.add(steen);
//	}	// tijdelijk werkveld bij maken en tijdelijk werkveld voor maken van SR. alle stenen die alleen liggen verplaatsen naar werkveld. mogelijkheid tot maken nieuwe SR
//		// joker vervangen --> naar wrkveld brengen. werkveld niet valideren. werkveld nieuwe serie/rij valideren. tijdelijjk werkveld niet valideren; verzameling van losse stenen

//	@Override
//	public List<Steen> getStenenOpTafel() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public boolean isRij(ArrayList<Steen> rij) {
//		int index1 = 0;
//		int index2 = 1;
//
//		try {
//			for (; index1 < rij.size(); index1++) {
//				for (; index2 <= rij.size(); index2++) {
//					if (rij.get(index1).getKleur() == rij.get(index2).getKleur()
//							|| rij.get(index1).getGetal() != rij.get(index2).getGetal())
//						if (rij.get(index1).getKleur() != Kleur.JOKER && rij.get(index2).getKleur() != Kleur.JOKER)
//							return false;
//				}
//				index1++;
//				index2++;
//			}
//		} catch (Exception e) {
//		}
//		return true;
//	}
//
//	@Override 
//	public boolean isSerie(ArrayList<Steen> serie) {
//		int index1 = 0;
//		int index2 = 1;
//
//		try {
//			for (; index1 < serie.size(); index1++) {
//				for (; index2 <= serie.size(); index2++) {
//					if (serie.get(index1).getKleur() != serie.get(index2).getKleur()
//							|| serie.get(index1).getGetal() != serie.get(index2).getGetal() - 1)
//						if (serie.get(index1).getKleur() != Kleur.JOKER && serie.get(index2).getKleur() != Kleur.JOKER)
//							return false;
//				}
//				index1++;
//				index2++;
//			}
//		} catch (Exception e) {
//		}
//	
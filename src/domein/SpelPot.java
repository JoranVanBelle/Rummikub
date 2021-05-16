package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpelPot {

	private List<Steen> stenen;
	private static final int STARTAANTALBLOKJES = 14;

	SpelPot() {
		// maakstenen
		stenen = new ArrayList<>();
	}

	public int getSpelPotStenenSize() {
		return stenen.size();
	}

	public void maakStenenAan() {

		for (int aantalKeer = 1; aantalKeer <= 2; aantalKeer++) // 2x elk blokje aanmaken
		{

			for (int getal = 1; getal <= 13; getal++)// getallen op blokjes afgaan
			{

				for (Kleur kleur : Kleur.values()) {
					if (kleur != Kleur.JOKER) {
						stenen.add(new Steen(kleur, getal));
					}
				}
			}
			stenen.add(new Steen(Kleur.JOKER, 25));

		}
		shuffleStenen();
	}
	
	private void shuffleStenen() {
		Collections.shuffle(stenen);
	}
	
	public Steen steenUitSpelpot() {
		return stenen.remove(0);
	}

	public List<Steen> geefStenen() {
		List<Steen> stenenLijst = new ArrayList<>();
		for(int index = 0; index < STARTAANTALBLOKJES; index++) {
			stenenLijst.add(stenen.remove(0));
		}
		return stenenLijst;
	}
	
}

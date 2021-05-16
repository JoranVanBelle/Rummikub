package domein;

import java.util.ArrayList;
import java.util.List;

public class Serie extends VerzamelingSteen { // zelfde kleur, opeenvolgende cijfers

	public Serie(List<Steen> stenen) {
		super(stenen);
	}

	@Override
	public boolean steenAanleggen(Steen steen) {
		if (valideerVooraan(steen)) {
			getStenen().add(0, steen);
			return true;
		}
		if (valideerAchteraan(steen)) {
			getStenen().add(steen);
			return true;
		}

		return false;
	}

	// 2x controleren
	private boolean valideerVooraan(Steen steen) {
		Steen s = getStenen().get(0);

		if(steen.getKleur().equals(Kleur.JOKER) && s.getGetal() > 1)
			return true;
		
		if (steen.getGetal() + 1 != s.getGetal() || !steen.getKleur().equals(s.getKleur()))
			return false;
		

		return true;
	}

	private boolean valideerAchteraan(Steen steen) {
		Steen s = getStenen().get(getStenen().size() - 1);

		if (steen.getGetal() != s.getGetal() + 1 || !(steen.getKleur().equals(s.getKleur())))
			return false;

		return true;
	}

//	private boolean valideerMidden(Steen steen, int kolIndex) {
//		List<Steen> stenen = getStenen();
//
//		Steen steenVoor = stenen.get(kolIndex - 1);
//		Steen steenAchter = stenen.get(kolIndex + 1);
//
//		if (steenVoor.getKleur() == steen.getKleur()) {
//			if (steenVoor.getGetal() + 1 == steen.getGetal())
//				return true;
//			if (steenAchter.getGetal() - 1 == steen.getGetal())
//				return true;
//		}
//
//		return false;
//	}

	public static boolean isSerie(List<Steen> serie) {
		int index1 = 0;
		int index2 = 1;
		boolean jokerAanwezig = serie.stream().anyMatch(e -> e.getKleur() == Kleur.JOKER);
		try {
			for (; index1 < serie.size(); index1++) {
				for (; index2 <= serie.size(); index2++) {
					if (serie.get(index1).getKleur() != serie.get(index2).getKleur()
							|| serie.get(index1).getGetal() != serie.get(index2).getGetal() - 1)
						if (!jokerAanwezig)
								return false;
				}
				index1++;
				index2++;
			}
		} catch (Exception e) {
		}
		return true;
	}

	public Steen jokerVervangen(Steen joker, int kolIndex, Steen steen) {
//
//		if (!(valideerVooraan(steen) && valideerAchteraan(steen) && valideerMidden(steen, kolIndex)))
//			return null;

//		if (valideerMidden(steen, kolIndex))
//		{
//			getStenen().remove(joker);
//			getStenen().add(kolIndex, steen);
//		}
//		else {
		if(jokerVervangenValidatie(kolIndex, joker, steen))
		{
			getStenen().remove(joker);
			getStenen().add(kolIndex, steen);
		}
		return joker;
	}
	
	private boolean jokerVervangenValidatie(int kolIndex, Steen joker, Steen steen) {
		int steenGetal = steen.getGetal();
		
		//voorraan controlerne
		if(kolIndex == 0) {
			return controleJoker(steen.getKleur(), steenGetal + 1, steenGetal + 2, 1, 2);
		}
		
		//achteraan conteolerne
		int size = getStenen().size();
		
		if(kolIndex == size-1) {
			return controleJoker(steen.getKleur(), steenGetal - 1, steenGetal - 2, size - 2, size - 3);
		}
		
		//midden controleren
		return controleJoker(steen.getKleur(), steenGetal - 1, steenGetal + 1, kolIndex -1, kolIndex + 1);
	}
	
	
	private boolean controleJoker(Kleur kleur, int steenGetal1, int steenGetal2, int index1, int index2) {
		if(steenGetal1 == getStenen().get(index1).getGetal() || steenGetal2 == getStenen().get(index2).getGetal())
		{
			if(kleur.equals(getStenen().get(index1).getKleur()) || kleur.equals(getStenen().get(index2).getKleur()))
				return true;
		}
		return false;
	}

}

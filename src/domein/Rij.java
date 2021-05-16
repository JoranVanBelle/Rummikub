package domein;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Rij extends VerzamelingSteen{ //zelfde getal, verschillend kleur
	private static final int MAX_LENGTE = 4;
	
	public Rij(List<Steen> stenen) {
		super(stenen);
	}

	@Override
	public boolean steenAanleggen(Steen steen) {
		if(valideerAanleggen(steen)) {
			getStenen().add(steen);
			return true;
		}
		return false;
	}

	public boolean valideerAanleggen(Steen steen) { //samenvoegen met valideerJoker => (Steen steen, boolean metJoker) als parameters !!!
		List<Steen> stenen = getStenen();	
		
		if(stenen.size() > MAX_LENGTE)
			return false;
		
		if(steen.getKleur() == Kleur.JOKER)
			return true;
	
		for(Steen s : stenen) {
			if(s.getGetal() != steen.getGetal() || s.getKleur() == steen.getKleur())
				return false;
		}
		return true;
	}
	
	public static boolean isRij(List<Steen> rij) {
		int index1 = 0;
		int index2 = 1;

		try {
			for (; index1 < rij.size(); index1++) {
				for (; index2 <= rij.size(); index2++) {
					if (rij.get(index1).getKleur() == rij.get(index2).getKleur()
							|| rij.get(index1).getGetal() != rij.get(index2).getGetal())
						if (rij.get(index1).getKleur() != Kleur.JOKER && rij.get(index2).getKleur() != Kleur.JOKER)
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
		
		if(jokerVervangenValidatie(kolIndex, joker, steen)) {
			getStenen().remove(joker);
			getStenen().add(kolIndex, steen);
		}
		return joker;
	}

	private boolean jokerVervangenValidatie(int kolIndex, Steen joker, Steen steen) {
		
		//vooraan
		if(kolIndex == 0) {
			return controleJoker(steen, 1, 2);
		}
		
		//achteraan conteolerne
		int size = getStenen().size();
		if(kolIndex == size-1) {
			return controleJoker(steen, size - 2, size - 3);
		}
		
		//midden controleren
		return controleJoker(steen, kolIndex - 1, kolIndex + 1);
		
	}
	
	private boolean controleJoker(Steen steen, int index1, int index2) {
		int steenGetal = steen.getGetal();
		
		if(steenGetal  == getStenen().get(index1).getGetal() || steenGetal  == getStenen().get(index2).getGetal())
		{
			if((!steen.getKleur().equals(getStenen().get(index1).getKleur()) && !getStenen().get(index1).getKleur().equals(Kleur.JOKER)) || 
					!steen.getKleur().equals(getStenen().get(index2).getKleur()) && !getStenen().get(index2).getKleur().equals(Kleur.JOKER))
				return true;
		}
		return false;
	}

}

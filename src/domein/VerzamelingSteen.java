package domein;

import java.util.ArrayList;
import java.util.List;

public abstract class VerzamelingSteen {

	private List<Steen> stenen = new ArrayList<>(); // serie of rij

	public VerzamelingSteen(List<Steen> stenen) {
		this.stenen = new ArrayList<>(stenen);
	}

	public List<Steen> getStenen() {
		return stenen;
	}
	
	public String toString() {
		return stenen.toString();
	}

	public void setStenen(List<Steen> stenen) {
		this.stenen = new ArrayList<>(stenen);
	}

	public abstract boolean steenAanleggen(Steen steen);

	public boolean bevatJoker(List<Steen> stenen) {
		for (Steen steen : stenen) {
			if (steen.getKleur().equals(Kleur.JOKER))
				return true;
		}
		return false;
	}

	public boolean isJoker(int index) {
		return stenen.get(index).getKleur() == Kleur.JOKER;
	}

	public void nieuweStenen(List<Steen> stenen) {
		setStenen(stenen);
	}

	public Steen geefSteen(int index) {
		return stenen.get(index);
	}

	public int getSize() {
		return stenen.size();
	}

	public abstract Steen jokerVervangen(Steen joker, int kolIndex, Steen steen);

}

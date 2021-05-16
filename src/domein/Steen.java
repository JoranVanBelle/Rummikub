package domein;

public class Steen implements SteenDisplay
{
	private Kleur kleur; //moet vervangen worden door enum
	private int getal;
	
	//constructor Public weg => zo kan de gui hier met werken !!!
	public Steen(Kleur kleur, int getal) {
		setKleur(kleur);
		setGetal(getal);
	}
	
	//getters en setters
	public Kleur getKleur() {
		return kleur;
	}
	private void setKleur(Kleur kleur2) {
		this.kleur = kleur2;
	}
	
	public int getGetal() {
		
		return getal;
	}
	private void setGetal(int getal) {
		this.getal = getal;
	}

	public String toString() {
		return (getal + "" + kleur);
	}
}

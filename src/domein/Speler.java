package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Speler implements SpelerDisplay
{
	//attr
	private String gebruikersnaam;
	private String wachtwoord;
	private List<Steen> stenenInBezit = new ArrayList<>();
	private int score;
	private boolean eersteZet = true;
	
	//BACKUP
	private List<Steen> persoonlijkBezitBackup = new ArrayList<>();
	
	//constructors
	public Speler(String gebruikersnaam, String wachtwoord) {
		setGebruikersnaam(gebruikersnaam);
		setWachtwoord(wachtwoord);
	}

	//getters & setters
	public void setPersoonlijkBezitBackup() {
		persoonlijkBezitBackup.removeAll(persoonlijkBezitBackup);
		persoonlijkBezitBackup = new ArrayList<>(stenenInBezit);
	}
	
	public String getGebruikersnaam() 
	{
		return gebruikersnaam;
	}
	private void setGebruikersnaam(String gebruikersnaam) 
	{
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getWachtwoord() 
	{
		return wachtwoord;
	}

	private void setWachtwoord(String wachtwoord) 
	{
		this.wachtwoord = wachtwoord;
	}
	
	public void setEersteZet() {
		this.eersteZet = false;
	}
	
	public void resetWachtwoord() {
		setWachtwoord("");
	}
	
	public boolean isCorrectWachtwoord(String wachtwoord) {
		return this.wachtwoord.equals(wachtwoord);
	}


	//START ITERATIE 2
	//================================================================================================================================================================
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Steen> getStenenInBezit() {
		return stenenInBezit;
	}

	public void setStenenInBezit(List<Steen> stenen) {
		stenenInBezit.addAll(stenen);
	}
	
	public int somStenenInBezit() {
		if (!stenenInBezit.isEmpty()) {
			int puntenSpeler = 0;
			for (Steen steen : stenenInBezit) {
				puntenSpeler += steen.getGetal();
			}
			score = puntenSpeler * -1;
			setScore(score);
		}
		return score;
	}
	
	public boolean bepaalWinnaar() {
		if(stenenInBezit.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	
	public void reset() {
		stenenInBezit.removeAll(getStenenInBezit());
		stenenInBezit = new ArrayList<>(persoonlijkBezitBackup);
	}

	public boolean isEersteZet() {
		return eersteZet;
	}

	@Override
	public String toString() {
		return String.format("Score van %s is %d%n", gebruikersnaam, score);
	}
	
	

}

package domein;

import java.sql.Connection;
import java.util.List;

import exceptions.FoutiefInloggegevensException;
import persistentie.SpelerMapper;

public class SpelerRepository {
	
	private SpelerMapper db = new SpelerMapper();
	
	public Speler meldAan(String gebruikersnaam, String wachtwoord) throws IllegalArgumentException, FoutiefInloggegevensException
	{
			Speler speler = db.meldAan(gebruikersnaam);
			if(speler != null && speler.isCorrectWachtwoord(wachtwoord))
				return speler;
			
			throw new FoutiefInloggegevensException();
		
	}
	
	
	//START ITERATIE 2
	//================================================================================================================================================================

}

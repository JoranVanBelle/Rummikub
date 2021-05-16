package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;

public class SpelerMapper {


	private static final String SELECTSPELER = "SELECT * from ID344104_g8.Speler WHERE gebruikersnaam = ?";
	
	public Speler meldAan(String gebruikersnaam) {
		Speler speler = null;
		
		try {
			Connection con = DriverManager.getConnection(DatabaseHandler.DB_URL, DatabaseHandler.USER, DatabaseHandler.PASSWORD);
			PreparedStatement query = con.prepareStatement(SELECTSPELER);
			query.setString(1, gebruikersnaam);
				try (ResultSet rs = query.executeQuery()){
					if(rs.next()) {
						String wachtwoord = rs.getString("wachtwoord");
						
						speler = new Speler(gebruikersnaam, wachtwoord);
					}
						
				}
			
		} catch(SQLException e){
			System.err.println(e.getMessage());
		}
		
		return speler;
	}

}

package ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Spel;
import domein.Speler;
import exceptions.FoutiefAantalSpelers;
import exceptions.FoutiefInloggegevensException;
import exceptions.FoutieveKeuzeException;
import exceptions.SpelerReedsAanwezigException;

public class RummikubApplicatie {
	
	//Jaap enz

	private DomeinController dc;

	public static void main(String[] args) {

		DomeinController dc = new DomeinController();
		new RummikubApplicatie(dc);

	}
	
	public RummikubApplicatie(DomeinController dc) {

		this.dc = dc;

		this.aantalSpelersKiezen();

		this.spelersWeergeven();

		this.keuzemogelijkheden();
		
		// START ITERATIE 2
		//=========================================
		this.geefSpelerAanDeBeurt();
		
		// START ITERATIE 3
		//=========================================
		
		
		
	}

	private void aantalSpelersKiezen() {
		boolean opnieuw = true;

		String aantalSpelers = ""; 

		int aantalSpelersG;

		Scanner s = new Scanner(System.in);

		do {
			System.out.print("Met hoeveel wil je het spel spelen? Kies tussen de 2 tem 4: "); // vragen aan dc
			aantalSpelers = s.nextLine();

			try {
				aantalSpelersG = Integer.parseInt(aantalSpelers);  
				dc.bepaalAantalSpelers(aantalSpelersG);
				opnieuw = false;
			} catch (NumberFormatException e) {
				System.err.println(); // aan dc vragen
			} catch (FoutiefAantalSpelers e) {
				System.err.println(e.getMessage() + "test");
			} 
			catch (Exception e) {
				System.err.println("Gelieve u bij de programmeurs te wenden!");
			}

		} while (opnieuw);
	}

//	public void aanmelden() {
//
//		boolean opnieuw = true;
//
//		String gebruikersnaam = "";
//		String wachtwoord = "";
//
//		Scanner s = new Scanner(System.in);
//
//		for (int i = 0; i < dc.getAantalSpelers(); i++) { // getAantalSpelers gaat naar Spel
//			opnieuw = true;
//			do {
//				try {
//
//					System.out.printf("Wat is de gebruikersnaam van de %d%s speler: ", i + 1, i == 1 ? "de" : "ste");
//					gebruikersnaam = s.nextLine();
//					System.out.printf("Wat is het wachtwoord van de %d%s speler: ", i + 1, i == 1 ? "de" : "ste");
//					wachtwoord = s.nextLine();
//					dc.meldAan(gebruikersnaam, wachtwoord);
//					opnieuw = false;
//				} catch(FoutiefInloggegevensException e) {
//					System.err.println(e.getMessage());
//				} catch(SpelerReedsAanwezigException e){
//					System.err.println(e.getMessage());
//				}
//				catch (Exception e) {
//					System.err.println(e.getMessage());
//				}
//			} while (opnieuw);
//		}
//	}

	private void spelersWeergeven() {

		List<String> gebruikersnamen = dc.geefGebruikersnamen();
		System.out.println("\nVolgende spelers zijn aangemeld: ");
		for (String st : gebruikersnamen) {
			System.out.println("\t" + st);
		}
	}

	private void keuzemogelijkheden() { 

		boolean opnieuw = true;

		List<String> keuzemogelijkheden = dc.geefKeuzemogelijkheid();
		Scanner s = new Scanner(System.in);

		String keuze = "";

		int keuzeInt;
		int teller = 0;

		System.out.println("\nU kunt kiezen uit volgende opties: ");

		for (String keuzemogelijkheid : keuzemogelijkheden) {
			System.out.printf("\t%d) %s%n", teller + 1, keuzemogelijkheid);
			teller++;
		}
		opnieuw = true;
		do {
			try {
				System.out.print("\nMaak uw keuze: ");
				keuze = s.nextLine();
				keuzeInt = Integer.parseInt(keuze);
				if (keuzeInt < 1 || keuzeInt > keuzemogelijkheden.size()) {
					throw new FoutieveKeuzeException();
				}
				System.out.println("U koos voor: " + keuzemogelijkheden.get(keuzeInt - 1));
				if(keuzeInt  == 1) {
					dc.startNieuwSpel(); //VERWIJST NAAR START ITERATIE 2
					mogelijkeActies();
		        }

				opnieuw = false;
			} catch (FoutieveKeuzeException e) {
				System.err.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.err.println("Gelieve een bestaande keuze te maken"); 
			} catch (Exception e) {
				System.err.println("Gelieve een bestaande keuze te maken of u te wenden tot de programmeurs" + e.getMessage());
			}
		} while (opnieuw);
		
		
	}
	
	
	//START ITERATIE 2
	//=======================================================================================================================================================================
	
	public void geefSpelerAanDeBeurt() {
		System.out.println("\nSpeler aan beurt:");
		System.out.println(dc.geefSpelerAanDeBeurt());
	}
		
	//START ITERATIE 3
	//=======================================================================================================================================================================
		

	private void mogelijkeActies() { 

		boolean opnieuw = true;

		List<String> mogelijkeActies = dc.toonMogelijkeActies();
		Scanner s = new Scanner(System.in);

		String keuze = "";

		int keuzeInt;
		int teller = 0;

		System.out.println("\nU kunt kiezen uit volgende opties: ");

		for (String keuzemogelijkheid : mogelijkeActies) {
			System.out.printf("\t%d) %s%n", teller + 1, keuzemogelijkheid);
			teller++;
		}
		opnieuw = true;
		do {
			try {
				System.out.print("\nMaak uw keuze: ");
				keuze = s.nextLine();
				keuzeInt = Integer.parseInt(keuze);
				if (keuzeInt < 1 || keuzeInt > mogelijkeActies.size()) {
					throw new FoutieveKeuzeException();
				}
				System.out.println("U koos voor: " + mogelijkeActies.get(keuzeInt - 1));
				
//				dc.spelerOnderneemtActie(keuzeInt); 
		        

				opnieuw = false;
			} catch (FoutieveKeuzeException e) {
				System.err.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.err.println("Gelieve een bestaande keuze te maken"); 
			} catch (Exception e) {
				System.err.println("Gelieve een bestaande keuze te maken of u te wenden tot de programmeurs" + e.getMessage());
			}
		} while (opnieuw);
		
		
	}

	
	
	
          
}

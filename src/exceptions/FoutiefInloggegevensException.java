package exceptions;

public class FoutiefInloggegevensException extends Exception {

	public FoutiefInloggegevensException() {
		super("De combinatie gebruikersnaam/wachtwoord is niet correct!");
	}

	public FoutiefInloggegevensException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.getMessage();
	}

}

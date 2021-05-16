package exceptions;

public class FoutieveKeuzeException extends Exception {

	public FoutieveKeuzeException() {
		super("Gelieve te kiezen uit de bestaande opties!");
	}

	public FoutieveKeuzeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.getMessage();
	}

}

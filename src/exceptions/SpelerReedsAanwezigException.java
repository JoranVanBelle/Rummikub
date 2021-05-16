package exceptions;

public class SpelerReedsAanwezigException extends Exception {

	public SpelerReedsAanwezigException() {
		super("Deze speler is al reeds aanwezig!");
	}

	public SpelerReedsAanwezigException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.getMessage();
	}

}

package exceptions;

public class GeenSteenGeselecteerd extends NullPointerException{
	
	public GeenSteenGeselecteerd() {
		super("Geen steen geselecteerd!");
	}
	
	public GeenSteenGeselecteerd(String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return super.getMessage();
	}
	
}

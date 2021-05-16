package exceptions;

public class FoutiefAantalSpelers extends Exception {

	public FoutiefAantalSpelers() 
	{
		super("Ongeldig aantal spelers!");
	}

	public FoutiefAantalSpelers(String message) 
	{
		super(message);
	}

	@Override
	public String toString() {
		return super.getMessage();
	}

}

package exceptions;

@SuppressWarnings("serial")
public class ExceptionTaille extends Exception{

	public ExceptionTaille(String s) {
		super("Probleme de taille: "+s+"\n\n");
	}
	
	@Override
	public String toString() {
		return this.getMessage();
	}

}

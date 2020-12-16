package packet;

@SuppressWarnings("serial")
public class ExceptionTaille extends Exception{

	public ExceptionTaille(String s) {
		super("Probleme de taille: "+s);
	}
	
	@Override
	public String toString() {
		return this.getMessage();
	}

}

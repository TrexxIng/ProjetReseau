package exceptions;

@SuppressWarnings("serial")
public class ExceptionFormat extends Exception{

	public ExceptionFormat(String s, int num) {
		super("Erreur dans le fichier (ligne "+num+"): "+s+"\n");
	}
	
	@Override
	public String toString() {
		return this.getMessage();
	}

}

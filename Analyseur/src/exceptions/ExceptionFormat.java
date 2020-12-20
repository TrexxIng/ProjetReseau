package exceptions;

@SuppressWarnings("serial")
public class ExceptionFormat extends Exception{

	public ExceptionFormat(String s, String filename, int num) {
		super("Erreur dans le fichier '"+filename+"' (ligne "+num+"):\n\t "+s+"\n\n");
	}
	
	@Override
	public String toString() {
		return this.getMessage();
	}

}

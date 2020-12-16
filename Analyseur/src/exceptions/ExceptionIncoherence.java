package exceptions;

@SuppressWarnings("serial")
public class ExceptionIncoherence extends Exception{

	public ExceptionIncoherence(String s) {
		super("Problème d'incohérence dans les données: "+s+"\n");
	}
	
	@Override
	public String toString() {
		return this.getMessage();
	}

}

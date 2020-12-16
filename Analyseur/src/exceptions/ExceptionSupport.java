package exceptions;

public class ExceptionSupport extends Exception{

	public ExceptionSupport(String s) {
		super("Non supporté par l'analyseur: "+s+"\n");
	}
	
	@Override
	public String toString() {
		return this.getMessage();
	}

}

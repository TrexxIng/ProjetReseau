package exceptions;

@SuppressWarnings("serial")
public class ExceptionSupport extends Exception{

	public ExceptionSupport(String s) {
		super("Non supporté par l'analyseur:\n\t "+s+"\n\n");
	}
	
	@Override
	public String toString() {
		return this.getMessage();
	}

}

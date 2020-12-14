package champs;

public class Vide implements IChamps {

	private String message;
	
	public Vide(String message) {
		this.message = message;
	}

	@Override
	public boolean checkSize() {
		return true;
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String formatDisplay(int tab) {
		String s ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				s += "\t";
			}
		}
		return s+this.toString();
	}
	

}

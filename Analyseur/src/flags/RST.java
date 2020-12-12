package flags;

import java.util.List;

public class RST implements IFlags {
	
	private int value;

	public RST(List<String> valbits) {
		this.value =  Integer.parseInt(valbits.get(13));
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
	
	@Override
	public String toString() {
		String s = ".... .... ."+value+".. = RST: ";
		if(value == 1)
			return s+"reinitialisation de la connexion";
		return s+"pas de reinitialisation de la connexion";
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public String getType() {
		return "RST";
	}
}

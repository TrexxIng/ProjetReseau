package flags;

import java.util.List;

public class PSH implements IFlags {
	
	private int value;

	public PSH(List<String> valbits) {
		this.value =  Integer.parseInt(valbits.get(12));
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
		String s = ".... .... "+value+"... = PSH: ";
		if(value == 1)
			return s+"données à lire d'urgence par l'application";
		return s+"pas de données à lire d'urgence par l'application";
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public String getType() {
		return "PSH";
	}

}

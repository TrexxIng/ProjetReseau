package flags;

import java.util.List;

public class SYN implements IFlags {

	private int value;
	
	public SYN(List<String> valbits) {
		this.value =  Integer.parseInt(valbits.get(14));
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
		String s = ".... .... .."+value+". = SYN: ";
		if(value == 1)
			return s+"ouverture de connexion";
		return s+"pas d'ouverture de connexion";
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public String getType() {
		return "SYN";
	}

}

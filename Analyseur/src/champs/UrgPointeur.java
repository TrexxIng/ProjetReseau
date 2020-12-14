package champs;

import java.util.List;

public class UrgPointeur implements IChamps {
	
	private String valHex;

	public UrgPointeur(List<String> valHex) {
		this.valHex = valHex.get(0)+valHex.get(1);
	}


	@Override
	public boolean checkSize() {
		if(valHex.length() == 4) return true;
		return false;
	}

	@Override
	public String toString() {
		return "Pointeur d'urgence (position): 0x"+valHex+" ("+Integer.parseInt(valHex,16)+")";
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

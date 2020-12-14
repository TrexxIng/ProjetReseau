package champs;

import java.util.List;

public class Identification implements IChamps{
	
	private List<String> valHex;
	private int valDec;
	
	public Identification(List<String> valHex) {
		this.valHex = valHex;
		this.valDec = Integer.parseInt( valHex.get(0)+valHex.get(1),16);
	}



	@Override
	public boolean checkSize() {
		if(valHex.size() == 2) return true;
		return false;
	}

	@Override 
	public String toString() {
		return "Identification: 0x"+valHex.get(0)+valHex.get(1)+" ("+valDec+")";
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

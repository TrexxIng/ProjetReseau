package champs.nbQuestRep;

import java.util.List;

import champs.IChamps;

public class NbAdditional implements IChamps {

	private List<String> valHex;
	private int value;
	
	public NbAdditional(List<String> valHex) {
		this.valHex = valHex;
		this.value = Integer.parseInt(valHex.get(0)+valHex.get(1), 16);
	}
	
	@Override
	public String toString() {
		return "Nombre de additional RR: "+value+" (0x"+valHex.get(0)+valHex.get(1)+")";
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
	
	public int getNb() {
		return value;
	}

}

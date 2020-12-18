package champs.options;

import java.util.List;

import champs.IChamps;

public class PointeurOption implements IChamps {
	
	private List<String> valHex;
	private int value;
	
	public PointeurOption(List<String> valHex) {
		this.valHex = valHex;
		this.value = Integer.parseInt(valHex.get(0),16);
	}

	
	@Override
	public String toString() {
		return "Pointeur: "+value+" (0x"+valHex.get(0)+")";
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

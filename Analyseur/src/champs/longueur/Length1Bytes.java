package champs.longueur;

import java.util.List;

import champs.IChamps;

public class Length1Bytes implements IChamps {

	private List<String> valHex;
	private int longueur;
	private String type;
	
	public Length1Bytes(List<String> valHex, String type) {
		this.type = type;
		this.valHex = valHex;
		this.longueur = Integer.parseInt(valHex.get(0), 16);
	}

	@Override
	public boolean checkSize() {
		if(valHex.size() == 1) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Longueur "+type+": "+longueur+" octets (0x"+valHex.get(0)+")";
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
	
	public int getLongueur() {
		return longueur;
	}

}

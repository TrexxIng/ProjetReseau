package champs.longueur;

import java.util.List;

import champs.IChamps;

public class LengthUDP implements IChamps{
	
	private List<String> valHex;
	private int longueur;
	
	public LengthUDP(List<String> valHex) {
		this.valHex = valHex;
		this.longueur = Integer.parseInt(
				(valHex.get(0)+valHex.get(1)), 16);
	}

	@Override
	public boolean checkSize() {
		if(valHex.size() == 2) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Longueur UDP: "+longueur+" octets (0x"+valHex.get(0)+valHex.get(1)+")";
	}
	
	public int getTailleUDP() {
		return longueur;
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

package champs.longueur;

import java.util.List;

import champs.IChamps;

public class LengthQuartet implements IChamps {
	private int longueur;
	private String quartet;
	private String type;
	
	public LengthQuartet(List<String> valHex, String type) {
		this.type = type;
		char val;
		if(type == "IP")
			val = valHex.get(0).charAt(1);
		else 
			val = valHex.get(0).charAt(0);
		this.quartet = ""+val;
		
			
		this.longueur = Integer.parseInt(quartet,16) * 4;
		
	}

	@Override
	public boolean checkSize() {
		if(quartet.length() == 1) return true;
		return false;
	}

	public int getTailleIP() {
		return longueur;
	}
	
	@Override
	public String toString() {
		return "Longueur de l'entête "+type+": "+longueur+" octets ("+Integer.parseInt(quartet,16)+")";
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

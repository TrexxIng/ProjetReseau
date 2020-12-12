package offset;

import java.util.List;

public class HeadLengthQuartet implements IOffset {
	private int longueur;
	private String quartet;
	private String type;
	
	public HeadLengthQuartet(List<String> valHex, String type) {
		char val = valHex.get(0).charAt(1);
		this.quartet = ""+val;
		this.longueur = Integer.parseInt(quartet,16) * 4;
		this.type = type;
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
		return "Longueur de l'entête "+type+": "+longueur+" octets ("+quartet+")";
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

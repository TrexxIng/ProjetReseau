package champs;

import java.util.List;

public class Windows implements IChamps {
	
	private String valHex;
	
	public Windows(List<String> valHex) {
		this.valHex = valHex.get(0)+valHex.get(1);
	}

	@Override
	public boolean checkSize() {
		if(valHex.length() == 4) return true;
		return false;
	}

	@Override
	public String toString() {
		return "Windows: 0x"+valHex+" ("+Integer.parseInt(valHex,16)+")";
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

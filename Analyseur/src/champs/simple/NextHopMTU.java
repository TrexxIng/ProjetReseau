package champs.simple;

import java.util.List;

import champs.IChamps;

public class NextHopMTU implements IChamps {

	private List<String> valHex;
	private String value ="";
	
	public NextHopMTU(List<String> valHex) {
		this.valHex = valHex;
		this.value = valHex.get(0)+valHex.get(1);
		
	}

	@Override
	public boolean checkSize() {
		return valHex.size() == 2;
	}
	
	@Override
	public String toString() {
		return "Next hop MTU: 0x"+value;
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

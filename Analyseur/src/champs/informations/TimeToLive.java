package champs.informations;

import java.util.List;

import champs.IChamps;

public class TimeToLive implements IChamps {
	private List<String> valHex;
	
	public TimeToLive(List<String> valHex) {
		this.valHex = valHex;	
	}

	
	@Override
	public String toString() {
		return "Time To Live (TTL): "+Integer.parseInt(valHex.get(0),16);
		
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

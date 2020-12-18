package champs.adresseEtPort;

import java.util.List;

import champs.IChamps;

public class Port implements IChamps{
	private boolean source;
	private List<String> valHex;
	private int valuePort;
	
	public Port(List<String> valHex, boolean source) {
		this.source = source;
		this.valHex = valHex;
		this.valuePort = Integer.parseInt((valHex.get(0)+valHex.get(1)),16);
	}

	
	@Override
	public String toString() {
		String s = "Destination";
		if(source)
			s = "Source";
		return  "Port "+s+": "+valuePort+" (0x"+valHex.get(0)+valHex.get(1)+")";

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
	
	public int getPortNumber() {
		return valuePort;
	}


}

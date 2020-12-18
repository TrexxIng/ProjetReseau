package champs.informations;

import java.util.List;

import champs.IChamps;

public class Operation implements IChamps {

	private List<String> valHex;
	private String type;
	
	public Operation(List<String> valHex) {
		this.valHex = valHex;
		setType();
	}


	@Override
	public String toString() {
		return "Operation: "+type+" (0x"+valHex.get(0)+valHex.get(1)+")";
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
	
	private void setType() {
		int s = Integer.parseInt(valHex.get(0)+valHex.get(1),16);
		if(s == 1)
			this.type = "requete ARP";
		else if(s == 2)
			this.type = "reponse ARP";
		else if(s == 3)
			this.type = "requete RARP";
		else if(s == 4)
			this.type = "reponse RARP";
		else this.type = "operation non listée";
	}

}

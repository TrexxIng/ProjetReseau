package champs.trameSuiv;

import java.util.List;

import champs.IChamps;

public class TypeEther implements IChamps {
	private List<String> valHex;
	private String type;
	
	
	public TypeEther(List<String> valHex) {
		this.valHex = valHex;
		this.setType();
	}


	
	private void setType() {
		int n0 = Integer.parseInt(valHex.get(0),16);
		int n1 = Integer.parseInt(valHex.get(1),16);
		if(n0 == 8 && n1 == 0) {
			type = "Datagramme IP";
		} else if(n0 == 8 && n1 == 6) {
			type = "ARP";
		}else if(n0 == 128 && n1 == 53) {
			type = "RARP";
		}
		else {
			type = "inconnu";;
		}
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "Type Ethernet: 0x"+valHex.get(0)+valHex.get(1)+" ("+type+")";
		
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

package champs.trameSuiv;

import java.util.List;

import champs.IChamps;

public class TypeICMP implements IChamps {
	private int value;
	private String type = "";
	
	
	public TypeICMP(List<String> valHex) {
		this.value = Integer.parseInt(valHex.get(0),16);
		this.setType(this.value); 
	}

	
	private void setType(int i) {
		if(i == 1) {
			this.type = "Echo reply";
		}
		else if(i == 3) {
			this.type = "Destination Unreachable";
		}
		else if(i == 4) {
			this.type = "Source Quench";
		}
		else if(i == 5) {
			this.type = "Redirect";
		}
		else if(i == 8) {
			this.type = "Echo request";
		}
		else if(i == 11) {
			this.type = "Time Exceeded";
		}
		else if(i == 12) {
			this.type = "Parameter Problem";
		}
		else if(i == 13) {
			this.type = "Timestamp";
		}
		else if(i == 14) {
			this.type = "Timestamp Reply";
		}
		else if(i == 15) {
			this.type = "Information Request";
		}
		else if(i == 16) {
			this.type = "Information Reply";
		}
		else if(i == 17) {
			this.type = "Address Mask Request";
		}
		else if(i == 18) {
			this.type = "Address Mask Reply";
		}
		else {
			this.type = "type de message non listé";
		}
		
	}
	
	@Override
	public String toString() {
		return "Type: "+type+" ("+value+")";
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
	
	public String getType(){
		return type;
	}

}

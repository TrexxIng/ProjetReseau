package champs.options;

import java.util.List;

import champs.IChamps;

public class TypeOptions implements IChamps{
	private int value;
	private String type = "";
	private String protocol;
	
	public TypeOptions(List<String> valHex, String protocol) {
		this.protocol = protocol;
		this.value = Integer.parseInt(valHex.get(0),16);
		this.setType(this.value); 
	}

	
	private void setType(int i) {
		if(i == 0) {
			this.type = "Fin d'options EOOL";
		}
		else if(i == 1) {
			this.type = "Pas d’opération";
		}
		else if(protocol == "IP") {
			if(i == 7) {
				this.type = "Enregistrement de route";
			}
			else if(i == 68) {
				this.type = "Enregistrement d’instant";
			}
			else if(i == 130) {
				this.type = "Sécurité";
			}
			else if(i == 131) {
				this.type = "Routage approximatif";
			}
			else if(i == 136) {
				this.type = "Identificateur de stream";
			}
			else if(i == 137) {
				this.type = "Routage impératif";
			}
			else {
				this.type = "option non listée";
			}
		}
		else {
			if(i == 4) {
				this.type = "Maximum Segment Size";
			}
			else if(i == 3) {
				this.type = "Window scale factor";
			}
			else if(i == 6) {
				this.type = "Echo";
			}
			else if(i == 7) {
				this.type = "Echo reply";
			}
			else if(i == 8) {
				this.type = "Timestamp";
			}
			else if(i == 14) {
				this.type = "Alternate checksum request";
			}
			else if(i == 15) {
				this.type = "Alternate checksum data";
			}
			else if(i == 26) {
				this.type = "TCP Compression Filter";
			}			
			else if(i == 28) {
				this.type = "User Timeout";
			}			
			else {
				this.type = "option non listée";
			}
		}
		
	}
	
	public String getType() {
		return type;
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

}

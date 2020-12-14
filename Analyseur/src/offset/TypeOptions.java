package offset;

import java.util.List;

public class TypeOptions implements IOffset{

	private List<String> valHex;
	private int value;
	private String type = "";
	
	
	public TypeOptions(List<String> valHex) {
		this.valHex = valHex;
		this.value = Integer.parseInt(valHex.get(0),16);
		this.setType(this.value); 
	}

	@Override
	public boolean checkSize() {
		if(valHex.size() == 1) return true;
		return false;
	}
	
	private void setType(int i) {
		if(i == 0) {
			this.type = "Fin d'options EOOL";
		}
		else if(i == 1) {
			this.type = "Pas d’opération";
		}
		else if(i == 7) {
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

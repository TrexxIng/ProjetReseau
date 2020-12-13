package offset;

import java.util.List;

public class ProtocolLength implements IOffset {

	private List<String> valHex;
	private int longueur;
	
	public ProtocolLength(List<String> valHex) {
		this.valHex = valHex;
		this.longueur = Integer.parseInt(valHex.get(0), 16);
	}

	@Override
	public boolean checkSize() {
		if(valHex.size() == 1) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Longueur du Protocole: "+longueur+" octets (0x"+valHex.get(0)+")";
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

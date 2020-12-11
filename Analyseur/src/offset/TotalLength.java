package offset;

import java.util.List;

public class TotalLength implements IOffset {
	private List<String> valHex;
	private int longueur;
	
	public TotalLength(List<String> valHex) {
		this.valHex = valHex;
		this.longueur = Integer.parseInt( valHex.get(0)+valHex.get(1),16);
	}

	@Override
	public List<String> getOctets() {
		return valHex;
	}

	@Override
	public boolean checkSize() {
		if(valHex.size() == 2) return true;
		return false;
	}
	
	@Override 
	public String toString() {
		return "Longueur totale: 0x"+valHex.get(0)+valHex.get(1)+" ("+longueur+" octets)";
	}

}
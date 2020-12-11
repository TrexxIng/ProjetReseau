package offset;

import java.util.List;

public class Identification implements IOffset{
	
	private List<String> valHex;
	private int valDec;
	
	public Identification(List<String> valHex) {
		this.valHex = valHex;
		this.valDec = Integer.parseInt( valHex.get(0)+valHex.get(1),16);
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
		return "Identification: 0x"+valHex.get(0)+valHex.get(1)+" ("+valDec+")";
	}
	
}

package offset;

import java.util.List;

public class Flags implements IOffset {
	private List<String> valHex;
	
	public Flags(List<String> valHex) {
		this.valHex = valHex;
	}



	@Override
	public boolean checkSize() {
		if(valHex.size() == 2) return true;
		return false;
	}
	
	@Override 
	public String toString() {
		return "Flags: 0x"+valHex.get(0)+valHex.get(1);
	}

}

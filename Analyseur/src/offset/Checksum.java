package offset;

import java.util.List;

public class Checksum implements IOffset {
	private String valHex;
	
	public Checksum(List<String> valHex) {
		this.valHex = valHex.get(0)+valHex.get(1);
	}

	@Override
	public boolean checkSize() {
		if(valHex.length() == 2) return true;
		return false;
	}
	
	public boolean compareCheckSum(String hex) {
		return hex == valHex; 
	}
	
	@Override
	public String toString() {
		return "Checksum: 0x"+valHex;
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

package offset;

import java.util.List;

public class Checksum implements IOffset {
	private String valHex;

	
	public Checksum(List<String> valHex) {
		this.valHex = valHex.get(0)+valHex.get(1);
	}

	@Override
	public boolean checkSize() {
		if(valHex.length() == 4) return true;
		return false;
	}
	
	public boolean compareCheckSum(String hex) {
		return hex == valHex; 
	}

}

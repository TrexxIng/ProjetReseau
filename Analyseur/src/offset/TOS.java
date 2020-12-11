package offset;

import java.util.List;

public class TOS implements IOffset {
	private List<String> valHex;

	public TOS(List<String> valHex) {
		this.valHex = valHex;
	}


	@Override
	public boolean checkSize() {
		if(valHex.size() == 1) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Type of Service: 0x"+valHex.get(0);
	}

}

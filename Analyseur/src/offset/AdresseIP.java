package offset;

import java.util.List;

public class AdresseIP implements IOffset {
	private boolean source;
	private List<String> valHex;
	
	public AdresseIP(List<String> valHex, boolean source) {
		this.source = source;
		this.valHex = valHex;
	}



	@Override
	public boolean checkSize() {

		return false;
	}

}

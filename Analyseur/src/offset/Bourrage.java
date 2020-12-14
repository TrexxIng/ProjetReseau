package offset;

import java.util.List;

public class Bourrage implements IOffset {

	private List<String> valHex;
	private String value ="";
	
	public Bourrage(List<String> valHex) {
		this.valHex = valHex;
		for(int i = 0; i<this.valHex.size();i++) {
			this.value += valHex.get(i);
		}
	}

	@Override
	public boolean checkSize() {
		return true;
	}
	
	@Override
	public String toString() {
		return "Bourrage: 0x"+value;
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

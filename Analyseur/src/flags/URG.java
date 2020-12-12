package flags;

import java.util.List;

public class URG implements IFlags {
	
	private List<String> valbits;
	private int value;

	public URG(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(6));
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
	
	@Override
	public String toString() {
		String s = ".... .."+value+". .... = Urgent (Pointeur): ";
		if(value == 1)
			return s+"oui";
		return s+"non";
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public String getType() {
		return "URG";
	}

}

package flags;

import java.util.List;

public class DF implements IFlags {
	
	
	private List<String> valbits;
	private int value;
	
	public DF(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(1));
	}
	
	@Override
	public String toString() {
		String s = "."+valbits.get(1)+".. .... .... .... = Don't Fragment (DF): ";
		if(value == 1)
			return s +"fragmentation interdit";
		else
			return s +"fragmentation autorisé";
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
	public int getValue() {
		return value;
	}
	
	@Override
	public String getType() {
		return "DF";
	}

}

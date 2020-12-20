package flags.flagsDNS;

import java.util.List;

import flags.IFlags;

public class AA implements IFlags {
	private List<String> valbits;
	private int value;
	
	public AA(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(5));
	}
	
	@Override
	public String toString() {
		String s = ".... ."+valbits.get(5)+".. .... .... = Authoritarive Answer: ";
		if(value == 1)
			return s +"oui";
		else
			return s +"non";
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
		if(value == 1)
			return "Aa";
		else
			return "non";
	}

}



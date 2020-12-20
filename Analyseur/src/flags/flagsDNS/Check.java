package flags.flagsDNS;

import java.util.List;

import flags.IFlags;

public class Check implements IFlags {

	private List<String> valbits;
	private int value;
	
	public Check(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(11));
	}
	
	@Override
	public String toString() {
		String s = ".... .... ..."+valbits.get(11)+" .... = données non authentifiées";
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
			return "oui";
		else
			return "non";
	}

}

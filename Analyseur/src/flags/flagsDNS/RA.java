package flags.flagsDNS;

import java.util.List;

import flags.IFlags;

public class RA implements IFlags {

	private List<String> valbits;
	private int value;
	
	public RA(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(8));
	}
	
	@Override
	public String toString() {
		String s = ".... .... "+valbits.get(8)+"... .... = RA: ";
		if(value == 1)
			return s +"récursion possible";
		else
			return s +"récursion impossible";
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

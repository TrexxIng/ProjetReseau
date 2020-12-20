package flags.flagsDNS;

import java.util.List;

import flags.IFlags;

public class RD implements IFlags {

	private List<String> valbits;
	private int value;
	
	public RD(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(7));
	}
	
	@Override
	public String toString() {
		String s = ".... ..."+valbits.get(7)+" .... .... = RD: ";
		if(value == 1)
			return s +"récursion désirée";
		else
			return s +"récursion non désirée";
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

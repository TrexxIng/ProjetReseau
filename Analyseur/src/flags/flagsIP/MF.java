package flags.flagsIP;

import java.util.List;

import flags.IFlags;

public class MF implements IFlags {
	
	private List<String> valbits;
	private int value;
		
	public MF(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(2));
	}
	
	@Override
	public String toString() {
		String s = ".."+valbits.get(2)+". .... .... .... = More Fragment (MF): ";
		if(value == 1)
			return s +"suivi d'un fragment";
		else
			return s +"non suivi d'un fragment";
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
		return "MF";
	}

}

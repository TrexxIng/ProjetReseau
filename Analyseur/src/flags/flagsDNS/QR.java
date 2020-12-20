package flags.flagsDNS;

import java.util.List;

import flags.IFlags;

public class QR implements IFlags {

	private List<String> valbits;
	private int value;
	
	public QR(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(0));
	}
	
	@Override
	public String toString() {
		String s = valbits.get(0)+"... .... .... .... = QR: ce message est une ";
		if(value == 1)
			return s +"réponse";
		else
			return s +"requete";
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
			return "reponse";
		else
			return "requete";
	}

}

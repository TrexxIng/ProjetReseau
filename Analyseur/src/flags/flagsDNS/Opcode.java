package flags.flagsDNS;

import java.util.List;

import flags.IFlags;

public class Opcode implements IFlags {

	private List<String> valbits;
	private int value;
	private String opcode;
	
	public Opcode(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(1)+valbits.get(2)+
				valbits.get(3)+valbits.get(4),2);
		this.opcode = getMessage();
	}
	
	@Override
	public String toString() {
		String s = "."+valbits.get(1)+valbits.get(2)+
				valbits.get(3)+" "+valbits.get(4)+"... .... .... = Opcode: ";

		return s + opcode +" ("+value+")";
	}
	
	private String getMessage() {
		if(value == 0)
			return "requête standard";
		if(value == 1)
			return "requête inverse";
		if(value == 2)
			return "status du serveur";
		if(value == 4)
			return "notification";
		if(value == 5)
			return "mise à jour";
		return "Réservé pour utilisation future";
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
		return opcode;
	}

}

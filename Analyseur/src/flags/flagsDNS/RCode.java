package flags.flagsDNS;

import java.util.List;

import flags.IFlags;

public class RCode implements IFlags {

	private List<String> valbits;
	private int value;
	private String code;
	
	public RCode(List<String> valbits) {
		this.valbits = valbits;
		this.value =  Integer.parseInt(valbits.get(11)+valbits.get(12)+
				valbits.get(13)+valbits.get(14),2);
		this.code = getMessage();
	}
	
	@Override
	public String toString() {
		String s = ".... .... .... "+valbits.get(11)+valbits.get(12)+
				valbits.get(13)+" "+valbits.get(14)+" = code de retour: ";

		return s + code +" ("+value+")";
	}
	
	private String getMessage() {
		if(value == 0)
			return "pas d'erreur";
		if(value == 1)
			return "erreur sur le format de la requete";
		if(value == 2)
			return "nom de domaine non trouve";
		if(value == 4)
			return " requete non supportee";
		if(value == 5)
			return " le serveur refuse de repondre";
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
		return code;
	}
}
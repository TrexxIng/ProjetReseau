package champs;

import java.util.List;

public class TraductionHTTP implements IChamps {
	private List<String> valHex;
	private String message;
	
	public TraductionHTTP(List<String> valHex) {
		this.valHex = valHex;		
		int n;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.valHex.size(); i++) {
			 n = Integer.valueOf(valHex.get(i), 16);
	         builder.append((char)n);	
		}
		message = builder.toString();
	}


	@Override
	public String toString() {
		return message;
	}

	@Override
	public String formatDisplay(int tab) {
		String s = "";
		String stab ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				stab += "\t";
			}
		}
		s = message.replace("\n", "\n"+stab);
		return "\n"+stab+s;

	}

}

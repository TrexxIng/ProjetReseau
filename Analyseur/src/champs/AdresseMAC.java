package champs;


import java.util.List;

public class AdresseMAC implements IChamps {
	private String source;
	private List<String> valHex;
	
	public AdresseMAC(List<String> valHex, String source) {
		this.source = source;
		this.valHex = valHex;
	}
	
	@Override
	public String toString() {
		String s = "adresse MAC "+source+": ";
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< valHex.size()-1; i++) {
			sb.append(valHex.get(i));
			sb.append(":");
		}
		sb.append(valHex.get(valHex.size()-1));
		s = s + sb.toString();
		
		return s;
	}



	@Override
	public boolean checkSize() {
		if(valHex.size() == 6) return true;
		return false;
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
	
	

}

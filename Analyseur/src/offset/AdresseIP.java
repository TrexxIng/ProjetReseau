package offset;

import java.util.List;

public class AdresseIP implements IOffset {
	private boolean source;
	private List<String> valHex;
	
	public AdresseIP(List<String> valHex, boolean source) {
		this.source = source;
		this.valHex = valHex;
	}



	@Override
	public boolean checkSize() {
		if(valHex.size() == 4) return true;
		return false;
	}
	
	@Override
	public String toString() {
		String s = "Destination";
		if(source)
			s = "Source";
		s = "adresse IP "+s+": ";
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< valHex.size()-1; i++) {
			sb.append(Integer.parseInt(valHex.get(i),16));
			sb.append(".");
		}
		sb.append(Integer.parseInt(valHex.get(valHex.size()-1),16));
		s = s + sb.toString();
		
		return s;
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

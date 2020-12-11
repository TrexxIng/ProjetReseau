package offset;


import java.util.List;

public class AdresseMAC implements IOffset {
	private boolean source;
	private List<String> valHex;
	
	public AdresseMAC(List<String> valHex, boolean source) {
		this.source = source;
		this.valHex = valHex;
	}
	
	@Override
	public String toString() {
		String s = "Destination";
		if(source)
			s = "Source";
		s = "adresse MAC "+s+": ";
		
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
	
	

}

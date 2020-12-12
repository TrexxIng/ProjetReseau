package offset;

import java.util.List;

public class Protocol implements IOffset {
	private List<String> valHex;
	private String protocol;
	
	public Protocol(List<String> valHex) {
		this.valHex = valHex;
		this.setProtocol();
	}

	@Override
	public boolean checkSize() {
		if(valHex.size() == 1) return true;
		return false;
	}
	
	
	@Override
	public String toString() {
		return "Protocole: 0x"+valHex.get(0)+" ("+protocol+")";
	}
	
	private void setProtocol() {
		int val = Integer.parseInt(valHex.get(0),16);
		if(val == 1) {
			protocol = "ICMP";
			return;
		} else if(val  == 6) {
			protocol = "TCP";
		} else if(val  == 17) {
			protocol = "UDP";
		}  else {
			protocol = "inconnu";;
		}
	}
	
	public String getProtocol() {
		return protocol;
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

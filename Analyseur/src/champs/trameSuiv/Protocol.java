package champs.trameSuiv;

import java.util.List;

import champs.IChamps;

public class Protocol implements IChamps {
	private List<String> valHex;
	private String protocol = "";
	private String type;
	
	public Protocol(List<String> valHex, String type) {
		this.valHex = valHex;
		this.type = type;
		if(type == "IP")
			this.setProtocolIP();
		if(type == "ARP")
			this.setProtocolARP();
			
	}

	
	
	@Override
	public String toString() {
		if(type == "IP")
			return "Protocole: 0x"+valHex.get(0)+" ("+protocol+")";
		return "Protocole: 0x"+valHex.get(0)+valHex.get(1)+" ("+protocol+")";
	}
	
	private void setProtocolIP() {
		int val = Integer.parseInt(valHex.get(0),16);
		if(val == 1) {
			protocol = "ICMP";
		} else if(val  == 6) {
			protocol = "TCP";
		} else if(val  == 17) {
			protocol = "UDP";
		}  else {
			protocol = "protocole non inconnu";;
		}
	}
	
	private void setProtocolARP() {
		if(Integer.parseInt(valHex.get(0)+valHex.get(1),16) == 2048) {
			protocol = "IPv4";
		} else {
			protocol = "protocole non listé";
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

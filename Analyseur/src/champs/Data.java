package champs;

import java.util.List;

public class Data implements IChamps {
	private List<String> valHex;
	private String data = "";
	
	public Data(List<String> valHex) {
		this.valHex = valHex;
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<valHex.size();i++) {
			sb.append(valHex.get(i));
		}
		this.data = sb.toString();
	}


	@Override
	public String toString() {
		return "Data ("+valHex.size()+" octets): 0x"+data;
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

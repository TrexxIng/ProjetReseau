package offset;

import java.util.List;

public class TypeEther implements IOffset {
	private List<String> valHex;
	private String type;
	
	
	public TypeEther(List<String> valHex) {
		this.valHex = valHex;
		this.setType();
	}



	@Override
	public boolean checkSize() {
		if(valHex.size() == 2) return true;
		return false;
	}
	
	private void setType() {
		int n0 = Integer.parseInt(valHex.get(0),16);
		int n1 = Integer.parseInt(valHex.get(1),16);
		if(n0 == 8 && n1 == 0) {
			type = "Datagramme IP";
		} else if(n0 == 8 && n1 == 6) {
			type = "ARP";
		} else {
			type = "inconnu";;
		}
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "Type Ethernet: 0x"+valHex.get(0)+valHex.get(1)+" ("+type+")";
		
	}
	
	

}

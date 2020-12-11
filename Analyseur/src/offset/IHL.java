package offset;

import java.util.ArrayList;
import java.util.List;

public class IHL implements IOffset {
	private int longueur;
	private String quartet;
	
	public IHL(List<String> valHex) {
		char val = valHex.get(0).charAt(1);
		this.quartet = ""+val;
		this.longueur = Integer.parseInt(quartet,16) * 4;	
	}

	@Override
	public boolean checkSize() {
		if(quartet.length() == 1) return true;
		return false;
	}

	@Override
	public List<String> getOctets() {
		List<String> quartList = new ArrayList<>();
		quartList.add(quartet);
		return quartList;
	}
	
	@Override
	public String toString() {
		return "Longueur de l'entête IP: "+longueur+" octets ("+quartet+")";
	}

	

}
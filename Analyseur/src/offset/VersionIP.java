package offset;

import java.util.ArrayList;
import java.util.List;

public class VersionIP implements IOffset {

	private int version;
	private String quartet;
	
	public VersionIP(List<String> valHex) {
		char val = valHex.get(0).charAt(1);
		this.quartet = ""+val;
		this.version = Integer.parseInt(quartet,16);
	}

	@Override
	public List<String> getOctets() {
		List<String> quartList = new ArrayList<>();
		quartList.add(quartet);
		return quartList;
	}

	@Override
	public boolean checkSize() {
		if(quartet.length() == 1) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Version: "+version;
	}

}

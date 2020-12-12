package offset;

import java.util.List;

public class Port implements IOffset{
	private boolean source;
	private List<String> valHex;
	private int valuePort;
	
	public Port(List<String> valHex, boolean source) {
		this.source = source;
		this.valHex = valHex;
		this.valuePort = Integer.parseInt((valHex.get(0)+valHex.get(1)),16);
	}

	@Override
	public boolean checkSize() {
		if(valHex.size() == 2) return true;
		return false;
	}
	
	@Override
	public String toString() {
		String s = "Destination";
		if(source)
			s = "Source";
		return  "Port "+s+": "+valuePort+" (0x"+valHex.get(0)+valHex.get(1)+")";

	}


}

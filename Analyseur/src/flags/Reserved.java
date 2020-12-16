package flags;

import java.util.List;

public class Reserved implements IFlags {

	private List<String> valbits;
	private String type;
	private int reserved;
	
	public Reserved(List<String> valbits, String type) {
		this.valbits = valbits;
		this.type = type;
		
		if(type == "IP") {
			reserved = Integer.parseInt(valbits.get(0),16);
		}else {
			StringBuilder sb = new StringBuilder();
			for(int i = 4; i<10;i++) {
				sb.append(valbits.get(i));
			}
			reserved = Integer.parseInt(sb.toString(),16);
		}
	}
	
	@Override
	public String toString() {
		if(type == "IP")
			return valbits.get(0)+"... .... .... .... = Reserved";
		
		StringBuilder sb = new StringBuilder();
		for(int i = 4; i<8;i++) {
			sb.append(valbits.get(i));
		}
		sb.append(" ");
		for(int i = 8; i<10;i++) {
			sb.append(valbits.get(i));
		}
		return sb.toString()+".. .... = Reserved";
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
	
	@Override
	public int getValue() {
		return reserved;
	}
	
	@Override
	public String getType() {
		return "Reserved";
	}

}

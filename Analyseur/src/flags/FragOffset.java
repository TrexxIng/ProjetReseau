package flags;

import java.util.List;

public class FragOffset implements IFlags {
	
	private List<String> valbits;
	public int value13bits;
	
	
	public FragOffset(List<String> valbits) {
		this.valbits = valbits;
		
		String val = "";
		for(int i = 3; i<valbits.size(); i++) {
			val += valbits.get(i);
		}
		this.value13bits = Integer.parseInt(val,2);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(valbits.get(3)+" ");
		for(int i = 0; i<4;i++) {
			sb.append(valbits.get(4+i));
		}
		sb.append(" ");
		for(int i = 0; i<4;i++) {
			sb.append(valbits.get(8+i));
		}
		sb.append(" ");
		for(int i = 0; i<4;i++) {
			sb.append(valbits.get(12+i));
		}
		
		return "..."+sb.toString()+" = Fragment Offset: "+value13bits;
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
		return value13bits;
	}
	
	@Override
	public String getType() {
		return "Fragment Offset";
	}
}

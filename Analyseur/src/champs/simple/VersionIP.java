package champs.simple;

import java.util.List;

import champs.IChamps;

public class VersionIP implements IChamps {

	private int version;
	private String quartet;
	
	public VersionIP(List<String> valHex) {
		char val = valHex.get(0).charAt(0);
		this.quartet = ""+val;
		this.version = Integer.parseInt(quartet,16);
	}


	
	@Override
	public String toString() {
		return "Version: "+version;
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

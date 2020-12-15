package segment;

import java.util.ArrayList;
import java.util.List;

import champs.IChamps;
import champs.TraductionHTTP;

public class HTTP implements ITrame {
	private List<String> listData;
	private TraductionHTTP http;
	
	public HTTP(List<String> listOctets) {
		this.listData = listOctets;
		this.http = new TraductionHTTP(listData);
		
	}

	@Override
	public boolean checkSize() {
		return true;
	}

	@Override
	public List<String> getData() {
		return new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return "Hypertext Transfer Protocol";
	}

	@Override
	public String formatDisplay(int tab) {
		String stab ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				stab += "\t";
			}
		}
		return stab+this.toString()+http.formatDisplay(tab+1);
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}

	@Override
	public int getSize() {
		return listData.size();
	}

}

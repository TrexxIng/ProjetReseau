package segment;

import java.util.ArrayList;
import java.util.List;

import champs.TraductionHTTP;
import packet.ExceptionTaille;

public class HTTP implements ITrame {
	private List<String> listData;
	private TraductionHTTP http;
	
	public HTTP(List<String> listOctets) throws ExceptionTaille {
		this.listData = listOctets;
		
		if(listData == null || listData.size() == 0) 
			throw new ExceptionTaille("réponse HTTP manquante");		
		
		this.http = new TraductionHTTP(listData);
		
		if(listData.size() < 26)
			throw new ExceptionTaille("réponse HTTP courte, vérifiez le message");
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

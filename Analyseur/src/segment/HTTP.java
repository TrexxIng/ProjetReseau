package segment;

import java.util.ArrayList;
import java.util.List;

import champs.TraductionHTTP;
import exceptions.ExceptionIncoherence;
import exceptions.ExceptionSupport;
import exceptions.ExceptionTaille;

public class HTTP implements ITrame {
	private List<String> listData;
	private TraductionHTTP http;
	private int sizeHTTP;
	
	public HTTP(List<String> listOctets) throws ExceptionTaille {
		this.listData = listOctets;
		this.sizeHTTP = listData.size();
		if(listData == null || listData.size() == 0) 
			throw new ExceptionTaille("réponse HTTP manquante");		
		
		this.http = new TraductionHTTP(listData);
		
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
		return sizeHTTP;
	}
	
	@Override
	public String nextSegment() {
		return "Rien";
	}


	@Override
	public void errorDetect() throws ExceptionSupport, ExceptionIncoherence {
		// pas d'erreur d'incoherence sévère ou de support detectable
	}


	@Override
	public String messageVerification() {
		// aucune erreur non importante ne peut etre determiné ici
		return "";
	}
	
	

}

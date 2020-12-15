package champs;

import java.util.ArrayList;
import java.util.List;

public class TraductionHTTP implements IChamps {
	private List<String> valHex;
	private List<String> phrases;
	
	public TraductionHTTP(List<String> valHex) {
		this.valHex = valHex;
		
		this.phrases = new ArrayList<>();
		int n;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < valHex.size(); i++) {
	         // Etape 1: regarde s'il y a saut de ligne
	    	  if(valHex.get(i).equals("0d")) {
	    		  if((i < valHex.size()-1 && valHex.get(i+1).equals("0a")) ||
	    				  (i == valHex.size() - 1)){
	    			// Etape 2.1: si oui, on stocke la phrase dans la liste
	    			  if(builder.length() > 0)
	    				  phrases.add(builder.toString());
	    			  builder = new StringBuilder();
	    			  i++;
	    		  }
	    	  } else {
	    		// Etape 2.2: sinon, on stocke la lettre dans phrase
	 	         n = Integer.valueOf(valHex.get(i), 16);
	 	         builder.append((char)n);
	    	  }  		  
		}
		phrases.add(builder.toString());
	}

	@Override
	public boolean checkSize() {
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder= new StringBuilder();
		for(int i = 0; i< phrases.size();i++) {
			builder.append(phrases.get(i));
			builder.append("\n");
		}
		return builder.toString();
	}

	@Override
	public String formatDisplay(int tab) {
		String s = "";
		String stab ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				stab += "\t";
			}
		}
		for(int i = 0; i<phrases.size(); i++) {
			s +="\n"+stab+phrases.get(i);
		}
		return s;

	}

}

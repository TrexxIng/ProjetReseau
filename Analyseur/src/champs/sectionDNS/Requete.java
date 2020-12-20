package champs.sectionDNS;

import java.util.List;

import champs.IChamps;

public class Requete implements IChamps{
	
	private List<String> valHex;
	private String message;
	private int value;
	private int classe;
	private int indexMessage;
	
	
	public Requete(List<String> valHex, int indexMessage) {
		
		this.valHex = valHex;
		this.indexMessage = indexMessage;
		
		int n;
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < indexMessage; i++) {
			if(Integer.parseInt(valHex.get(i),16) < 10) {
				builder.append('.');
			}
			else if(valHex.get(i).equals("2d")) {
				builder.append('-');
			}
			else {
				n = Integer.valueOf(valHex.get(i), 16);
				builder.append((char)n);
			}
		}
		message = builder.toString();
		
		
		this.value = Integer.parseInt(valHex.get(indexMessage+1)+
				valHex.get(indexMessage+2), 16);
		this.classe = Integer.parseInt(valHex.get(indexMessage+3)+
				valHex.get(indexMessage+4), 16);

	}
	
	@Override 
	public String toString() {
		return "Requete: "+message;
	}


	public String formatDisplay(int tab) {
		String s ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				s += "\t";
			}
		}
		return s+toString()+"\n"+
				s+"\t"+"Nom: "+message+"\n"+
				s+"\t"+"Type: "+value+"\n"+
				s+"\t"+"Classe: "+classe+" (0x"+valHex.get(indexMessage+3)+valHex.get(indexMessage+4)+")";
	}
	
	
	

}

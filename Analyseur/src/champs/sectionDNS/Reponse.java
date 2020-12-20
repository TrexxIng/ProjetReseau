package champs.sectionDNS;

import java.util.List;

import champs.IChamps;

public class Reponse implements IChamps {

	private List<String> valHex;
	private String nom;
	private int type;
	private int classe;
	private int ttl;
	private int rdLength;
	private String data ="";
	
	
	public Reponse(List<String> valHex) {
		this.valHex = valHex;
		this.nom = valHex.get(0)+valHex.get(1);
		this.type = Integer.parseInt(valHex.get(2)+valHex.get(3), 16);
		this.classe = Integer.parseInt(valHex.get(4)+valHex.get(5), 16);	
		this.ttl = Integer.parseInt(valHex.get(6)+valHex.get(7)+valHex.get(8)+valHex.get(9), 16);		
		this.rdLength = Integer.parseInt(valHex.get(10)+valHex.get(11), 16);		
		for(int i = 12; i< 12+rdLength; i++) {
			data += valHex.get(i);
		}
	}

	@Override 
	public String toString() {
		return "Réponse: \n";
	}


	public String formatDisplay(int tab) {
		String s ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				s += "\t";
			}
		}
		return s+toString()+
				s+"\t"+"Nom (Offset): "+nom+"\n"+
				s+"\t"+"Type: "+type+"\n"+
				s+"\t"+"Classe: "+classe+" (0x"+valHex.get(2)+valHex.get(3)+")"+"\n"+
				s+"\t"+"TTL: "+ttl+"\n"+
				s+"\t"+"Longueur: "+rdLength+"\n"+
				s+"\t"+"Data: 0x"+data;
				
	}
	

}

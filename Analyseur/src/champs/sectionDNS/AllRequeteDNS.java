package champs.sectionDNS;

import java.util.ArrayList;
import java.util.List;

import champs.IChamps;
import exceptions.ExceptionTaille;

public class AllRequeteDNS implements IChamps {

	private List<String> listData;
	private List<IChamps> champs;
	private int size = 0;
	
	public AllRequeteDNS(List<String> valHex,int nbrequete) {
		this.listData = valHex;
		this.champs = new ArrayList<>();
				
		List<String> list;
		for(int i = 0; i< nbrequete; i++) {

			/** verification de la taille pour la requete */
			if(!valHex.contains("00"))
				new ExceptionTaille("pas assez d'octets pour la requête DNS n°"+(i+1));
			int index = valHex.indexOf("00");
			if(valHex.size() < index+5)
				new ExceptionTaille("pas assez d'octets pour la requête DNS n°"+(i+1));
			
			/** construit la liste pour  la requete */
			list = new ArrayList<>();
			for(int j = 0; j< index+5; j++) {
				list.add(listData.get(0));
				listData.remove(0);
			}
			champs.add(new Requete(list,index));
			size += index+5;
		}
		
	}

	@Override 
	public String toString() {
		return "Requêtes: ";
	}
	
	@Override
	public String formatDisplay(int tab) {
		String stab ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				stab += "\t";
			}
		}
		String s = stab+this.toString();
		for(int i = 0; i<champs.size(); i++) {
			s +="\n"+champs.get(i).formatDisplay(tab+1);
		}
		return s;
	}
	
	public List<String> getData(){
		return listData;
	}
	
	public int getSize() {
		return size;
	}

}



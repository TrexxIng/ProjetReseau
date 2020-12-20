package champs.sectionDNS;

import java.util.ArrayList;
import java.util.List;

import champs.Data;
import champs.IChamps;
import exceptions.ExceptionTaille;

public class OtherDNS implements IChamps {

	private List<String> listData;
	private List<IChamps> champs;
	private String type;
	private int size;
	
	public OtherDNS(List<String> valHex,int nbrequete, String type) {
		this.type = type;
		this.listData = valHex;
		this.champs = new ArrayList<>();
				
		List<String> list;
		int taille;
		int i;
		
		if(type == "Reponse") {
			for(i = 0; i< nbrequete; i++) {			
				/** verification de la taille pour la requete */
				taille = Integer.parseInt(valHex.get(10)+valHex.get(11), 16) + 12;
				if(valHex.size() < taille)
					new ExceptionTaille("pas assez d'octets pour la réponse DNS n°"+(i+1));
				
				/** construit la liste pour  la requete */
				list = new ArrayList<>();
				for(int j = 0; j< taille; j++) {
					list.add(listData.get(0));
					listData.remove(0);
				}
				this.size += list.size();
				champs.add(new Reponse(list));
				
			}
		}
		else if(type == "AA") {
			/** authorative answer in construction */
		}
		else if(type == "Addition") {
			/**  additionnal answer in construction */
		}
		else{
			list = new ArrayList<>();
			this.size = listData.size();
			for(i = 0; i< listData.size(); i++) {
				list.add(listData.get(0));
				listData.remove(0);
			}
			champs.add(new Data(list));
			
			
		}
		
		
		
	}

	@Override 
	public String toString() {
		if(type == "Reponse")
			return "Réponses: ";
		if(type == "AA")
			return "Authorité: ";
		if(type == "Addition")
			return "Additionnelles: ";
		return "Réponses authorités et/ou additionnelles: ";
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

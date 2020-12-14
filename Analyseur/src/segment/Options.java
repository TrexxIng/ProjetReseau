package segment;

import java.util.ArrayList;
import java.util.List;

import champs.Data;
import champs.IChamps;
import champs.adresseEtPort.AdresseIP;
import champs.longueur.Length1Bytes;
import champs.options.TypeOptions;
import champs.options.ValeurOption;


public class Options implements ITrame {
	private List<IChamps> listOption;
	private List<String> listData;
	private int sizeOption = 0;
	private String type;
	private String protocol;
	private boolean stop = false;
	
	public Options(List<String> listOctet, String protocol) {
		this.protocol = protocol;
		this.listData = listOctet;
		this.listOption = new ArrayList<>();
		
		
		List<String> list= new ArrayList<>();
			
		/** ajout type d'options */
		list.add(listData.get(0));
		listData.remove(0);
		listOption.add(new TypeOptions(list,protocol));
		this.type = ((TypeOptions)listOption.get(0)).getType();
			
		if(type != "Fin d'options EOOL" && type != "Pas d’opération") {
			/** ajout taille option */
			list= new ArrayList<>(); 
			list.add(listData.get(0));
			listData.remove(0);
			listOption.add(new Length1Bytes(list,"option"));
			this.sizeOption = ((Length1Bytes)listOption.get(1)).getLongueur();
			
			/** ajout de la valeur */
			list= new ArrayList<>(); 
			list.add(listData.get(0));
			listData.remove(0);
			listOption.add(new ValeurOption(list));
			
			/** traitement des options selon type et taille */
			list= new ArrayList<>(); 
			for(int i = 0; i<sizeOption-3;i++) {
				list.add(listData.get(0));
				listData.remove(0);	
			}
			if(type == "Enregistrement de route") {
				this.listAdresseIP(list);
			}
			else {
				listOption.add(new Data(list));
			}
		}
		else {
			this.sizeOption = 1;
			if(type == "Fin d'options EOOL") 
				stop = true;
		}
	}

	@Override
	public boolean checkSize() {
		return true;
	}

	
	@Override
	public List<String> getData() {
		return listData;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}
	
	@Override
	public String toString() {
		if(sizeOption == 0) return "Pas d'options";
		return "Option: "+type+" ("+sizeOption+" octets)";
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
		if(sizeOption > 0) {
			for(int i = 0; i<listOption.size(); i++) {
				s +="\n"+listOption.get(i).formatDisplay(tab+1);
			}
		}
		return s;
	}
	
	private void listAdresseIP(List<String> list) {
		List<String> listIP;
		while(list.size()>4) {
			listIP = new ArrayList<>();
			for(int i = 0; i<4;i++) {
				listIP.add(list.get(0));
				list.remove(0);
			}
			listOption.add(new AdresseIP(listIP,""));		
		}
	}
	
	@Override
	public int getSize() {
		return sizeOption;
	}
	
	public boolean stop() {
		return stop;
	}

}

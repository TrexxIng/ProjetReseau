package segment;

import java.util.ArrayList;
import java.util.List;

import offset.AdresseIP;
import offset.Data;
import offset.IOffset;
import offset.Length1Bytes;
import offset.TypeOptions;
import offset.ValeurOption;


public class Options implements ITrame {
	private List<IOffset> listOption;
	private List<String> listData;
	private int sizeOption = 0;
	private String type;
	
	public Options(List<String> listOctet) {
		this.listData = listOctet;
		this.listOption = new ArrayList<>();
		
		List<String> list= new ArrayList<>();
			
		/** ajout type d'options */
		list.add(listData.get(0));
		listData.remove(0);
		listOption.add(new TypeOptions(list));
		this.type = ((TypeOptions)listOption.get(0)).getType();
			
		if(type != "EOOL: Fin d'options") {
			/** ajout taille option */
			list= new ArrayList<>(); 
			list.add(listData.get(0));
			listData.remove(0);
			listOption.add(new Length1Bytes(list,"Options"));
			this.sizeOption = ((Length1Bytes)listOption.get(1)).getLongueur();
			
			/** ajout de la valeur */
			list= new ArrayList<>(); 
			list.add(listData.get(0));
			listData.remove(0);
			listOption.add(new ValeurOption(list));
			
			/** traitement des options selon type et taille */
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
		}
	}

	@Override
	public boolean checkSize() {
		if(sizeOption == 1 || ((sizeOption+1) % 4 == 0) ) return true;
		return false;
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

}

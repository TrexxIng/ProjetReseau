package segment;

import java.util.ArrayList;
import java.util.List;

import offset.AdresseMAC;
import offset.IOffset;
import offset.TypeEther;

public class Ethernet implements ITrame {
	private List<IOffset> listEther;
	private List<String> listData;
	private int sizeEther;
	
	
	public Ethernet(List<String> listOctet) {
		this.sizeEther = listOctet.size();
		this.listData = listOctet;
		this.listEther = new ArrayList<>();
		List<String> list= new ArrayList<>(); 
		
		/** ajout adresseMac destination */
		for(int i = 0; i < 6; i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}		
		listEther.add(new AdresseMAC(list,"Destination"));
		
		/** ajout adresseMac source */
		list = new ArrayList<>(); 
		for(int i = 0; i < 6; i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		listEther.add(new AdresseMAC(list,"Source"));
		
		/** ajout Type Ethernet */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		listEther.add(new TypeEther(list));
	}

	@Override
	public List<IOffset> getListOffset() {
		return listEther;
	}
	
	public String getDataType() {
		return ((TypeEther)listEther.get(2)).getType();
		
	}
	
	@Override
	public String toString() {
		String s = "Trame Ethernet (avec données): "+sizeEther+" octets";
		for(int i = 0; i<listEther.size(); i++) {
			s +="\n\t"+listEther.get(i).toString();
		}
		return s;
	}

	@Override
	public boolean checkSize() {
		if((sizeEther - listData.size() == 14) 
				&& (listEther.size() == 3)) return true;
		return false;
	}

	@Override
	public List<String> getData() {
		return listData;
	}

	@Override
	public String formatDisplay(int tab) {
		String stab ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				stab += "\t";
			}
		}
		String s = stab+"Trame Ethernet, taille (avec données): "+sizeEther+" octets";
		for(int i = 0; i<listEther.size(); i++) {
			s +="\n"+listEther.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}
	

}

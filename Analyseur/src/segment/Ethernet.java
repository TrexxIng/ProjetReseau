package segment;

import java.util.ArrayList;
import java.util.List;

import champs.IChamps;
import champs.adresseEtPort.AdresseMAC;
import champs.trameSuiv.TypeEther;

public class Ethernet implements ITrame {
	private List<IChamps> listEther;
	private List<String> listData;
	private int sizeEther;
	
	
	public Ethernet(List<String> listOctet) {
		this.sizeEther = 0;
		this.listData = listOctet;
		this.listEther = new ArrayList<>();
		List<String> list= new ArrayList<>(); 
		
		/** ajout adresseMac destination */
		for(int i = 0; i < 6; i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}		
		this.sizeEther += list.size();
		listEther.add(new AdresseMAC(list,"Destination"));
		
		/** ajout adresseMac source */
		list = new ArrayList<>(); 
		for(int i = 0; i < 6; i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		this.sizeEther += list.size();
		listEther.add(new AdresseMAC(list,"Source"));
		
		/** ajout Type Ethernet */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeEther += list.size();
		listEther.add(new TypeEther(list));
	}

	
	public String getDataType() {
		return ((TypeEther)listEther.get(2)).getType();
		
	}

	@Override
	public boolean checkSize() {
		if((sizeEther == 14) 
				&& (listEther.size() == 3)) return true;
		return false;
	}

	@Override
	public List<String> getData() {
		return listData;
	}

	@Override
	public String toString() {
		return "Trame Ethernet: "+sizeEther+" octets";
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
		for(int i = 0; i<listEther.size(); i++) {
			s +="\n"+listEther.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}
	
	@Override
	public int getSize() {
		return sizeEther;
	}
	

}
